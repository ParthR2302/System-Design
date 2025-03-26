package Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.components;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.cars.Car;

// Product Feature-4
public class TripComputer {
    private Car car;

    public void setCar(Car car) {
        this.car = car;
    }

    public void showFuel() {
        System.out.println("Fuel Level: " + car.getFuel());
    }

    public void showStatus() {
        if (this.car.getEngine().isStarted()) {
            System.out.println("Car is started");
        } else {
            System.out.println("Car isn't started");
        }
    }
}
