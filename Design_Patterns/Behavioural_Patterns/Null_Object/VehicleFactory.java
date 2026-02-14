package Design_Patterns.Behavioural_Patterns.Null_Object;

public class VehicleFactory {
    VehicleInterface vehicle;

    public static VehicleInterface getVehicle(String vehicle) {
        if (vehicle.equalsIgnoreCase("car")) {
            return new Car(4);
        }
        if (vehicle.equalsIgnoreCase("bike")) {
            return new Bike(2);
        }
        return new NullVehicle();
    }

}
