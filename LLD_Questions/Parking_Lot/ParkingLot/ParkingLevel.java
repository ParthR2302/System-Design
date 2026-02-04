package LLD_Questions.Parking_Lot.ParkingLot;

import java.util.Map;

import LLD_Questions.Parking_Lot.Entities.ParkingSpot;
import LLD_Questions.Parking_Lot.Entities.Vehicle;
import LLD_Questions.Parking_Lot.Enums.VehicleType;
import LLD_Questions.Parking_Lot.Managers.ParkingSpotManager;

public class ParkingLevel {
    int level;
    private final Map<VehicleType, ParkingSpotManager> spotManagers;

    public ParkingLevel(int level, Map<VehicleType, ParkingSpotManager> spotManagers) {
        this.level = level;
        this.spotManagers = spotManagers;
    }

    public boolean hasAvailableSpot(VehicleType vehicleType) {
        ParkingSpotManager manager = spotManagers.get(vehicleType);
        return manager != null && manager.hasFreeSpots();
    }

    public ParkingSpot park(Vehicle vehicle) {
        ParkingSpotManager manager = spotManagers.get(vehicle.getVehicleType());
        if(manager == null) {
            throw new IllegalArgumentException("No spot manager for vehicle type: " + vehicle.getVehicleType());
        }

        return manager.park(vehicle);
    }

    public void unpark(Vehicle vehicle, ParkingSpot spot) {
        ParkingSpotManager manager = spotManagers.get(vehicle.getVehicleType());
        
        manager.unpark(spot);
    }

    public int getLevel() {
        return level;
    }

}
