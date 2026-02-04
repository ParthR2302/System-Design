package LLD_Questions.Parking_Lot.LookupStrategies;

import java.util.List;

import LLD_Questions.Parking_Lot.Entities.ParkingSpot;

public interface ParkingSpotLookupStrategy {
    ParkingSpot selectSpot(List<ParkingSpot> spots);
} 
