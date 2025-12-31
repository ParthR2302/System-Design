# Data Modeling:

- [Overview](#overview)
- [Schema Design](#schema-design)
- [Entities, Key and Relationships](#entities-keys-and-relationships)
- [Normalization vs Denormalization](#normalization-vs-denormalization)
- [Indexing](#indexing)
- [Scaling and Sharding](#scaling-and-sharding)
- [Key Points](#key-points)

## Overview:

A data model defines how your data is structured, stored, related.

Define what entities or tables exist, how are we going to identify and find them, how they relate or connect with each other.

**Database Model Options:** Relational Database, Document DB, Key-Value Store, Wide-Column DB, Graph DB.

## Schema Design:

Three key factors:
1. Data Volume - Where can data live? (Single DB vs Distributed)
    - In case of social media platform with millions of user, we might need the data to distribute on multiple machine
2. Access Patterns - How is data required? (Drives Indexes & Structure)
    - A news feed loading recent posts by followed users suggests that we'll want to denormalize that data or have a specific index so that the query can be really fast.
3. Consistency requirements - How strict? (ACID vs Eventual Consistency)
    - It defines how tightly coupled the data is. Financial transactions need strong consistency. So we keep related data in the same database. A user's activity feed can handle some eventual consistency, so we can distribute that data accross several systems maybe even like a cache.

All the techniques like entities, keys, normalization, indexes, etc are used to address the above 3 key factors.

## Entities, Keys and Relationships

## Normalization vs Denormalization

`Normalization` means that we are storing each piece of information in exactly one location.
- It keeps the data consistant but requires join for query

`Denormalization` means deliberately duplicating data across tables
- It makes querying faster but consistency (issue of anamoly) is one of the trade offs.

In system development, denormalization is needed only when there is a specific performance need which cannot be met by indexing.
- When we denormalize the data, its best to put it in cache

## Indexing:

First thing we need to do to make data access faster is not denormalization, its indexing.

`Indexing is a process` within a database where we create data structures that help the database find records more quickly than having to scan every row.

Types of Indexing: B-Tree, 

## Scaling and Sharding:

[Sharding](./Sharding.md)

## Key Points:

1. Choose a DB - Impact on how the data is stored
2. Outline Columns
3. Add primary and foreign keys
4. Determine what indexes we need
5. Determine whether to denormalize 
6. Consider if sharding is needed, choose a partition key

All in the context of access pattern, data volume and consistency requirements

## Resources:

- https://app.excalidraw.com/l/56zGeHiLyKZ/AjAIb7q1lP8




