package System_Design_Principles.Liskov_Substitution_Principle;

// Before Applying LSP
class Vehicle1 {
    public void engine_start() {}
}

class Car1 extends Vehicle1 {
    @Override
    public void engine_start() {
        System.out.println("Engine start method of Car class");
    }
}

class Bicycle1 extends Vehicle1 {
    @Override
    public void engine_start() {
        // There is no sense in giving engine_start method to bicycle
    }
}

// After applying LSP
class Vehicle2 {
    public void start() {}
}

class Car2 extends Vehicle2 {
    @Override
    public void start() {
        System.out.println("Start method of Car class");
    }
}

class Bicycle2 extends Vehicle2 {
    @Override
    public void start() {
        System.out.println("Start method of Bicycle class - User is pedaling the bicycle");
    }
}

public class LiskovSubstitutionPrinciple {
    public static void main(String[] args) {
        Car1 car1 = new Car1();
        Bicycle1 bicycle1 = new Bicycle1();

        car1.engine_start();
        bicycle1.engine_start(); // There is no sense in giving engine start method to bicycle

        Car2 car2 = new Car2();
        Bicycle2 bicycle2 = new Bicycle2();

        car2.start();
        bicycle2.start();
    }
}
