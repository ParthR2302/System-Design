package Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.builders;

import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.cars.CarType;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.components.Engine;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.components.GPSNavigator;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.components.Transmission;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.components.TripComputer;

// Common Builder Interface
public interface Builder {
    void setCarType(CarType type);
    void setSeats(int seats);
    void setEngine(Engine engine);
    void setTransmission(Transmission transmission);
    void setTripComputer(TripComputer tripComputer);
    void setGPSNavigator(GPSNavigator gpsNavigator);
}
