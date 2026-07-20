# Design Patterns LLD Cheatsheet — Problem Signal → Pattern

## Creational Patterns (object creation problems)

**Singleton** — *Signal: "only one instance should exist, globally accessible"*
- Examples: DB connection pool, Logger, Config manager, Cache manager
- Interview tell: "How would you design a Logger class used across the app?"
- Watch out: interviewers love asking about thread-safety (double-checked locking, enum singleton in Java)

**Factory Method** — *Signal: "create objects but let subclasses decide the exact type"*
- Examples: `NotificationFactory` returning Email/SMS/Push notification objects, `ShapeFactory`
- Interview tell: "Design a system that creates different payment methods based on input"

**Abstract Factory** — *Signal: "create families of related objects without specifying concrete classes"*
- Examples: UI toolkit (WindowsButton+WindowsCheckbox vs MacButton+MacCheckbox), cross-platform furniture sets (modern vs Victorian: chair+sofa+table)
- Interview tell: when you have *multiple* product lines that must stay consistent with each other

**Builder** — *Signal: "construct complex object step-by-step, many optional parameters"*
- Examples: building a `Pizza` with toppings, constructing an HTTP request, `StringBuilder`
- Interview tell: "Design a class with 10 optional fields without a constructor explosion"

**Prototype** — *Signal: "cloning is cheaper than constructing from scratch"*
- Examples: cloning a complex game character/object, copying a document template
- Interview tell: rare in interviews, but mention it if creation is expensive (e.g., DB hit needed to build)

## Structural Patterns (how objects/classes compose)

**Adapter** — *Signal: "two incompatible interfaces need to talk"*
- Examples: legacy payment gateway with old interface wrapped to match new `PaymentProcessor` interface, plugging a `XMLDataSource` into code expecting JSON
- Interview tell: "integrate this third-party library whose interface doesn't match ours"

**Decorator** — *Signal: "add responsibilities to an object dynamically without subclassing explosion"*
- Examples: coffee with milk/sugar/whip add-ons, Java's `BufferedReader(new FileReader())`, adding logging/caching/compression wrappers to a `DataSource`
- Interview tell: "design a system where pizza/coffee toppings each add cost" — classic decorator question

**Facade** — *Signal: "simplify a complex subsystem behind one clean interface"*
- Examples: `OrderService.placeOrder()` internally calling Inventory, Payment, Shipping, Notification services
- Interview tell: when the question is more about API design simplicity than core logic

**Composite** — *Signal: "tree structure, treat individual objects and groups uniformly"*
- Examples: File system (File/Folder both implement `FileSystemNode`), org hierarchy, UI components (Panel containing Buttons containing Panels)
- Interview tell: "design a file system" or "design an org chart" — composite, instantly

**Proxy** — *Signal: "control access to an object — lazy load, cache, permission check, remote call"*
- Examples: lazy-loading large images, access-control proxy before hitting real service, caching proxy
- Interview tell: "delay expensive object creation until actually needed"

**Bridge** — *Signal: "decouple abstraction from implementation so both vary independently"*
- Examples: `RemoteControl` (abstraction) working with any `Device` (implementation) — TV, Radio
- Interview tell: less common in interviews; mention when you have two *independent* dimensions of variation (e.g., shape × color)

**Flyweight** — *Signal: "huge number of objects, share common state to save memory"*
- Examples: character glyphs in a text editor, particle systems in games, tree objects in a forest simulation (shared mesh/texture, unique position)
- Interview tell: "millions of similar objects" is the giveaway word

## Behavioral Patterns (how objects interact / object behavior changes)

**Strategy** — *Signal: "interchangeable algorithms, pick one at runtime"*
- Examples: sorting strategies, payment methods (CreditCard/UPI/Wallet), route-calculation strategies in Maps
- Interview tell: by far the **most asked** pattern — "design a system supporting multiple payment/discount/compression algorithms"

