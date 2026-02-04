package LLD_Questions.Parking_Lot.ParkingLot;

import java.util.List;

import LLD_Questions.Parking_Lot.Entities.ParkingSpot;
import LLD_Questions.Parking_Lot.Entities.Ticket;
import LLD_Questions.Parking_Lot.Entities.Vehicle;

public class ParkingBuilding {
    private final List<ParkingLevel> levels;

    public ParkingBuilding(List<ParkingLevel> levels) {
        this.levels = levels;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        for(ParkingLevel level : levels) {
            if(level.hasAvailableSpot(vehicle.getVehicleType())) {
                ParkingSpot spot = level.park(vehicle);
                if(spot != null) {
                    Ticket ticket = new Ticket(vehicle, spot, level);
                    System.out.println("Parking allocated at level: "
                            + level.getLevel()
                            + " spot: " + spot.getSpotId());
                    return ticket;
                }
            }
        }

        throw new RuntimeException("No available spots for vehicle type: " + vehicle.getVehicleType());
    }

    public void release(Ticket ticket) {
        ticket.getLevel().unpark(ticket.getVehicle(), ticket.getParkingSpot());
        System.out.println("Parking spot released at level: "
                + ticket.getLevel().getLevel()
                + " spot: " + ticket.getParkingSpot().getSpotId());
    }
    
}
