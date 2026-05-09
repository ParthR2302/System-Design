# Different Databases

## SQL vs NoSQL

### Relational Database

Bad data locality could lead to distributed transactions.
- Two Phase Commit is slow
- Reads from many nodes is slow

### Non Relational Databases

Uses `denormalised data` -> Better data locality, repeated data

**Tradeoffs:**
1. Avoid cross platform reads, all relevant info stored together
2. Denormalized data leads to more writes, may need distributed transactions
3. May have to send whole document over network depending on implementation

## MySQL vs PostgreSQL

Should we ever use SQL if we don't need normalized data?

### Common Features

1. B-Tree based indexes -> better for reads?
2. Single Leader Replication -> No write conflicts?
3. Configurable isolation levels -> Data correctness? Performance cost

### Isolation Differences

|MySQL - Two Phase Locking|PostgreSQL - Serializable Snapshot Isolation|
|--|--|
|Every row has locks|Transactions read from data snapshots|
|Read only transactions can grab in shared node|If transaction reads value which is modified by another transaction before committing, original needs to be rolled back|
|Lots of deadlocks to detect and undo||

**NOTE:** Both of the databases are Open-Source and they always have different development happening. The characteristics mentioned can or cannot be true in future

**`Conclusion:`** 
- Use SQL database both for data that needs to be normalized and for data that needs to be correct.
- In theory SSI > 2PL, however if there are a lot of conflicting transactions, pessimistic locking may be better!