# Miscellaneous

## Consul, etcd, Zookeeper for service discovery

| Feature | Consul | etcd | ZooKeeper |
|--------|--------|------|-----------|
| Primary Role | Service Discovery, Config, Service Mesh | Distributed Key-Value Store | Distributed Coordination |
| Service Discovery | Built-in (DNS/HTTP) | Not Built-in (Requires custom implementation) | Not Built-In (Requires custom implementation) |
| Consensus Algorithm | Raft | Raft | Atomic Broadcast (ZAB) |
| Data Model | Key-Value | Key-Value | Hierarchical Tree (Znodes) |
| Health Checking | Yes (Native) | No | No |
| Multi-Datacenter Support | Native Support | Limited / Complex | Limited / Complex |
| Typical Use Case | Microservices, Application Coordination | Kubernetes, Cloud-Native Metadata Storage | Hadoop, Java Distributed Systems |

## DNS

## CDN

### Pull CDN

### Push CDN

## Load Balancers

- Preventing requests from going to unhealthy servers
- Preventing overloading resources
- Helping to eliminate a single point of failure

Load balancers can be implemented with hardware (expensive) or with software such as HAProxy. Additional benefits include:
- SSL termination - Decrypt incoming requests and encrypt server responses so backend servers do not have to perform these potentially expensive operations
    - Removes the need to install X.509 certificates on each server
- Session persistence - Issue cookies and route a specific client's requests to same instance if the web apps do not keep track of sessions

