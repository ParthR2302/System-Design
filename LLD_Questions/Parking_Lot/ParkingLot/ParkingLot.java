package LLD_Questions.Parking_Lot.ParkingLot;

import LLD_Questions.Parking_Lot.Entities.Ticket;
import LLD_Questions.Parking_Lot.Entities.Vehicle;
import LLD_Questions.Parking_Lot.Payment.Payment;

public class ParkingLot {
    private final ParkingBuilding parkingBuilding;
    private final EntranceGate entranceGate;
    private final ExitGate exitGate;

    public ParkingLot(ParkingBuilding parkingBuilding, EntranceGate entranceGate, ExitGate exitGate) {
        this.parkingBuilding = parkingBuilding;
        this.entranceGate = entranceGate;
        this.exitGate = exitGate;
    }

    public Ticket vehicleArrives(Vehicle vehicle) {
        return entranceGate.enter(parkingBuilding, vehicle);
    }

    public void vehicleDeparts(Ticket ticket, Payment paymentMethod) {
        exitGate.completeExit(parkingBuilding, ticket, paymentMethod);
    }
}
