package LLD_Questions.Parking_Lot.Entities;

import java.time.LocalDateTime;

import LLD_Questions.Parking_Lot.ParkingLot.ParkingLevel;

public class Ticket {
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final ParkingLevel level;
    private final LocalDateTime entryTime;

    public Ticket(Vehicle vehicle, ParkingSpot parkingSpot, ParkingLevel level) {
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.level = level;
        this.entryTime = LocalDateTime.now();
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public ParkingLevel getLevel() {
        return level;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    

}
