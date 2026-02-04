package LLD_Questions.Parking_Lot.LookupStrategies;

import java.util.List;

import LLD_Questions.Parking_Lot.Entities.ParkingSpot;

public class RandomLookupStrategy implements ParkingSpotLookupStrategy {
    @Override
    public ParkingSpot selectSpot(List<ParkingSpot> spots) {

        for(ParkingSpot spot : spots) {
            if (spot.isFree()) {
                return spot;
            }
        }

        return null;
    }
}
