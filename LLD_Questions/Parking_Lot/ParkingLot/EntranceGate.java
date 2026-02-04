package LLD_Questions.Parking_Lot.ParkingLot;

import LLD_Questions.Parking_Lot.Entities.Ticket;
import LLD_Questions.Parking_Lot.Entities.Vehicle;

public class EntranceGate {
    public Ticket enter(ParkingBuilding parkingBuilding, Vehicle vehicle) { return parkingBuilding.parkVehicle(vehicle); }
}
