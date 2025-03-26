package Design_Patterns.Creational_Patterns.Abstract_Factory;

/*
 * - Abstract Products
 * - Concrete Products: ElectricCar, PetrolCar, ElectricBike, PetrolBike
 * - Factory Interface (Creator Interface) - VehicleFactory
 * - Concrete Factories: ElectricVehicleFactory, PetrolVehicleFactory
*/

// Abstract Products - Car, Bike
interface Car {
    void assemble();
}
interface Bike {
    void assemble();
}

// Concrete Products - ElectricCar, PetrolCar, ElectricBike, PetrolBike
class ElectricCar implements Car {
    public void assemble() {
        System.out.println("Electric Car Assembled!");
    }
}
class PetrolCar implements Car {
    public void assemble() {
        System.out.println("Petrol Car Assembled!");
    }
}
class ElectricBike implements Bike {
    public void assemble() {
        System.out.println("Electric Bike Assembled!");
    }
}
class PetrolBike implements Bike {
    public void assemble() {
        System.out.println("Petrol Bike Assembled!");
    }
}

// Abstract Factory
interface VehicleFactory {
    Car createCar();
    Bike createBike();
}

// Concrete Factory: ElectricVehicleFactory, PetrolVehicleFactory
class ElectricVehicleFactory implements VehicleFactory {
    public Car createCar() {
        return new ElectricCar();
    }
    public Bike createBike() {
        return new ElectricBike();
    }
}
class PetrolVehicleFactory implements VehicleFactory {
    public Car createCar() {
        return new PetrolCar();
    }
    public Bike createBike() {
        return new PetrolBike();
    }
}

public class AbstractFactory {
    public static void main(String[] args) {
        VehicleFactory electricVehicleFactory = new ElectricVehicleFactory();
        Car electricCar = electricVehicleFactory.createCar();
        Bike electricBike = electricVehicleFactory.createBike();
        electricCar.assemble();
        electricBike.assemble();

        VehicleFactory petrolVehicleFactory = new PetrolVehicleFactory();
        Car petrolCar = petrolVehicleFactory.createCar();
        Bike PetrolBike = petrolVehicleFactory.createBike();
        petrolCar.assemble();
        PetrolBike.assemble();
    }
}
