package Design_Patterns.Creational_Patterns.Factory_Pattern;

/*
 * - Product Interface
 * - Concrete Products: Car, Bike, Truck
 * - VehicleFactory Interface (Creator Interface)
 * - Concrete Factories: CarFactory, BikeFactory, TruckFactory
 * --> If in future a new type of Vehicle comes we will only need to implement the Concrete class for that Vehicle and a dedicated VehicleFactory class for that type of vehicle without touching any other part of the code and then we will be able to use that vehicle in our code.
 * 
 * Below is Factory method pattern implementation in Java.
*/

// Product Interface
interface Vehicle {
    void drive();
}

// Concrete Product: Car
class Car implements Vehicle {
    public void drive() {
        System.out.println("Car is driving");
    }
}

// Concrete Product: Bike
class Bike implements Vehicle {
    public void drive() {
        System.out.println("Bike is driving");
    }
}

// Concrete Product: Truck
class Truck implements Vehicle {
    public void drive() {
        System.out.println("Truck is driving");
    }

    public void independentTruckMethod() {
        System.out.println("This method of Truck is not inherited from the super class/interface");
    }
}

// VehicleFactory Interface (Creator Interface)
interface VehicleFactory {
    Vehicle getVehicle();
}

// Concrete Factories
class CarFactory implements VehicleFactory {
    public Vehicle getVehicle() {
        return new Car();
    }
}

class BikeFactory implements VehicleFactory {
    public Vehicle getVehicle() {
        return new Bike();
    }
}

class TruckFactory implements VehicleFactory {
    public Vehicle getVehicle() {
        return new Truck();
    }
}

// We can directly use the factories to get the vehicle objects 
// Or we can have a FactoryProducer class to get the factories based on some input
class FactoryProducer {
    public static Vehicle getFactory(String factoryType) {
        if (factoryType.equalsIgnoreCase("Car")) {
            return new CarFactory().getVehicle();
        } else if (factoryType.equalsIgnoreCase("Bike")) {
            return new BikeFactory().getVehicle();
        } else if (factoryType.equalsIgnoreCase("Truck")) {
            return new TruckFactory().getVehicle();
        }
        return null;
    }
}

public class VehicleFactoryPattern {
    public static void main(String[] args) {
        
        // Car is created directly using CarFactory
        VehicleFactory carFactory = new CarFactory();
        Vehicle car = carFactory.getVehicle();
        car.drive();

        // Bike is created using FactoryProducer with Passing type of vehicle variable
        Vehicle bike = FactoryProducer.getFactory("Bike");
        bike.drive();

        VehicleFactory truckFactory = new TruckFactory();
        Truck truck = (Truck)truckFactory.getVehicle();
        truck.drive();
        truck.independentTruckMethod();
    }
}
