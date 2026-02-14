package Design_Patterns.Behavioural_Patterns.Null_Object;

public class Car implements VehicleInterface {
    int seatCapacity;

    public Car(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    @Override
    public void start() {
        System.out.println("Car started");
    }

    @Override
    public void stop() {
        System.out.println("Car stopped");
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }
}
