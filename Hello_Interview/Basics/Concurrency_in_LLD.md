# Concurrency

- [Correctness](#correctness)
- [Coordination](#coordination)
- [Scarcity](#Scarcity)

## Categories of Concurrency

1. Correctness
2. Coordination
3. Scarcity

### Correctness

Shared state gets corrupted because two threads access it at the same time

Pattern where this issue mostly occurs is **`Check-then-act`**. Check if the resource is available then act on it.
- The high level solution is that the check and the action both needs to happen together as one atomic operation.

```Java
public class BookingService {
    Map<String, Seat> seats;

    public boolean bookSeat(String seatId, String visitorId) {
        Seat seat = seats.get(seatId);
        synchronized (lock) {
            if(seat.isAvailable()) {
                seat.setOccupant(visitorId);
                return true;
            }
            return false;
        }
    }
}
```

Second most common correctness pattern that shows up is **`read-modify-write`**.
- Make the whole operation atomic

```Java
private int count = 0;
private AtomicInteger atomicCount = new AtomicInteger(3);

public void increament() {
    count++; // If multiple threads access this at the same time, there are chances that the result would be incorrect.

    atomicCount.increamentAndGet(); // Atomic operation at hardware level.
}

```

### Coordination

**Scenario:** User sign-ups and we send a welcome email. Suppose welcome email takes 500ms. We don't want user to wait for that time before going forward.
- How to solve it? Assign heavy non concerning tasks to task workers. Populate the request to a message queue and move forward. The worker will pick up the task from the queue and process it in background.

**Challanges in above:** 
1. How does a consumer know that work arrived?

```Java
// Bad: Busy waiting burns CPU
while(true) {
    if(!queue.empty()) {
        Task task = queue.poll();
        process(task);
    }

    // To avoid busy waiting, we can introduce sleep if queue is empty
    // This is also not the optimal solution as sleep introduces latency
    else {
        Thread.sleep(100);
    }

    // We want the workers to sleep when there is no work and wake up instantly the moment that work arrives
    // Solution is to use Blocking queue.
    BlockingQueue<Task> queue = new LinkedBlockingQueue<>();

    // Producer API handler
    public void handleSignUp(User user) {
        saveUser(user);
        queue.put(new EmailTask(user)); // Blocks if queue is full
        return success();        
    }

    // Consumer worker thread
    while(true) {
        Task task = queue.take(); // Blocks if queue is empty, zero CPU
        processTask(task);
    }
}
```

2. What happens if work is arriving too fast for the worker to keep up?
    - Solution: **`Bounded blocking queue`**
    - Set a maximum size of the queue
    - When queue is full, producers are blocked until there is room, this is called **`back pressure`**.

```Java
BlockingQueue<Task> queue = new LinkedBlockingQueue<>(1000);
```

### Scarcity

Limit concurrent access to finite resources.

Solution: **`Semaphores`**. Semaphore in an operating system is a synchronization tool—specifically a non-negative integer variable—used to **manage concurrent access to shared resources by multiple processes**.

```Java
Semaphore permits = new Semaphore(5);

public void download(String url) {
    permits.acquire(); // Grab a permit, blocks if non available
    try {
        doDownload(url);
    } catch(Exception e) { } 
    finally {
        permits.release(); // put it back
    }

    // If we don't wrap processing block in try-catch, and,
    // If doDownload throws an exception, we would get out of the method and we would never release the permit
}
```

When managing actual objects with state (eg. Database Connection), create a pool with blocking queue

```Java
// Initialize pool with connection
BlockingQueue<Connection> pool = new BlockingQueue<>(1000);
for(int i=0;i<10;++i) {
    pool.put(createConnection());
}

// Usage
public Result query(String sql) {
    Connection conn = pool.take(); // Blocks if queue is empty
    try {
        // Why we put connections in queue?
        // If we try to build connection everytime here, it would not be efficient
        return conn.execute(sql);
    } finally {
        pool.put(conn);
    }
}
```

## When Concurrency comes up in interview:

State Check:
- Is there **`shared state`** that multiple threads can access?
    - If yes, then we are talking about correctness issue: Locks, Atomic Variables
- Is work flowing from one thread to another (**`Work handoff`**)?
    - Coordination: Blocking Queues (Bounded BQ is preferred)
- If there is a fixed limit of something (**`Limited Resources`**)?
    - Scarcity: Semaphores or Blocking Queues (BQ in case of Managing objects with state)