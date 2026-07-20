package Design_Patterns.Creational_Patterns.Prototype_Pattern;

public class Car extends Vehicle {
    private int seatCapacity;
    private String carType;

    public Car() {
        super("", "", "");
    }

    public Car(Car car) {
        super(car.getBrand(), car.getModel(), car.getColour());
        this.seatCapacity = car.seatCapacity;
        this.carType = car.carType;
    }

    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    // Implementation of the clone() method to create a copy of the Car object.
    @Override
    public Vehicle clone() {
        return new Car(this);
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Seat Capacity: " + seatCapacity);
        System.out.println("Car Type: " + carType);
    }
}
