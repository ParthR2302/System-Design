# How to prepare for System Design Interviews

- [Overview](#overview-of-the-desing-interview)
- [Things to focus on](#things-to-focus-on)
- [Fundamentals](#the-fundamentals)
- [Components](#components)
- [Practice Problems](#practice-problems)

<br>

- [Resources](#resources)

## Overview of the Desing Interview

Steps to follow:
1. Requirements
    - Functional (Feature of the system) and Non-Functional (Quality of the System) requirements
2. Core Entities
3. API or Interface
4. Data Flow
5. High-Level Design
    - Primary goal is to satisfy the functional requirements
6. Deep Dives
    - Primary goal is to satisfy the non-functional requirements

## Things to focus on:

1. Problem solving: Identify and Prioritize the core challanges
2. Solution Design: Create scalable architectures with balanced trade-offs
3. Technical Excellence: Demonstrate deep knowledge and expertise
4. Communication: Clearly explain complex concepts to stakeholders

## The Fundamentals

- Storage
    - Relational, Document Based, Key-Value
    - ACID vs BASE
- Scalability
    - Scale Compute: Horizontal and Vertical scaling
    - Scale Storage: Sharding, Consistent Hashing
- Networking
    - OSI Layers: Application Layer, Presentation Layer, Session Layer, Transport Layer, Network Layer, Data Link Layer, Physical Layer
    - Application Layer, Transport Layer and Network Layer are sufficient for dicussion in Interview
        - Application Layer: HTTP/HTTPS protocols, REST vs GraphQL vs RPC, RESTful semantics, DNS resolution, WebSockets vs SSE (Server Sent Events)
        - Transport Layer: TCP, UDP, Request Response Lifecycle
        - Network Layer: Load Balancing, Firewalls, ACLs (Access Control Lists)
- Latency, Throughput and Performance
    - Approximate latency numbers for common operations like memory access, disk reads or network calls.
    - Discuss throughput limitations and maybe perform a basic capcity planning estimations.
- Fault Tolerance and Redundancy
- CAP Theorem
    - Should be discussed in the Non-Functional requirements discussion time.

## Components

- Database
- Cache
- Message Queue
- Load Balancer
- Blob Storage
- CDN

## Practice Problems

- **Design a URL Shortener (Bitly)** - Tests your understanding of hashing, databases, and caching. 
- **Design Dropbox** - Tests file storage, synchronization, and metadata management. 
- **Design Ticketmaster** - Tests concurrency, race conditions, and transactional integrity. 
- **Design a News Feed** - Tests content delivery, personalization, and real-time updates. 
- **Design WhatsApp** - Tests real-time communication, presence detection, and message delivery. 
- **Design LeetCode** - Tests code execution environments, scaling compute, and security. 
- **Design Uber** - Tests geospatial indexing, matching algorithms, and real-time updates. 
- **Design a Web Crawler** - Tests distributed systems, scheduling, and politeness policies. 
- **Design an Ad Click Aggregator** - Tests high-throughput event processing and analytics. 
- **Design Facebook's Post Search** - Tests indexing, ranking, and search optimization. 

-----------------------

## Resources

- List of resources present in the description
    - https://www.youtube.com/watch?v=Ru54dxzCyD0&list=PL5q3E8eRUieVFeK1oLahJ8KONkAxDpqk2&index=6