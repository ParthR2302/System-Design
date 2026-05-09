# Core Prerequisites Before Starting LLD and HLD Practice

The goal is **not** to master everything before touching design problems.  
The goal is to know enough core concepts so design questions actually make sense.

---

# Low-Level Design (LLD) Prerequisites

LLD is about designing classes, objects, interactions, maintainable code, and clean architecture.

Think: **"How do I design the internals of a system?"**

## 1. Object-Oriented Programming (Must Know)

This is the absolute foundation.

### Core Concepts
- Classes vs Objects
- Encapsulation
- Abstraction
- Inheritance
- Polymorphism
- Composition vs Inheritance (**very important**)
- Association / Aggregation / Composition
- Interfaces
- Abstract Classes
- Method Overriding
- Method Overloading
- Access Modifiers
- Static vs Instance members
- Constructors / Destructor basics

### Why it matters
Almost every LLD interview problem is solved using object modeling.

Example:
- Parking Lot
- Elevator
- ATM
- Splitwise
- Library Management

Without OOP, LLD becomes memorization.

---

## 2. SOLID Principles (Must Know)

Core design principles for maintainable code.

- Single Responsibility Principle
- Open/Closed Principle
- Liskov Substitution Principle
- Interface Segregation Principle
- Dependency Inversion Principle

### Why it matters
Interviewers look for maintainability, extensibility, and clean separation.

Bad LLD:
One god class doing everything.

Good LLD:
Responsibilities separated cleanly.

---

## 3. Object Relationships & Modeling

Need to learn how to convert requirements into classes.

Topics:
- Identify entities
- Identify responsibilities
- Relationships between objects
- Has-a vs Is-a
- Aggregation vs Composition
- Cardinality (one-to-one, one-to-many, many-to-many)

### Why it matters
This is the heart of LLD.

---

## 4. UML Basics (Very Helpful)

You do NOT need enterprise-level UML mastery.

Learn:
- Class diagrams
- Sequence diagrams
- Use case diagrams (basic understanding)

### Why it matters
Helps communicate design clearly.

---

## 5. Design Patterns (High ROI)

This is where many candidates struggle.

### Creational
- Singleton
- Factory Method
- Abstract Factory
- Builder
- Prototype (optional initially)

### Structural
- Adapter
- Decorator
- Facade
- Composite
- Proxy

### Behavioral
- Strategy
- Observer
- State
- Command
- Template Method
- Chain of Responsibility
- Iterator

### Why it matters
Interviewers expect pattern recognition.

Example:
- Observer → Notifications
- Strategy → Payment methods
- State → Vending machine
- Factory → Object creation

---

## 6. Clean Code Principles

Topics:
- Naming conventions
- Small functions
- Separation of concerns
- Avoid tight coupling
- High cohesion
- Low coupling
- DRY
- KISS
- YAGNI

### Why it matters
LLD is not just "making classes."

It's writing maintainable software.

---

## 7. Basic Exception Handling & Validation Design

Topics:
- Custom exceptions
- Validation layers
- Error handling strategies

### Why it matters
Interview solutions often ignore failure cases.

Strong candidates don't.

---

## 8. Dependency Injection (Very Useful)

Learn:
- What dependency injection is
- Constructor injection
- Why tight coupling is bad

### Why it matters
Common in modern backend systems.

---

## 9. Interfaces & Contract-Driven Design

Topics:
- Program to interfaces
- Dependency inversion
- Extensibility via contracts

### Why it matters
Helps future-proof designs.

---

## 10. Basic Concurrency Concepts (Helpful)

Not mandatory for beginner LLD.

Topics:
- Threads basics
- Race conditions
- Synchronization
- Locks / mutex
- Thread-safe design

### Why it matters
Needed for:
- Logger
- Rate limiter
- Producer-consumer
- Elevator

---

## 11. Collections / Data Structures Knowledge

Must know practical usage of:
- HashMap
- HashSet
- Queue
- Stack
- Priority Queue / Heap
- List / ArrayList
- LinkedList basics

### Why it matters
LLD designs often need internal storage choices.

---

## LLD Learning Priority

### Start immediately with:
1. OOP
2. SOLID
3. Object Modeling
4. UML basics
5. Clean Code

### Learn alongside practice:
6. Design Patterns
7. Dependency Injection
8. Interfaces
9. Exception handling

