# Database Types

## Linearizable Databases

What it is and why do we need it?
- We need it for correct reads.
    - It provide the strongest consistency model, ensuring that once a write completes, all subsequent reads reflect that update instantly, making a distributed system behave as if it were a single, non-replicated node.

All the writes are ordered.

### How do we order our writes?

#### In Single Leader Replication

Using Replication Logs

#### In Multi Leader and Leaderless Replication

Using Version Vectors/Lamport Clock

- Version Vectors take O(N) space where N is the number of Nodes
- Lamport Clock always exclusively takes 2 elements

##### Lamport Clocks

