# Replication

1. [Overview](#overview)
2. [Data Replication between replicas](#data-replication-between-replicas)
   - [Synchronous Replication](#synchronous-replication)
   - [Asynchronous Replication](#asynchronous-replication)
   - [How is data replicated?](#how-is-data-replicated)
3. [Dealing with Stale Reads](#dealing-with-stale-reads)
   - [Reading your own writes](#reading-your-own-writes)
   - [Monotonic Reads and Writes](#monotonic-reads-and-writes)
   - [Consistent Prefix Reads](#consistent-prefix-reads)
4. [Single Leader Replication](#single-leader-replication)
   - [Follower goes down](#follower-goes-down)
   - [Leader Goes Down](#leader-goes-down)
5. [Multi Leader Replication](#multi-leader-replication)
   - [Pass Writes Between Leaders](#pass-writes-between-leaders)
   - [Modification in the Replication Log](#modification-in-the-replication-log)
   - [Issues in Multi Leader Replication](#issues-in-multi-leader-replication)
   - [Solutions for Write Conflicts](#solutions-for-write-conflicts)
6. [Leaderless Replication](#leaderless-replication)
   - [The Quorum Mechanism](#the-quorum-mechanism)
   - [Repair and Data Propogation techniques](#repair-and-data-propogation-techniques)
   - [More on Quorum Mechanism](#more-on-quorum-mechanism)
7. [Replication Summary](#replication-summary)

## Overview

Multiple instances of same database.

One database in America's region, One in EU region and One in APAC region is an example of database replication.

**Benefits:**
- Redundant data -> Avoids data loss
- High DB throughput

## Data Replication between replicas

### Synchronous Replication

User makes update in database-1. Copy of that data would go to Database-2. 
- It's not considered valid until every single replica has a copy of that data

**`Strong Consistency`**

### Asynchronous Replication

User performs write in database-1 and hear backs from that database. From this moment the write is considered valid.
- In the background, the first database is going to asynchronously replicate the data to second database

**`Eventual Consistency`**

### How is data replicated?

Three ways:
1. Take the SQL query and copy that to other databases
    - Issues: Use of something like time.now()
2. Write Ahead Log (WAL)
    - Useless if switching database software between replicas
3. Replication Log
    - id:1, Name:Parth, Age:24
    - id:2, Name:Malav, Age:25

## Dealing with Stale Reads

Making Eventual Consistency less of a problem

### Reading your own writes
- Read from the same DB that you write to
- Use timestamp to check while reading if data from that timestamp is present or not (Not reliable in all of the cases)

### Monotonic Reads and Writes
- **Out-of-Order Updates:** A newer write (W2) might be applied before an older write (W1), causing the system state to reflect an outdated value.
- **Confusing User Experience:** A user might update their profile (e.g., change a password), but a subsequent read shows the old data because the write was sent to a different, lagging replica.
- **Non-Atomic Ordering:** While "Monotonic Writes" guarantees that if a process performs w1 then w2, all processes observe w1 before w2, a lack thereof allows different replicas to process updates out of sequence.
- **Replication Lag:** When writes are sent to different replicas that are not properly synchronized, the system allows the state to go backward in time from the perspective of the user.
    - **Fix for Monotonic Reads:** User reads off the data from same replica everytime
    - **Fix for Monotonic Writes:** 
        - Time Stamping: ssigning unique, increasing timestamps to every write to ensure they are committed in chronological order.
        - WAL: Maintain a sequence of operations that all replicas must follow.
        - Consensus Protocol: Using algorithms like Paxos or Raft to coordinate writes across all replicas so they reflect the same order.
        - Client-Driven Sequencing

### Consistent Prefix Reads

It's a consistency model that guarantees if a sequence of writes happens in a specific order, anyone reading those writes will see them appear in that same order
- The "Prefix" Concept: If the system has received a series of updates (A, B, C), a reader might see only A, or A and B, but they will never see B without A, or C without B.

## Single Leader Replication

Note for the section: In majority of the scenarios we usually prefer Asynchronous replication as Synchronous replication is not that much feasible

Write happens to Leader DB, read can happen from any Db (Leader + Replicas) 
- Increased Durability
- **`High Read Throughput`**

DB Failure scenarios:

### Follower goes down

- At the time when the follower went down it was at read 50. After it came back, leader DB is at 70. Leader DB can send data from 51 to 70 to that follower DB. How do we know these numbers? -> Replication Log

### Leader Goes Down

#### Network Connection Issue between Leader and One of the Followers

#### Leader dies before populating the latest write

- Leader is at write 50. Followers are at write 40 (41 to 50 are not yet replicated in followers)
- Leader goes down. Follower-2 becomes a new leader.
- But now the new leader is at write 40 and write 41 to 50 are lost

#### Leader is dead, follower is now a leader prime, original leader comes back up

It's called **`Split Brain`**

Now, user-1 writes to Original Leader and user-2 writes to Leader Prime. They both send replication call to followers.
- Followers get confused, what the hell - 2 leaders?

To tackle the Leader Goes Down situation, we need **`Distributed Consensus`**.

In the context of Leader Failure, Distributed Consensus is the mechanism used to ensure that all remaining nodes in the cluster agree on a single version of the truth: specifically, who the new leader is and what the final state of the data was before the old leader crashed.

Without consensus, we risk a "Split Brain" scenario, where two nodes both believe they are the leader, leading to data corruption.
The Goal of Consensus during Failover

When the leader goes down, the remaining replicas must agree on three things:
- Detection: Is the leader actually dead, or just slow? (Agreement on status).
- Election: Which follower should be promoted? (Usually the one with the most recent Log Sequence Number).
- Consistency: What was the last "committed" write? (Ensuring no data is lost or duplicated during the hand-off).

## Multi Leader Replication

- More write throughput
- If leaders are scatterred accross different region, any write request from a location can go to nearest leader.

There are many configurations in which they pass these writes from one to another

### Pass Writes Between Leaders

#### Circle Topology

All the leaders in a connected graph are going to pass the writes to each other.
- Writes are propogated to next node. That next node will pass the write to its next node.
- If one node (node-2) is killed, node-1 relies on node-2 to pass on the write to node-3.

#### Star Topology

One center node and bunch of outer nodes.
- All the outer nodes are going to pass their writes to center node and center node then distributes the write to all the other outer nodes.
- What if the center node goes down?

#### All to All topology

One node is dead, no problem

**The problem of causality**:
- Chronological order of writes
    - Node-2 has message A written to it and it sends A to Node-3
    - Node-3 has now B written to it
    - Node-3 passes B to Node-1 (Node-2 has not yet passed A to Node-1)
        - Meanig, Node-1 has received message B which is causily depended on message A
    
Fix of the above is using slight modifications in the replication log

### Modification in the Replication Log

Add a new entry to keep track which writes in the application log have been seen by which database.

### Issues in Multi Leader Replication

#### Write Conflict

If for a key, a write goes to L1 and L2, which write wins?
- For a particular key, write goes to one particular database only:
    - Conflict Avoidance
    - Limit the write throughput
    - If the selected leader is in America, all the writes from across globe need to go there
- `Last Write wins:` Use time stamp to decide
    - What timestamp to use?
        - Use sender's timestamp? Not reliable
        - Use Receviver's timestamp?
            - Only reliable if we could rely on those timestamp? Yes, we can. 
                - Clocks on computer run on Quartz Crystal. Issue of clock skew.
                - Use of NTP (Network Time Protocol). In terms of time from NTP, it is perfect. But we are making network call for this

### Solutions for Write Conflicts

#### Distribute Counter

#### Version Vectors (IMPORTANT)

Instead of storing counter in each database, we're actually going to store the number of increaments that we have seen from each leader node
- Each entry on version vector represents how many writes from each leaders have been accounted for on that node
    - Image stored in Images folder
- To merge version vectors, take the bigger element at each index of the vector, thats how many time inc() has been called on the leader.

**When are writes concurrent?**

How to check concurrent writes using Version Vectors?

Two writes are concurrent if neither vector is >= than the other
- Eg. If in three leader replication (consider all to all topology) [5,2,1] and [4,3,2]

**Detailed Walkthrough of the Scenario:**

Imagine three database nodes: Node 1, Node 2, and Node 3. They all start with a version of a document at [4, 2, 1].
1. The Write on Node 1:
    - A user updates the document on Node 1. Node 1 increments its own slot.
    - New Vector: [5, 2, 1]
2. The Write on Node 2:
    - Simultaneously (before the update from Node 1 reaches Node 2), another user updates the document on Node 2. Node 2 increments its own slot. Node 2 might also have seen a previous update from Node 3 that Node 1 hasn't seen yet.
    - New Vector: [4, 3, 2]
3. The Synchronization (Conflict!):
    - When these two nodes eventually exchange data, the system compares the vectors.
    - Node 1 looks at [4, 3, 2] and says: "Wait, you have updates for Node 2 and 3 that I don't (3>2 and 2>1), but I have an update for Node 1 that you don't (5>4)."

**What can we actually do when we realise that the writes are actually concurrent?**

1. Store Sibilings: Have user decide later
    - Once the user chooses, delete the non-selected values and hence conflict is resolved

2. The database automatically merges them for us
    - **`CRDTs: Conflict-free Replicated Data Types`**

#### CRDT (Conflict-free Replicated Data Type)

In a multi leader replication, we are prone to have write conflicts

CRDTs are used in databases like Riak, Redis (sets in Redis enterprise)

**Operational CRDT**

As oppose to sending a full vector, we only send less data like inc(0). Where inc() is the funciton and 0 is the leader index.

Downside:
- Whenever we have a causal relationship between 2 leaders, operational CRDT could let us down.
    - Operations on L1: add("ham"), remove("ham")
    - Ideally, replication request on L2 should be in same order, but what if 1st operation (add()) gets dropped and not reached or operation-2 reaches first?
        - These two operations are causally related becuase we can only perform op-2 if op-1 is already performed
    - Not only we need to ensure they are not dropped but we need to make sure they are not duplicated.
        - These operations in itself are not idempotent (Do the same thing many time over and no difference).

**State Based CRDT:**

Instead of sending only an operation, send the entire CRDT (vector) and use merge operation on receiver's side.
- There are some conditions which merge function needs to fulfill in order to be eligible

No need to worry about the oreder of request, as the state is eventually going to be consistent.

Since the order doesn't matter we can communicate via **`Gossip Protocol`**
- It allows us to easily communicate with a bunch of nodes in the cluster without any messaging middleware

**Issues with this:**
- Gossip Protocol has no guarantee on possible when the update is going to reach all the receivers.
- Additionally, state-CDRTs can be long so it can take long to pass over the network

**Type of CRDTs:**

1. Distributed Counter based CRDTs (Increament and Decreament)
2. Sets. Operations: add("Val"), remove("Val")
    - With the above set CRDTs where we have add and remove CRDTs,
        - We can't add back an element that has been removed (that is added in the remove CRDT)
        - Cool solution: Add and identifier to each add and remove operations
            - Add: {"ham":153, "burger":482, "ham":1724}
            - Remove: {"ham":153}
3. Sequence CRDTs:
    - Use of list which is tough to implement if we don't know the elements beforehand
    - Google Docs, VS Code like text-editors have implemented this

## Leaderless Replication

Write to many nodes, read from many nodes

Used in Apache Cassandra, Riak, 

### The Quorum Mechanism

Consistency is managed through a **`"voting" system called a Quorum`**: 
- N (`Replicas`): The total number of nodes that should store a copy of the data.
- W (`Write Quorum`): The minimum number of nodes that must acknowledge a write for it to be considered successful.
- R (`Read Quorum`): The minimum number of nodes that must be queried during a read.

`Overlap Rule` (W + R > N): If this condition is met, at least one node in the read set is guaranteed to have the latest write, enabling Strong Consistency.

Writes are stored with versions, so if we get multiple values while reading, the value with latest version is the correct result.
- While reading, the values with lower versions are updated with highest version value.
- This update to stale value while reading is called **`Read Repair`**

### Repair and Data Propogation techniques

#### Read Repair

#### Anti Antroy

Propogate Changes in Background

If db1 has processed a,c,e,f,g and db2 has processed b,c,d,f,g. 
- Now when trying to propogate the changes only send writes a,e from db1. Don't need full replication log, it would send to many writes.
    - Why there are common writes? In leaderless partition, writes happen to multiple nodes (W)

**How can we quickly tell which writes to send?**

Using **`Merkle Trees`**. Logarithmic TC

### More on Quorum Mechanism

**Are quorums strongly consitent?** 
- Will all users that perform the read operation at the exact same time, receive the same data? NO.

1. Race condition
    - Consider N is 3 and W is also 3
    - If multiple write requests come on same time, the order of writes to go to each database can differ
    - At time-1: W1,W1,W2. T2: W2,W3,W3. T3:W3,W2,W1
2. Write fails
    - N is 3, W is 2 and R is 2.
    - What if write to one DB fails? Read from 2 DB can take old values since in this case (W + R > N) is falling false
3. Sloppy Quorums
    - There are multiple clustes of Db in different region
    - C1 in America has 3 DB and C2 in APAC has 3 DB.
    - If C1 entirely goes down, a smart system will route the reqest in America's region to APAC region.
    - APAC in it self is resilliant as N=3,W=2,R=2. 
    - But what if at some point C1 comes back up. Now requests' in America's region go to C1. C1 has not yet done data correction with C2.
        - We need to take data from C2 and transfer it to C1 which is called as `hinted handoff`.

Leaderless database setup can be good for certain applications, but if you need completely correct data, do not rely on them

## Replication Summary

Need: Durability, Throughput, Geography

**Single Leader Replication**
- No write conflicts
- Eventual Consistency (Strong consitency is time consuming)
- Low Write Throughput
- Single point of failure

**Multi Leader Replication**
- High Write Throughput
- Good for large geography area
- Write conflicts
    - Can't use timestamp based solution
    - Version Vectors
    - CRDTs (Counter based CRDTs, Set CRDTs)

**Leaderless Replicaiton**
- Relatively High Write Throughput
- Quorum Reads/Writes
- Low Read Throughput
- Write Conflicts
    - Read Repair
    - Anti Antroy - Use of Merkle Tree