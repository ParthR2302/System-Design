package Design_Patterns.Creational_Patterns.Prototype_Pattern;

public abstract class Vehicle {
    private String brand;
    private String model;
    private String colour;

    protected Vehicle() {}

    protected Vehicle(Vehicle target) {
        if(target != null) {
            this.brand = target.brand;
            this.model = target.model;
            this.colour = target.colour;
        }
    }

    // IMPORTANT: clone() method
    public abstract Vehicle clone();

    public void printDetails() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Colour: " + colour);
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColour() {
        return colour;
    }
}
