package Design_Patterns.Behavioural_Patterns.Null_Object;

public class Bike implements VehicleInterface {
    int seatCapacity;

    public Bike(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    @Override
    public void start() {
        System.out.println("Bike started");
    }

    @Override
    public void stop() {
        System.out.println("Bike stopped");
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }
    
}
