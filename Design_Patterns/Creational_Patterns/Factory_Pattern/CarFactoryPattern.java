package Design_Patterns.Creational_Patterns.Factory_Pattern;

interface Car1 {
    public void drive();
}

class Sedan implements Car1 {
    public void drive() {
        System.out.println("Driving a Sedan");
    }
}

class SUV implements Car1 {
    public void drive() {
        System.out.println("Driving an SUV");
    }
}

class CarFactoryImpl {
    public static Car1 createCar(String type) {
        if (type.equalsIgnoreCase("Sedan")) {
            return new Sedan();
        } else if (type.equalsIgnoreCase("SUV")) {
            return new SUV();
        }
        return null;
    }
}

public class CarFactoryPattern {
    public static void main(String[] args) {
        Car1 sedanCar = CarFactoryImpl.createCar("Sedan");
        Car1 suvCar = CarFactoryImpl.createCar("SUV");
        sedanCar.drive();
        suvCar.drive();
    }    
}
