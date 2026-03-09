# Partition in Database

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

