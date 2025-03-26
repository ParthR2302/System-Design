package Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.directors;

import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.builders.Builder;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.cars.CarType;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.components.Engine;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.components.GPSNavigator;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.components.Transmission;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.components.TripComputer;

// Director controls builders
public class Director {
    public void constructSportsCar(Builder builder) {
        builder.setCarType(CarType.SPORTS_CAR);
        builder.setSeats(2);
        builder.setEngine(new Engine(3.0, 0));
        builder.setTransmission(Transmission.SEMI_AUTOMATIC);
        builder.setTripComputer(new TripComputer());
        builder.setGPSNavigator(new GPSNavigator());
    }

    public void constructCityCar(Builder builder) {
        builder.setCarType(CarType.CITY_CAR);
        builder.setSeats(4);
        builder.setEngine(new Engine(1.2, 0));
        builder.setTransmission(Transmission.AUTOMATIC);
        builder.setTripComputer(new TripComputer());
        builder.setGPSNavigator(new GPSNavigator());
    }

    public void constructSUV(Builder builder) {
        builder.setCarType(CarType.SUV);
        builder.setSeats(7);
        builder.setEngine(new Engine(2.5, 0));
        builder.setTransmission(Transmission.MANUAL);
        builder.setGPSNavigator(new GPSNavigator());
    }
}
