package LLD_Questions.Parking_Lot.Managers;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import LLD_Questions.Parking_Lot.Entities.ParkingSpot;
import LLD_Questions.Parking_Lot.Entities.Vehicle;
import LLD_Questions.Parking_Lot.LookupStrategies.ParkingSpotLookupStrategy;

public abstract class ParkingSpotManager {
    protected final List<ParkingSpot> parkingSpots;
    ParkingSpotLookupStrategy lookupStrategy;
    private final ReentrantLock lock = new ReentrantLock();

    ParkingSpotManager(List<ParkingSpot> parkingSpots, ParkingSpotLookupStrategy lookupStrategy) {
        this.parkingSpots = parkingSpots;
        this.lookupStrategy = lookupStrategy;
    }

    public ParkingSpot park(Vehicle vehicle) {
        lock.lock();
        try {
            ParkingSpot spot = lookupStrategy.selectSpot(parkingSpots);
            if (spot == null) {
                return null;
            }
            spot.setOccupied(false);
            spot.setVehicle(vehicle);
            return spot;
        } finally {
            lock.unlock();
        }
    }

    public void unpark(ParkingSpot spot) {
        lock.lock();
        try {
            spot.releaseSpot();
        } finally {
            lock.unlock();
        }
    }

    public boolean hasFreeSpots() {
        lock.lock();
        try {
            return parkingSpots.stream().anyMatch(ParkingSpot::isSpotFree);
        } finally {
            lock.unlock();
        }
    }
}
