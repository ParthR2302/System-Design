# Detailed Notes on Basics

## Caching:

A cache is a temporary storage that keeps recently or frequently accessed data in memory so that future requests can be served faster, reducing latency and backend load.

### Where to Cache?

---

- [External Caching](#1-external-caching-distributed-cache)
- [In-Process Caching](#2-in-process-caching-local-cache)
- [CDN](#3-cdn-content-delivery-network)
- [Client-Side Caching](#4-client-side-caching)


### 1. External Caching (Distributed Cache)

**Examples:** Redis, Memcached, Hazelcast

- Uses the **Cache-Aside (Lazy Loading)** pattern  
- On `cache hit`: data is returned directly from the cache  
- On `cache miss`: data is fetched from DB and then stored in the cache  
- Cache lives outside the application process  
- Multiple application servers can share the same cache  

**Additional Characteristics**
- Supports TTL (time-based expiration)
- Eviction policies like LRU / LFU
- High availability and replication

**Pros**
- Shared across servers
- Reduces database load
- Centralized cache management
- Works well with horizontal scaling

**Cons**
- Network latency compared to local cache
- Cache consistency is harder to manage
- Requires additional infrastructure and operations

**When to Use**
- Multiple application servers
- Read-heavy workloads
- Database is a bottleneck
- Eventual consistency is acceptable

**Typical Use Cases**
- User profiles
- Product catalogs
- Session storage
- Feature flags
- Rate limiting

---

### 2. In-Process Caching (Local Cache)

**Examples:** HashMap, Caffeine, Guava Cache

- Cache is stored inside the application memory
- Fastest form of caching (no network calls)
- Each server maintains its own cache

**Additional Characteristics**
- Cache is lost on application restart or crash
- Data can become inconsistent across servers
- Memory usage grows with traffic

**Pros**
- Extremely low latency
- Simple to implement
- No external dependency

**Cons**
- Not shared across servers
- Hard to invalidate globally
- Risk of stale data

**When to Use**
- Single server or small-scale systems
- Data changes infrequently
- Slight staleness is acceptable
- Ultra-low latency is required

**Typical Use Cases**
- Configuration values
- Static reference data
- Enum-like database tables
- Hot computations

---

### 3. CDN (Content Delivery Network)

**Examples:** CloudFront, Cloudflare, Akamai

- Geographically distributed servers cache content closer to users
- Primary goal is reducing network latency
- Sits in front of backend services
- Uses HTTP caching headers like `Cache-Control` and `ETag`

**Pros**
- Massive latency reduction
- Offloads backend traffic
- Handles traffic spikes efficiently
- Highly scalable

**Cons**
- Cache invalidation is slow
- Not suitable for private or personalized data
- Less control compared to application-level caching

**When to Use**
- Users are globally distributed
- Data is public or semi-public
- Backend load needs to be reduced

**Typical Use Cases**
- Images, videos, CSS, JavaScript
- Public product pages
- Landing pages
- Public GET APIs

---

### 4. Client-Side Caching

**Examples:** Browser cache, LocalStorage, IndexedDB

- Data is stored on the user's device (browser or mobile app)
- Controlled by HTTP headers or application logic
- Data is usually user-specific

**Pros**
- Zero backend cost
- Instant responses
- Reduces repeated network calls

**Cons**
- Limited storage capacity
- Hard to invalidate reliably
- Security and privacy risks for sensitive data

**When to Use**
- Data is user-specific
- Data does not change frequently
- Offline access is required

**Typical Use Cases**
- UI preferences
- Recently viewed items
- Form drafts
- Authentication tokens (with care)
