package Design_Patterns.Creational_Patterns.Factory_Pattern;

/*
 * - Product Interface
 * - Concrete Products: Car, Bike, Truck
 * - VehicleFactory Interface (Creator Interface)
 * - Concrete Factories: CarFactory, BikeFactory, TruckFactory
 * --> If in future a new type of Vehicle comes we will only need to implement the Concrete class for that Vehicle and a dedicated VehicleFactory class for that type of vehicle without touching any other part of the code and then we will be able to use that vehicle in our code.
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

public class VehicleFactoryPattern {
    public static void main(String[] args) {
        VehicleFactory carFactory = new CarFactory();
        Vehicle car = carFactory.getVehicle();
        car.create();

        VehicleFactory bikeFactory = new BikeFactory();
        Vehicle bike = bikeFactory.getVehicle();
        bike.create();

        VehicleFactory truckFactory = new TruckFactory();
        Truck truck = (Truck)truckFactory.getVehicle();
        truck.create();
        truck.independentTruckMethod();
    }
}
