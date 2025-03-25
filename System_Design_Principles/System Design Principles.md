# System Design Principles:

1. [SOLID Principles](#solid-principles)
2. [DRY Principle](#dry-principle)

## SOLID Principles:

### S: Single Responsibility Principle (SRP):

A class should have one, and only one, reason to change. 

[Code](./Single_Responsibility_Principle/SingleResponsibilityPrinciple.java)

### O: Open/Close Principle (OCP):

Software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification. 

[Without applying OCP](./Open_Close_Principle/BeforeOCP.java) | [With OCP](./Open_Close_Principle/AfterOCP.java)

### L: Liskov Substitution Principle (LSP):

Objects of a superclass should be replaceable with objects of its subclasses without affecting the correctness of the program.

[Code](./Liskov_Substitution_Principle/LiskovSubstitutionPrinciple.java)

### I: Interface Segregation Principle (ISP):

No client should be forced to depend on interfaces they don't use.

[Code](./Interface_Segregation_Principle/InterfaceSegregationPriciple.java)

### D: Dependency Inversion Principle (DIP):

High-level modules should not depend on low-level modules; both should depend on abstractions.

[Without Applying DIP](./Dependeny_Inversion_Principle/BeforeDIP.java) | [With DIP](./Dependeny_Inversion_Principle/AfterDIP.java)

## DRY Principle:

Don't Repeat Yourself: "Every piece of knowledge must have a single, unambiguous, authoritative representation within a system." [[3](#references)]

[Code](./DRY_Principle/DRY.java)

## YAGNI:

You Aren't Gonna Need It: Always implement things when you actually need them, never when you just foresee that you might need them.


## References:

[1] [SOLID Priciples details](https://medium.com/backticks-tildes/the-s-o-l-i-d-principles-in-pictures-b34ce2f1e898) <br />
[2] [SOLID Principles with Code](https://blog.algomaster.io/p/solid-principles-explained-with-code) <br />
[3] [DRY Principles](https://blog.algomaster.io/p/082450d8-0e7b-4447-a8dc-b7308e45f048) <br />
[4] [Design Patterns](https://refactoring.guru/design-patterns) <br />