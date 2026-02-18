# System Design

- [Database Indexes](#database-indexes)
    - [Hash Indexing](#hash-indexing)
    - [B-Trees](#b-trees)
    - [LSM Tree](#lsm-tree)
    - [SS Table](#ss-table)

## Database Indexes

Why do we need Database Indexes?
- Indexes exist to make data retrieval fast.
    - Without indexes → database scans every row.
    - With indexes → database jumps directly to the data.

Add index when:
- Column used in WHERE clause frequently, Used in JOIN condition, Used in ORDER BY, Used in GROUP BY

Faster reads on specific key value. Slower writes for any possible writes.

### Hash Indexing

Hash Maps are bad on Disk. Why?
- Hash function's work is to distribute the data evenly.
- Since disk is in itself a long array of locations, data stored far from each other makes it hard for the disk to fetch.

Hence, **`Hash indexes are always kept in memory`**.
- RAM is expensive and less in size
- RAM is also not durable

**`WAL - Write Ahead Log`**
- Writes are logged and stored sequentially in Disk instead of memory.

How does WAL provide durability?
- If we loose memory, we can recreate Hash Index by replaying all the changes in WAL.

**Pros:**
- O(1) reads and writes
**Cons:**
- Keys must fit in memory
- We can't do range query in Hash Indexing

### B-Trees

Its a balanced tree on disk.
- No limits to dataset size
- Support for range queries

Reads are fast but on the other hand writes are not that fast in B-Tree Indexing

### LSM Tree

LSM tree is a balanced binary search tree like red-black or a B-Tree or an AVL tree.

LSM tree is also stored in memory like Hash Indexes. It also uses WAL for durability.

Once the size of LSM tree crosses a threashold, it is converted into an **`SSTable`** which is stored in Disk and purge the LSM Tree.

### SS Table

Is is a conversion of LSM tree to be sorted in order with all of the scores kept same. 
- This SS Table is immutable.

Write is going to happen in LSM tree but we have to incorporate SS Table when reading.
- When reading, it first goes to the LSM Tree, and if the key isn't in there then perhaps it will be in the SS Table. 

Same thing hapens for delete case. This is where we do something called `tombstone`.
 

#### LSM Optimizations

- Add sparse index: Take certain keys from the SS table and write their location on disk.
- Bloom Filter: Is allows us to say whether a key is not in the table.
    - If it says that key is present in the table, it can be wrong. But if it says no, then its correct.
    - Using this, we can skip searching in the selected SS table.

#### Compaction

Writes add new record into index. Updating a key just adds another copy of it. Delete uses tombstones. We are going to potentially be wasting tons of storage.
- SS_Table_1 + SS_Table_2 = New_Sorted_Combined_SS_Table
    - Duplicates are not present in the resultant table

### Multi Dimmensional Index