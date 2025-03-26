package Design_Patterns.Creational_Patterns.Factory_Pattern;

/*
 * - Product Interface
 * - Concrete Products: Car, Bike, Truck
 * - Factory Interface (Creator Interface)
 * - Concrete Factories: CarFactory, BikeFactory, TruckFactory
 * --> If in future a new type of Vehicle comes we will only need to implement the Concrete class for that Vehicle and a dedicated factory class for that type of vehicle without touching any other part of the code and then we will be able to use that vehicle in our code.
*/

// Product Interface
interface Vehicle {
    void create();
}

// Concrete Product: Car
class Car implements Vehicle {
    public void create() {
        System.out.println("Car Created");
    }
}

// Concrete Product: Bike
class Bike implements Vehicle {
    public void create() {
        System.out.println("Bike Created");
    }
}

// Concrete Product: Truck
class Truck implements Vehicle {
    public void create() {
        System.out.println("Truck Created");
    }

    public void independentTruckMethod() {
        System.out.println("This method of Truck is not inherited from the super class/interface");
    }
}

// Factory Interface (Creator Interface)
interface Factory {
    Vehicle getVehicle();
}

// Concrete Factories
class CarFactory implements Factory {
    public Vehicle getVehicle() {
        return new Car();
    }
}

class BikeFactory implements Factory {
    public Vehicle getVehicle() {
        return new Bike();
    }
}

class TruckFactory implements Factory {
    public Vehicle getVehicle() {
        return new Truck();
    }
}

public class FactoryPattern {
    public static void main(String[] args) {
        Factory carFactory = new CarFactory();
        Vehicle car = carFactory.getVehicle();
        car.create();

        Factory bikeFactory = new BikeFactory();
        Vehicle bike = bikeFactory.getVehicle();
        bike.create();

        Factory truckFactory = new TruckFactory();
        Truck truck = (Truck)truckFactory.getVehicle();
        truck.create();
        truck.independentTruckMethod();
    }
}
