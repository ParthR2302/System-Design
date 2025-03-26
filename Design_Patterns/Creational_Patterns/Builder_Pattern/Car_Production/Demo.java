package Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production;

import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.builders.CarBuilder;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.builders.CarManualBuilder;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.cars.Car;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.cars.Manual;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Car_Production.directors.Director;

// CLient Code
public class Demo {
    public static void main(String[] args) {
        Director director = new Director();

        CarBuilder carBuilder = new CarBuilder();
        director.constructSportsCar(carBuilder);
        Car sportsCar = carBuilder.getResult();
        System.out.println("\nCar built:\n" + sportsCar.getCarType());

        CarManualBuilder carManualBuilder = new CarManualBuilder();
        director.constructSportsCar(carManualBuilder);
        Manual carManual = carManualBuilder.getResult();
        System.out.println("\nCar Manual Built:\n" + carManual.print());

        System.out.println("\nEnd of code\n");
    }
}
