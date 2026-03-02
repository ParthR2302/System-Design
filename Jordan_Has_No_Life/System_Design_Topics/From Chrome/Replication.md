# Database Replication: Core Concepts & Topics

Database replication is the process of sharing information so as to ensure consistency between redundant resources. This guide outlines the critical topics required to master the subject.

## 1. Replication Architectures
*   **Single-Leader (Master-Slave)**
    *   One authoritative node handles all writes.
    *   Followers provide horizontal scaling for read-heavy workloads.
*   **Multi-Leader (Master-Master)**
    *   Multiple nodes accept writes; ideal for multi-region availability.
    *   Requires complex conflict resolution strategies.
*   **Leaderless (Quorum-based)**
    *   No primary node; clients write to/read from multiple nodes.
    *   Common in Dynamo-style systems (e.g., Cassandra, Riak).

## 2. Timing and Synchronicity
*   **Synchronous Replication**
    *   Guarantees no data loss but increases write latency.
*   **Asynchronous Replication**
    *   Optimized for performance; risks "stale reads" and data loss on failover.
*   **Semi-Synchronous**
    *   Wait for at least one follower to acknowledge before returning success.

## 3. Consistency Models & Lag Issues
*   **Eventual Consistency**
    *   The "Base" model: replicas will converge given enough time.
*   **Read-Your-Writes Consistency**
    *   Ensures a user sees their own updates immediately.
*   **Monotonic Reads**
    *   Prevents a user from seeing data "move backward" in time.
*   **Replication Lag**
    *   Measuring the delay between leader writes and follower updates.

## 4. Multi-Leader Topologies
*   **Circular:** Each node connects to two others in a ring.
*   **Star:** A central hub distributes updates to spokes.
*   **All-to-All:** Every node communicates with every other node (most robust).

## 5. Conflict Resolution
*   **Convergence:** Ensuring all replicas arrive at the same final state.
*   **Version Vectors:** Tracking causality between writes to detect conflicts.
*   **Last Write Wins (LWW):** Simplistic but potentially destructive resolution.
*   **CRDTs:** Data types that merge automatically without conflicts.

## 6. Implementation Mechanics
*   **Statement-based Replication:** Shipping SQL queries (e.g., `INSERT...`).
*   **Write-Ahead Log (WAL) Shipping:** Copying the physical disk blocks.
*   **Logical (Row-based) Replication:** Shipping the specific data changes per row.

## 7. Deep Dive: Conflict-Free Replicated Data Types (CRDTs)

CRDTs are specialized data structures that enable "Strong Eventual Consistency" (SEC) by embedding conflict resolution directly into the data type's logic.

### A. Mathematical Foundations (ACI Properties)
To ensure convergence without coordination, CRDT merge operations must be:
*   **Associative:** Grouping of merges doesn't change the result.
*   **Commutative:** Order of receiving updates doesn't change the result.
*   **Idempotent:** Duplicate updates (e.g., retried network packets) don't change the result.

### B. CRDT Flavors
*   **State-based (CvRDT):** 
    *   Replicas sync by sending their full internal state.
    *   **Pros:** Tolerates message loss and reordering easily.
    *   **Cons:** High bandwidth usage as state grows.
*   **Operation-based (CmRDT):** 
    *   Replicas sync by broadcasting individual operations (e.g., "add element X").
    *   **Pros:** Highly bandwidth-efficient.
    *   **Cons:** Requires a reliable, ordered delivery protocol (Causal Broadcast).
*   **Delta-based:** Sends only the "diff" since the last sync to optimize CvRDTs.

### C. Standard CRDT Implementations
1.  **Counters:**
    *   **G-Counter:** Increments only; each node has a slot in a vector.
    *   **PN-Counter:** Uses two G-counters (one for + and one for -) to allow decrements.
2.  **Sets:**
    *   **G-Set:** Elements can only be added.
    *   **OR-Set (Observed-Remove):** Supports adds and removes by tagging each add with a unique ID; removals reference those specific IDs.
3.  **Registers:**
    *   **LWW-Register:** Uses a wall-clock timestamp; the highest timestamp wins.

### D. Practical Challenges
*   **Tombstones:** In many set and sequence CRDTs, deleted items must leave a "tombstone" to prevent them from being re-added by late-arriving messages. This can lead to unbounded memory growth.
*   **Garbage Collection:** Complex logic is required to safely remove tombstones once all replicas have seen the deletion.
*   **Causality Tracking:** Managing vector clocks or version vectors to maintain the correct "happened-before" relationship.


## 8. Leaderless Replication (Dynamo-style)

In leaderless systems, the responsibility for consistency shifts from a central leader to the client (or a coordinator node) using quorum mathematics.

### A. Quorum Variables
*   **N:** Total number of replicas.
*   **W:** Minimum nodes required to confirm a **Write**.
*   **R:** Minimum nodes required to participate in a **Read**.
*   **Consistency Guarantee:** If $W + R > N$, you achieve a "strict quorum" where reads theoretically see the latest write.

### B. High Availability Mechanisms
*   **Sloppy Quorum:** Writing to healthy nodes that aren't the primary designated replicas to avoid rejecting writes during network partitions.
*   **Hinted Handoff:** A "delivery notice" system where temporary nodes hold data for a failed node and deliver it upon its recovery.

### C. Repair Strategies
*   **Read Repair:** Stale data is patched on-the-fly when a client detects an older version during a quorum read.
*   **Anti-Entropy:** Background synchronization using **Merkle Trees** to find and fix inconsistencies without sending the entire dataset over the network.

### D. Trade-offs
*   **Pros:** No single point of failure; excellent write availability; tunable consistency.
*   **Cons:** Complex conflict resolution (requires Version Vectors); potential for stale reads if quorums are "sloppy."
