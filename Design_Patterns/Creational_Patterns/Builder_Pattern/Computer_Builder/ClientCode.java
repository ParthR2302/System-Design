package Design_Patterns.Creational_Patterns.Builder_Pattern.Computer_Builder;

public class ClientCode {
    public static void main(String[] args) {
        Computer computer1 = new ComputerBuilder("Intel i5", 8).build();
        computer1.showConfig();

        Computer computer2 = new ComputerBuilder("Intel i9", 16).setGraphicsCard(true).setBluetooth(true).build();
        computer2.showConfig();
    }
}
