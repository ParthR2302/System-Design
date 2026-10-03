package Design_Patterns.Creational_Patterns.Prototype_Pattern;

public class Bus extends Vehicle {
    private int seatCapacity;

    public Bus() {
    }

    public Bus(Bus bus) {
        super(bus);
        this.seatCapacity = bus.seatCapacity;
    }

    @Override
    public Vehicle clone() {
        return new Bus(this);
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Seat Capacity: " + seatCapacity);
    }

}
