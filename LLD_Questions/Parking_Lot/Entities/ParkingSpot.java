package LLD_Questions.Parking_Lot.Entities;

public class ParkingSpot {
    private final String spotId;
    boolean isFree = true;
    Vehicle vehicle;

    public ParkingSpot(String spotId) {
        this.spotId = spotId;
        this.isFree = true;
        this.vehicle = null;
    }

    public boolean isSpotFree() {
        return isFree;
    }

    public void occupySpot(Vehicle vehicle) {
        this.isFree = false;
        this.vehicle = vehicle;
    }

    public void releaseSpot() {
        this.isFree = true;
        this.vehicle = null;
    }

    public String getSpotId() {
        return spotId;
    }
    public boolean isFree() {
        return isFree;
    }
    public void setOccupied(boolean isFree) {
        this.isFree = isFree;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
