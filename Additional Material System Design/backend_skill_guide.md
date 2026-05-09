# Backend Engineer Skill Upgrade Guide (2.5 YOE)

---

# Topics to Master (Beyond CRUD)

At this stage, backend engineers are expected to go beyond CRUD and understand:

- System Thinking (scalability, failure handling, reliability)
- Database Internals & Optimization
- API Design & Contracts
- Concurrency & Async Programming
- Basic System Design
- Messaging & Event-Driven Systems
- Observability & Debugging
- Code Quality & Design (OOPS, SOLID, Patterns)
- Deployment Basics (CI/CD, Docker)
- Ownership Mindset

---

# Skill Audit (Questions + Your Answers + Correct Answers + Gaps)

---

## Section A: Backend Fundamentals

### Q1: API latency increases from 100ms → 2s. How do you debug?

Your Answer:
- Check health of servers
- Check gateway logs and routing
- Check request distribution
- Analyze logs with timestamps

Correct Answer:
Total latency = network + load balancer + application + DB + downstream services

Steps:
- Check CPU, memory, thread usage
- Verify load balancer distribution
- Analyze logs with timestamps
- Identify bottleneck:
  - DB latency
  - External services
  - Thread pool exhaustion
  - GC pauses
  - Network latency

Gap:
- Missing structured breakdown

---

### Q2: What happens if request fails at DB level?

Your Answer:
- Check DB server
- Check replicas
- Promote slave if master fails

Correct Answer:
- Check DB health
- Verify master availability
- Check replication
- Ensure failover works
- Investigate:
  - connection pool exhaustion
  - slow queries
  - locks/deadlocks

Gap:
- Missing connection pool awareness

---

### Q3: What is idempotency?

Your Answer:
- Same request returns similar output

Correct Answer:
Multiple identical requests produce the same effect (no duplicate side effects)

Examples:
- Payment should not happen twice
- Order should not duplicate

Implementation:
- Idempotency keys
- Unique request IDs

Gap:
- Missing side-effect concept

---

## Section B: Database

### Q1: Optimize slow query

SELECT * FROM orders WHERE user_id = 123;

Your Answer:
- Create index
- Use sharding

Correct Answer:
- Check index
- Use EXPLAIN
- Avoid SELECT *
- Use covering index
- Consider caching

Gap:
- Jumped to sharding too early

---

### Q2: What is an index?

Your Answer:
- Mapping for faster access

Correct Answer:
- Data structure (B-Tree)
- Faster reads, slower writes

Gap:
- Missing internal understanding

---

### Q3: Read Replica vs Sharding

Your Answer:
- Replication correct

Correct Answer:
Read Replica:
- Master writes, replicas read

Sharding:
- Split data horizontally

Gap:
- Missing sharding clarity

---

## Section C: API Design

### Q1: Create Order API

Your Answer:
- POST API
- Response 200

Correct Answer:
POST /orders

Responses:
- 201 Created
- 400 Bad Request
- 401 Unauthorized
- 409 Conflict
- 500 Internal Server Error

Gap:
- Incorrect status codes

---

### Q2: PUT vs PATCH

Correct Answer:
PUT = full replace
PATCH = partial update

---

## Section D: Concurrency

### Q1: Thread Pool

Your Answer:
- Place where threads exist

Correct Answer:
- Fixed reusable threads

---

### Q2: Why Thread Pool?

Correct Answer:
- Control concurrency
- Avoid overhead

---

### Q3: Unlimited threads?

Correct Answer:
- Memory exhaustion
- CPU thrashing

---

## Section E: System Design

### Q1: Rate Limiter

Correct Answer:
- Token bucket / leaky bucket
- Reject excess requests

---

## Section F: Production Debugging

### Q1: Error spike

Correct Answer:
- Check logs
- Check metrics

---

### Q2: Debugging approach

Correct Answer:
- Logs → trace → isolate → fix

---

# Final Goal

From:
Engineer who implements

To:
Engineer who designs and owns systems
