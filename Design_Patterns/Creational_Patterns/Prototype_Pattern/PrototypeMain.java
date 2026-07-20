package Design_Patterns.Creational_Patterns.Prototype_Pattern;

public class PrototypeMain {
    public static void main(String[] args) {
        // Create a Car object and set values for data members.
        Car car1 = new Car();
        car1.setSeatCapacity(5);
        car1.setCarType("XUV");
        System.out.println("Original car1 details:");
        car1.printDetails();
        System.out.println();

        // -------------------------- Clone the Car object --------------------------
        Car car2 = (Car) car1.clone();
        System.out.println("Cloned car2 details:");
        car2.printDetails();
        System.out.println();

        // Modify car2's object field and show car1 remains unchanged.
        car2.setCarType("Sports");
        System.out.println("After modifying car2 carType:");
        System.out.println("car2 details:");
        car2.printDetails();
        System.out.println();

        System.out.println("car1 details after car2 modification:");
        car1.printDetails();
    }
}
