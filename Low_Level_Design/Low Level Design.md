# Low Level Design

- [Overview](#overview)


## Overview

It lies beyween High Level Design and Actual Code.

Low Level Design (LLD) focuses on translating high-level system architecture into detailed class-level designs, including classes, methods, data structures, and interactions between objects.

It emphasizes **object-oriented principles, design patterns, and clear APIs** to ensure the system is scalable, maintainable, and easy to implement.

**`Categories:`** Creational, Structural, Behavioural

### Creational:

It controls the object creation.
- Singleton, Builder, Factory, Abstract Factory, Object Pool, Prototype


### Structural:

It focuses on how different classes/objects are arranged together so that larger problems can be solved in the most flexible way.
- Decorator, Proxy, Composite, Adapter, Bridge, Facade, Flyweight

### Behavioural:

It focuses on how different objects communicate/interact with each other.

How the skaleton (which is created by the structural pattern) behaves (coordination, responsibility, interaction) is all guided by the behavioural patterns.

- State, Strategy, Observer, Chain of Responsibility, Template, Iterator, Interpreter, Command, Visitor, Mediator, Memento, Null Object