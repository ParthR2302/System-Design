package Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.cars;

import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.components.Engine;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.components.GPSNavigator;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.components.Transmission;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.components.TripComputer;

// Car Product
public class Car {
    private final CarType carType;
    private final int seats;
    private final Engine engine;
    private final Transmission transmission;
    private final TripComputer tripComputer;
    private final GPSNavigator gpsNavigator;
    private double fuel = 0;

    public Car(CarType carType, int seats, Engine engine, Transmission transmission, TripComputer tripComputer, GPSNavigator gpsNavigator) {
        this.carType = carType;
        this.seats = seats;
        this.engine = engine;
        this.transmission = transmission;
        this.tripComputer = tripComputer;
        this.gpsNavigator = gpsNavigator;
    }

    public CarType getCarType() {
        return carType;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public int getSeats() {
        return seats;
    }

    public Engine getEngine() {
        return engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public TripComputer getTripComputer() {
        return tripComputer;
    }

    public GPSNavigator getGpsNavigator() {
        return gpsNavigator;
    }

}
