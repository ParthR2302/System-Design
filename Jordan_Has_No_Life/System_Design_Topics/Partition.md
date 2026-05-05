# Partition in Database

1. [Overview](#overview)
2. [Partition Types](#partition-types)
   - [Range Based Partition](#range-based-partition)
   - [Hash Range Based Partition](#hash-range-based-partition)
3. [Secondary Indexes](#secondary-indexes)
   - [Local Secondary Indexes](#local-secondary-indexes)
   - [Global Secondary Index](#global-secondary-index)
4. [Distribute Transactions](#distribute-transactions)
   - [Two Phase Commit](#two-phase-commit)
5. [Consistent Hashing](#consistent-hashing)


## Overview

Splitting the database into multiple partition so that the storage is distributed.
- Some of/All of the partitions can be in different machines

## Partition Types

### Range Based Partition

Assume partition key is on Name. Names starting from A-C are on one partition, D-F on another and so on.

- Pros: Similar keys on same node -> Good for Range Query
- Cons: Hotspot

**Hotspot:** What if there are billion users with name starting from A to B. Only couple of thousand users with name starting from D to E.
- Node with A to B would become hotspot with large data

### Hash Range Based Partition

Use Hashing function on key. Distribute to partition on the value of Hash

- Pros: Relatevly even distribution of the keys
- Cons: No Data Locality for range queries

To solve the range query issue we can use the [`Secondary Indexes`](#secondary-indexes)

## Secondary Indexes

An additional index to our primary index.

Is a second copy of the data effectively where we are sorting it in a different sort order.

### Local Secondary Indexes

Problem: NBA player database is partitioned and sorted on name but now we want them sorted by Height.

Lets say there are 2 shards and in them data is stored in sorted order for Names.

A copy of data for each shard which is sorted by Height is stored on the same physical shard. 
- Here same data for as the main shard, just sorting order is on Height
- It is organised into seperate internal data structure (typically a B-Tree)

- Pros: No need to perform any extra writes over the network
- Cons: To find all players with a given height, we need to read from each shards

### Global Secondary Index

Shard possible values of an Index on each node. This is in addition of the original copy of the data.
- From above example where for the main copy, we are storing in sorted order of Name. Now instead of storing the same data-sharding pair into same shard with index on Height, store the Height range values distributed on copy shard
    - Copy Shard-1 will contain players with Height <= 6'3", and so on

- Pros: Now reads using index require reading from only one node
- Cons: We may have to write to multiple shards at once, requires **`distributed transactions`**

## Distribute Transactions

A distributed transaction is a set of database operations that spans across two or more distinct nodes (servers, databases, or shards) but is treated as a single, indivisible unit of work

### Two Phase Commit

Why do we need it? Need atomicity in distributed transactions

How does Two-Phase Commit works:
- Because there is no single "brain" to control all shards, the system uses a Coordinator and a protocol called Two-Phase Commit (2PC): 
    - Phase 1 (Prepare): The coordinator asks all involved shards, "Are you ready to commit this change?" Each shard checks its local locks and resources and votes YES or NO.
    - Phase 2 (Commit): If all shards voted YES, the coordinator tells them to finalize the change. If even one shard voted NO (or didn't respond), the coordinator tells everyone to Abort/Rollback

Problems wirh 2PC:
- Coordinator goes down: No transaction can proceed, receiving nodes hold locks and can't touch rows
- Receiver goes down: Transaction can't commit, coordinator needs to send it messages forever until it comes back up

## Consistent Hashing

1. Distributes Key Evenly
2. Minimal Data sent over network during rebalancing
3. Great for `Partitioning` and `Load Balancing`

Number of Partitions per node???

Detailed notes present in Hello_Interview/Basics/Sharding/#Consistent-Hashing

## Linearizable Databases

### What is Linearizable Storage?**

Linearizable storage, also known as strong or atomic consistency, guarantees that every read operation returns the most recent write for a given piece of data
- We need this for "correct" reads
- Just one person with the lock, just one database leader

Instead of a single server database, linearizable databases can be distributed, but they act as a single server database from client's perspective
- To achieve this linearizability in distributed databases, it often requires consensus algorithms

Linearizable storage is going to allow us to do is build applications on top of that such as distributed locking or service discovery mechanism

### How do we order our writes?

**Single Leader Replication:** Using replication log
- Send the replication log from leader to all the replicas

**Multi-Leader/Leaderless Replication:** [Version vectors](./Replication.md#version-vectors-important)/[Lamport Clock](#lamport-clocks---o1-space) 

Version Vectors / Lamport Clocks are NOT Linearizable [image](#lamport-clocks---o1-space)


#### Single Leader is not linearizable

Writes order is like x=5 and then x=10. Now, x=5 is replicated in the followers. X=10 is in replication log but not replicated yet
- If replication is **`Asynchronous`** then we cannot achieve linearizability
- Also, it is not a fault tolerant linearizability

We need **`Total Order Broadcast`**:
1. Every node has to agree on the order of writes
2. In the face of faults, we cannot lose any writes

How do we make this happen? [Distributed Consensus](#distributed-consensus)

#### Lamport Clocks - O(1) space

![Lamport Clocks](./Images/lamport_clocks.png)

Version Vectors / Lamport Clocks are NOT Linearizable

![Not Linearizable](./Images/vv_lc_not_linearizable.png)

Why? We can be in good shape (consistent data between nodes) once convergence happens between nodes.
- Lamport clock only give us a total ordering after the fact. We still need to wait for the convergence (replication) between nodes