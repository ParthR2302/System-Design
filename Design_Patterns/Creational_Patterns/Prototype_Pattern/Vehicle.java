package Design_Patterns.Creational_Patterns.Prototype_Pattern;

public abstract class Vehicle {
    private String brand;
    private String model;
    private String colour;

    protected Vehicle(String brand, String model, String colour) {
        this.brand = brand;
        this.model = model;
        this.colour = colour;
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
