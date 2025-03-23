# System Design

## Scale from Zero to Million Users:

**[Topic List↗︎](https://bytebytego.com/courses/system-design-interview/scale-from-zero-to-millions-of-users)**
1. Single Server Setup:
2. Database:
3. Vertical Scaling vs Horizontal Scaling:
    - Vertical: Adding more power (CPU, RAM, etc.), Horizontal: Adding more servers
4. Load Balancer:
    - User uses the public IP of the LB. LB uses private IP of each servers to decide which server it should send the request to.
5. Database Replication:
    - Master Slave, Multi-master, circular replication
6. Cache and Cache Tier:
    - Storage level between web-server(s) and database(s).
    - Need to understand few things: Decide when to use cache, Consistency, Expiration policy, Eviction Policy (Using LRU, LFU, FIFO, etc.), Mitigating failures (To avoid SPOF "Single Point Of Failure").
7. Content Delivery Network (CDN):
    - Cache the static contents like CSS, Javascript file, images, videos in Cache.
    - There is a concept of TTL (Time-To-Live) to determine how long the content is cached in the CDN.
    - CDNs are run by third-party providers, and you are charged for data transfers in
and out of the CDN
8. Stateless Web Tier:
    - No need to store state (For example: session data) on the web server. These details can either be stored at client-side storgae like Cookies, Can be sent to server via stateless Tokens like JSON Web Tokens (JWTs), Can be stored in an external database or a distributed cache like Redis.
    - NOTE: Go through Chat GPT question mentioned in the [reference](#reference) section to understand more about this topic.
9. Stateful Architecture:
    - A stateful server remembers client data (state) from one request to the next.
10. Data Centers: 
    - In normal operation, users are geoDNS-routed, also known as geo-routed, to the closest data center.
    - geoDNS is a DNS service that allows domain names to be resolved to IP addresses based on the location of a user.
11. Message Queue: [[2]](#reference)
12. Logging, metrics, automation
    - Logging: Monitoring error logs is important because it helps to identify errors and problems
in the system. We can monitor error logs at per server level or use tools to aggregate them to
a centralized service for easy search and viewing.
    - Metrics: Host Level, Aggregated Level, Key Business Metrics.
    - Automation

### Horizontal Scaling:

Sharding separates large databases into smaller, more easily managed parts called shards. Each shard shares the same schema, though the actual data on each shard is unique to the shard.

Anytime you access data, a hash function is used to find the corresponding shard. In our example, user id % 4 is used as the hash function.

The most important factor to consider when implementing a sharding strategy is the choice of the `sharding key`. Sharding key (known as a partition key) consists of `one or more columns` that determine how data is distributed.

Sharding is a great technique to scale the database but it is far from a perfect solution. It introduces complexities and new challenges to the system:
1. Resharding data
2. Celebrity Problem
3. Join and de-normalization

**Key points from this chapter:**
- Keep web tier stateless
- Build redundancy at every tier
- Cache data as much as you can
- Support multiple data centers
- Host static assets in CDN
- Scale your data tier by sharding
- Split tiers into individual services
- Monitor your system and use automation tools

## **Reference:**
1. [Chat GPT](https://chatgpt.com/c/67822b73-9530-8013-9c9f-8c529a047c8d) <br />
2. [Message Queue](https://www.geeksforgeeks.org/message-queues-system-design/)<br />

