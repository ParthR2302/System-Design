package Design_Patterns.Creational_Patterns.Builder_Pattern.Computer_Builder;

// Product
class Computer {
    // Required Parameters
    public String processor;
    public int ram;

    // Optional Parameters
    public boolean graphicsCard;
    public boolean bluetooth;

    // Private constructor to enforce the use of the builder
    public Computer(ComputerBuilder builder) {
        this.processor = builder.processor;
        this.ram = builder.ram;
        this.graphicsCard = builder.graphicsCard;
        this.bluetooth = builder.bluetooth;
    }

    // Display computer configuration
    public void showConfig() {
        System.out.println("Processor: " + processor + ", RAM: " + ram + "GB, Graphics Card: " 
                            + graphicsCard + ", Bluetooth: " + bluetooth);
    }
}

// Builder class
public class ComputerBuilder {
    // Required Parameters
    public String processor;
    public int ram;

    // Optional Parameters
    public boolean graphicsCard = false;
    public boolean bluetooth = false;

    // Constructor for required parameters
    ComputerBuilder(String processor, int ram) {
        this.processor = processor;
        this.ram = ram;
    }

    // Setter methods for optional parameters
    public ComputerBuilder setGraphicsCard(boolean graphicsCard) {
        this.graphicsCard = graphicsCard;
        return this;
    }
    public ComputerBuilder setBluetooth(boolean bluetooth) {
        this.bluetooth = bluetooth;
        return this;
    }

    public Computer build() {
        return new Computer(this);
    }
}
