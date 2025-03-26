# Design Patterns:

1. [Details](#details)
2. [Creational Design Patterns](#creational-design-patterns)
    1. [Factory Method](#factory-method)
    2. [Abstract Factory](#abstract-factory)

## Details:

Design patterns are typical solutions to commonly occurring problems in software design. They are like pre-made blueprints that you can customize to solve a recurring design problem in your code.

The pattern is not a specific piece of code, but a general concept for solving a particular problem.

**Classificaiton of Patterns:**

The most basic and low-level patterns are often called `idioms`. They usually apply only to a single programming language.

The most universal and high-level patterns are `architectural patterns`. Developers can implement these patterns in virtually any language. Unlike other patterns, they can be used to design the architecture of an entire application.

Three main groups of patterns:
1. **Creational patterns** provide object creation mechanisms that increase flexibility and reuse of existing code.
2. **Structural patterns** explain how to assemble objects and classes into larger structures, while keeping these structures flexible and efficient.
3. **Behavioral patterns** take care of effective communication and the assignment of responsibilities between objects.

## Creational Design Patterns:

List of Creational Design Patterns: Factory, Abstract Factory, Builder, Prototype and Singleton.

### Factory Method:

[Code](./Creational_Patterns/Factory_Pattern/FactoryPattern.java)

Provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created.

**Key Components**
1. `Product` (Interface/Abstract Class) – Defines the type of object that will be created.
2. `Concrete Product` (Classes that Implement Product) – The actual classes that will be instantiated.
3. `Factory` (Creates Concrete Product) – Decides which object to instantiate based on input conditions.

**When to use?**
- Use the Factory Method when you don’t know beforehand the exact types and dependencies of the objects your code should work with.


### Abstract Factory:

[Code](./Creational_Patterns/Abstract_Factory/AbstractFactory.java) [[2](#references)]

`Abstract Factory` is a creational design pattern that lets you produce families of related objects without specifying their concrete classes.

**Key Components;**
1. `Abstract Factory` (Interface for Factories) – Declares methods for creating product families.
2. `Concrete Factories` (Implement Abstract Factory) – Create specific families of products.
3. `Abstract Product` (Interface for Objects) – Declares methods for different product types.
4. `Concrete Products` (Implement Abstract Product) – Actual implementations of products.
5. `Client` (Uses the Factory) – Uses abstract interfaces, does not depend on specific classes.

**When to Use?**
- When you need to create multiple families of related objects.
- When you want to ensure that objects from different families are used together.
- When the system should be independent of how objects are created and composed.






















## References:

[1] [Design Patterns](https://refactoring.guru/design-patterns) <br />
[2] [Abstract Factory Code](https://refactoring.guru/design-patterns/abstract-factory/java/example#example-0) <br />