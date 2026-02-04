package LLD_Questions.Parking_Lot.Managers;

import java.util.List;

import LLD_Questions.Parking_Lot.Entities.ParkingSpot;
import LLD_Questions.Parking_Lot.LookupStrategies.ParkingSpotLookupStrategy;

public class TwoWheelerSpotManager extends ParkingSpotManager {
    public TwoWheelerSpotManager(List<ParkingSpot> spots, ParkingSpotLookupStrategy lookupStrategy) {
        super(spots, lookupStrategy);
    }
}
