# SDE-2 Interview Cheat Sheet: Numbers Every Engineer Should Know

To sound like a senior engineer, you must justify your architectural choices with math. Always state your assumptions (e.g., "Assuming 100 bytes per row...") before performing these calculations.

---

## 1. The "Golden" Base Estimations
Memorize these three conversions to do mental math on the fly:
*   **1 Million rows × 1 KB** = **1 GB**
*   **1 Billion rows × 100 bytes** = **100 GB**
*   **1 Day** = **86,400 seconds** (Round to **100,000** for quick mental math)

---

## 2. Memory vs. Disk: The Thresholds
When an interviewer asks "Where do we store this?", use these numbers to decide:


| Scale | Storage Choice | Smart Justification |
| :--- | :--- | :--- |
| **< 64 GB** | **In-Memory (Redis/RAM)** | "This fits comfortably in RAM; we can prioritize sub-millisecond latency." |
| **64 GB - 2 TB** | **Single Node SSD** | "We can manage this on a single high-performance instance with SSD indexing." |
| **> 5 TB** | **Distributed (Sharded)** | "The dataset exceeds single-node reliability; we need to shard or use S3." |

---

## 3. Latency: Why Caching Matters
Compare these to explain *why* you are adding a cache or a CDN:

*   **L1 Cache Reference:** 0.5 ns
*   **Main Memory (RAM) Reference:** 100 ns
*   **Read 1 MB sequentially from RAM:** 250,000 ns (0.25 ms)
*   **Read 1 MB sequentially from SSD:** 1,000,000 ns (1 ms)
*   **Read 1 MB sequentially from HDD:** 20,000,000 ns (20 ms)
*   **Round trip within same Datacenter:** 500,000 ns (0.5 ms)
*   **Packet Round trip (US to Europe):** 150 ms

**The SDE-2 Pro Tip:** Mention that "Disk is roughly **10,000x slower** than RAM."

---

## 4. Traffic & Throughput (QPS)
Use these to determine if you need a Load Balancer or a Distributed Queue:

*   **Low Traffic:** < 500 QPS (One small app server handles this).
*   **Medium Traffic:** 500 - 2,000 QPS (Standard for a single robust service).
*   **High Traffic:** > 5,000 QPS (Requires horizontal scaling and load balancing).

**Mental Math Formula:**
`Requests per Day / 100,000 = Average QPS`  
*Example:* 100 Million requests/day = 1,000 QPS.

---

## 5. Storage Sizing for Common Objects
If they ask you to design Twitter or Instagram, use these averages:

*   **Simple Metadata/Row:** 100 - 500 bytes.
*   **Short Text (Tweet/SMS):** ~300 bytes.
*   **High-Quality Image:** 1 MB - 3 MB.
*   **1-Minute Video (720p):** ~15 MB - 20 MB.

---

## 6. Smart Phrases to Drop
*   *"At this scale, we are **I/O bound**, so adding more CPU won't help; we need to optimize our disk access patterns."*
*   *"Since our read-to-write ratio is **100:1**, we should use **read-replicas** to offload the primary database."*
*   *"We should account for **Peak Traffic**, which is usually **2x to 5x** the average QPS."*
*   *"If we use an **LRU eviction policy**, we can keep the 'hot' 20% of data in memory to serve 80% of requests (Pareto Principle)."*

---

## 7. Availability Cheat Sheet (The "Nines")
When discussing SLA or Uptime:
*   **Two 9s (99%):** ~3.6 days downtime/year.
*   **Three 9s (99.9%):** ~9 hours downtime/year.
*   **Four 9s (99.99%):** ~52 minutes downtime/year.
*   **Five 9s (99.999%):** ~5 minutes downtime/year.
