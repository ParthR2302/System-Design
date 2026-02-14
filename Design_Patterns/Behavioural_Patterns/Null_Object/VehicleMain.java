package Design_Patterns.Behavioural_Patterns.Null_Object;

public class VehicleMain {
    public static void main(String[] args) {
        VehicleInterface car = VehicleFactory.getVehicle("car");
        // No need of null check here
        car.start();
        car.stop();

        VehicleInterface truck = VehicleFactory.getVehicle("truck");
        truck.start();
        truck.stop();
        System.out.println("Type of truck: " + truck.getClass().getSimpleName());
    }
}