### Later:
10. Concurrency

---

# High-Level Design (HLD) Prerequisites

HLD is about architecture, scalability, reliability, distributed systems.

Think: **"How do I design the whole system?"**

---

## 1. Networking Fundamentals (Must Know)

Core topics:
- HTTP / HTTPS
- TCP vs UDP
- DNS
- TLS / SSL basics
- REST APIs
- gRPC / RPC basics
- WebSockets basics
- Load balancer basics
- Reverse proxy basics

### Why it matters
Distributed systems communicate over networks.

---

## 2. Databases Fundamentals (Must Know)

### Relational DB
Topics:
- Tables
- Indexes
- Primary key
- Foreign key
- Joins
- Transactions
- ACID
- Normalization basics

### NoSQL
Topics:
- Key-value
- Document DB
- Wide-column
- Graph DB

### Why it matters
Storage design is central to HLD.

---

## 3. Caching Fundamentals (Must Know)

Topics:
- Why cache exists
- Cache-aside
- Write-through
- Write-back
- Write-around
- TTL
- Cache invalidation
- Cache eviction (LRU/LFU)

### Why it matters
Almost every scalable system uses caching.

---

## 4. Scalability Basics (Must Know)

Topics:
- Vertical scaling
- Horizontal scaling
- Stateless services
- Bottlenecks
- Throughput
- Latency
- SPOF
- Capacity estimation basics

### Why it matters
Core HLD thinking.

---

## 5. Load Balancing

Topics:
- Why load balancing
- Round robin
- Least connections
- Health checks
- Sticky sessions

### Why it matters
Traffic distribution is fundamental.

---

## 6. Distributed Systems Fundamentals (Very Important)

Topics:
- Replication
- Sharding / partitioning
- Consistent hashing
- Leader-follower
- Quorum
- Consensus basics
- CAP theorem
- Eventual consistency
- Strong consistency

### Why it matters
This is the heart of HLD.

---

## 7. Messaging / Async Communication

Topics:
- Message queues
- Pub-sub
- Event-driven architecture
- Producers / consumers
- Retry handling
- Dead-letter queue
- Idempotency

### Why it matters
Scalable systems are asynchronous.

---

## 8. API Design Basics

Topics:
- REST design
- Resource modeling
- Pagination
- Filtering
- Versioning
- Rate limiting
- Idempotency
- Authentication basics

### Why it matters
System boundaries are APIs.

---

## 9. Reliability Concepts

Topics:
- Retries
- Timeouts
- Circuit breaker
- Failover
- Redundancy
- Graceful degradation
- Health checks
- Backpressure

### Why it matters
Real systems fail.

---

## 10. Observability Basics

Topics:
- Logging
- Metrics
- Monitoring
- Tracing
- Alerting

### Why it matters
Production systems need visibility.

---

## 11. Security Basics

Topics:
- Authentication
- Authorization
- Sessions
- JWT basics
- API keys
- Rate limiting
- Encryption in transit
- Encryption at rest

### Why it matters
Interviewers increasingly ask security tradeoffs.

---

## 12. Storage Systems Awareness

Need basic awareness of:
- Redis
- PostgreSQL / MySQL
- Cassandra
- DynamoDB
- Elasticsearch
- CDN basics
- Blob/object storage

### Why it matters
Tool selection matters in HLD.

---

## 13. System Design Estimation

Topics:
- QPS estimation
- Storage estimation
- Bandwidth estimation
- Memory estimation

### Why it matters
HLD interviews often start here.

---

## 14. Consistency & Transactions

Topics:
- ACID
- BASE
- Distributed transactions basics
- Saga pattern
- Two-phase commit (basic awareness)

### Why it matters
Critical in real systems.

---

# HLD Learning Priority

## Start immediately with:
1. Networking
2. DB fundamentals
3. Caching
4. Scalability
5. API design

## Then:
6. Distributed systems
7. Messaging
8. Reliability
9. Estimation

## Then:
10. Security
11. Observability
12. Advanced consistency

---

# Practical Advice

Do NOT wait to "finish prerequisites."

Best approach:

For LLD:
- Learn basics for 1–2 weeks
- Start solving design problems immediately

For HLD:
- Learn foundations in parallel
- Start simple design problems early:
  - URL shortener
  - Rate limiter
  - Tiny pastebin
  - Notification service

Learning only theory causes slow progress.