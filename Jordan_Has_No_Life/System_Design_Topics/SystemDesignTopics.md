# System Design

## Table of Contents
- [Database Indexes](#database-indexes)
  - [Hash Indexing](#hash-indexing)
  - [B-Trees](#b-trees)
  - [LSM Tree](#lsm-tree)
  - [SS Table](#ss-table)
    - [LSM Optimizations](#lsm-optimizations)
    - [Compaction](#compaction)
  - [Multi Dimensional Index](#multi-dimmensional-index)
- [ACID Database Transactions](#acid-database-transactions)
  - [Atomicity](#atomicity)
  - [Consistency](#consistency)
  - [Isolation - Important](#isolation---important)
  - [Durability](#durability)
  - [More on ACID](#more-on-acid)
  - [Read committed Isolation - Important](#read-committed-isolation---important)
  - [Race Conditions](#some-examples-of-race-conditions)
    - [Dirty Write](#dirty-write-writing-over-uncommitted-values)
    - [Dirty Reads](#dirty-reads-reading-uncommitted-data)
    - [Non Repeatable Read and Read Skew](#non-repeatable-read-and-read-skey)
    - [Write Skew and Phantom Writes](#write-skew-and-phantom-writes)
    - [Lost Update](#lost-update)
  - [Achieving ACID: Serial Execution](#achieving-acid-serial-execution)

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

## ACID Database Transactions

A way of writing to certain databases, the database provides certain abstractions about those writes that allows us to guarantee that certain properties (ACID) are going to hold.

### Atomicity

All writes succeed or none of them do

### Consistency

All fails occur gracefully, no invariants are broken
- Successfully enforcing atomicity guarantees consistency or atleast it should.

### Isolation - Important

Appears as if all transactions are exceuted independently of eachother. No race condition!

### Durability

committed writes don't get lost.

### More on ACID

Achieving Atomicity, Consistency and Durability: Use a Write Ahead Log (WAL)

### Read committed Isolation - Important

Databases are multi-threaded with bunch of reader threads and bunch of writer threads.
- Since the concurrancy in multiple transactions, we cannot know which transaction occured first. Which can create issues threatening the correctness of our results.

### Some Examples of Race Conditions:**

#### Dirty Write: Writing over uncommitted values 
- A dirty write occurs when a transaction overwrites uncommitted data that was written by another concurrent transaction
- There is a transaction which updates rows in two different tables. T1 and T2 both are of that type. T1 updates row of table-1, then T2 updates row of table-1, then T2 updates row of table-2 and then T1 updates row of table-2
    - Result: Table-1 has value from T2 transaction and Table-2 has value from T1 transaction
- Fix: Row level locks

#### Dirty Reads: Reading uncommitted data
- A dirty read occurs when a transaction reads data that has been modified by another concurrent transaction which then rolls back its changes, making the read data invalid.
- Fix: 
    1. Row level locking. (Locking is slow, which can cause problems in read operations as there can be tons of reads between write and commit transactions)
    2. Store old value until commit (Extra storage but relief from locking)

Any database that implements Read Commit Isolation is going to `protect againts both dirty reads and dirty writes`.

#### Non Repeatable Read and Read Skey

- The above 2 issues occur when a read transaction is long and a write transaction takes place during that read transaction.
- `Repeatable Read guarantees` that if you read a row once inside a transaction, reading it again will return the same value — even if another transaction updates it meanwhile.
- `Read Skew` is you read two related values that should be consistent together — but you see one old value and one new value.
- Fix:
    1. Write Ahead Log
    2. Database Snapshot (`Snapshot Isolation`): When updating/inserting a value, don't just delete the old value. Store the old value with transaction number.
        - It will help in getting the correct latest value for a given transaction number. eg. transaciton at T15. If value is updated at T2, T7, T14, T20, T22 -> Value of T14 is taken for T15 calculation.
        
#### Write Skew and Phantom Writes

- `Phantom` occurs when two people write new rows that conflict and the issue with new rows is there are no locks to grab
    - Fix: Materialize Conflicts

#### Lost Update

- Two threads try to update the same counter. Initial counter value = 1. Thread-1 reads 1 and puts 2. Thread-2 reads before thread-1 changes the value so Thread-2 also reads 1 and puts 2. 
- At the end the value of the counter become 2 where it should have been 3. Meaning one of the `increament/update is lost`.
- Fix:
    - Pessimist Locking - Row Lock
    - Optimist Locking - Versioning
    - Atomic updates - Best for counters

### Achieving ACID: Serial Execution

CPU are faster than they used to be. Run everything on one core!
- Limits of this: Disks are slow, so we intend to achieve serial execution on data stored in memory. Less space, and data is not durable in memory
- VoldDB is an example of using Actual Serial Execution

When the execution query/script is more in size, sending it on network becomes slow. To handle this, we can use `Stored Procedures`. These are stored in the
- SQL funcitons sent over the network in advance. We just need to pass the parameters.
    - Ex. addInventory(productId, quantity)
- Hard to manage code deployements and version control, not used elsewhere much

