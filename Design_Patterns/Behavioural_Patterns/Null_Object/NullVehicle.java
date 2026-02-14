package Design_Patterns.Behavioural_Patterns.Null_Object;

public class NullVehicle implements VehicleInterface {

    int seatCapacity = 0;

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }
}