**Observer** — *Signal: "one-to-many, dependents need notification on state change"*
- Examples: pub-sub systems, stock price tickers notifying subscribers, YouTube channel subscribers, event listeners in UI
- Interview tell: "design a notification system" or "design Twitter feed" — Observer almost always shows up

**Command** — *Signal: "encapsulate a request as an object — supports undo/redo, queuing, logging"*
- Examples: remote control buttons, text editor undo/redo stack, task queue/job scheduler
- Interview tell: "design an undo-redo feature" — instant Command pattern

**State** — *Signal: "object behavior changes based on internal state, avoid giant if-else"*
- Examples: traffic light (Red/Yellow/Green), order lifecycle (Placed→Shipped→Delivered), vending machine, media player (Playing/Paused/Stopped)
- Interview tell: "design a vending machine" or "design traffic light system" — classic State pattern questions

**Template Method** — *Signal: "same algorithm skeleton, steps vary in subclasses"*
- Examples: data parsers (CSV/JSON/XML share `parse()` flow but differ in `readData()`), report generation pipeline
- Interview tell: "build different report generators that follow the same steps but differ in details"

**Chain of Responsibility** — *Signal: "request passes through a chain of handlers until one handles it"*
- Examples: middleware chains (auth → logging → rate-limit), ATM cash dispenser (note denominations), support-ticket escalation (L1→L2→L3)
- Interview tell: "design an ATM that dispenses different denominations" or "design a logging/middleware pipeline"

**Mediator** — *Signal: "many objects communicate; centralize the communication"*
- Examples: chatroom (Mediator routes messages between Users), air traffic control coordinating planes
- Interview tell: "design a chat application" — Mediator over direct peer-to-peer references

**Visitor** — *Signal: "perform varying operations on a stable object structure without modifying those classes"*
- Examples: compiler AST traversal (different visitors for type-checking, code-gen), shopping cart applying tax/discount visitors to different item types
- Interview tell: rarer; mention when adding new *operations* is common but new *types* are rare

**Iterator** — *Signal: "traverse a collection without exposing internal structure"*
- Examples: custom collection classes, traversing a tree/graph
- Interview tell: usually assumed via language built-ins, but bring it up for custom data structures

**Memento** — *Signal: "capture/restore object state without exposing internals"*
- Examples: undo in text editors (often paired with Command), game save/checkpoint system
- Interview tell: "implement undo functionality that restores previous state" (often combined with Command)

## The fast mental shortcut for interviews

| If the question mentions... | Think... |
|---|---|
| "multiple algorithms / interchangeable behavior" | Strategy |
| "notify multiple parties on change" | Observer |
| "undo / redo" | Command (+ Memento) |
| "object behaves differently by state, avoid if-else hell" | State |
| "tree / hierarchy / nested groups" | Composite |
| "incompatible interfaces" | Adapter |
| "add features dynamically without subclass explosion" | Decorator |
| "only one instance" | Singleton |
| "complex object, many optional params" | Builder |
| "create object, type decided by input" | Factory Method |
| "simplify complex subsystem" | Facade |
| "chain of handlers / middleware" | Chain of Responsibility |
| "many objects talking to each other → centralize" | Mediator |
| "lazy load / access control / caching wrapper" | Proxy |
| "millions of similar objects, save memory" | Flyweight |
| "same steps, different sub-implementation" | Template Method |

## A few interview reality checks

1. **LLD questions are rarely single-pattern.** "Design a parking lot" often combines Strategy (pricing) + Factory (vehicle creation) + Observer (slot availability notification). Naming the *combination* impresses more than naming one pattern.
2. **Strategy, Observer, Factory, Singleton, Decorator, State** cover ~70% of what actually gets asked (payment systems, notification systems, vending machines, parking lots, elevators, splitwise, etc.). Master these deeply first.
3. Interviewers often want you to **derive** the pattern by first writing bad code (giant if-else, tight coupling) and then refactoring — not jump straight to naming it. Practice the "why" not just the "what."
