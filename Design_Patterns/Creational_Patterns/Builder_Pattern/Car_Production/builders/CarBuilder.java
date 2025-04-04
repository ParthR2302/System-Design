package Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.builders;

import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.cars.Car;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.cars.CarType;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.components.Engine;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.components.GPSNavigator;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.components.Transmission;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.components.TripComputer;

// Builder of Car
public class CarBuilder implements Builder {
    private CarType type;
    private int seats;
    private Engine engine;
    private Transmission transmission;
    private TripComputer tripComputer;
    private GPSNavigator gpsNavigator;

    public void setCarType(CarType type) {
        this.type = type;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public void setTripComputer(TripComputer tripComputer) {
        this.tripComputer = tripComputer;
    }

    public void setGPSNavigator(GPSNavigator gpsNavigator) {
        this.gpsNavigator = gpsNavigator;
    }

    public Car getResult() {
        return new Car(type, seats, engine, transmission, tripComputer, gpsNavigator);
    }
}
