package LLD_Questions.Parking_Lot.Costing;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import LLD_Questions.Parking_Lot.Entities.Ticket;

public class TimeBasedCosting implements CostingStrategy {
    @Override
    public int calculateCost(Ticket ticket) {
        long durationInSeconds = (LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - ticket.getEntryTime().toEpochSecond(ZoneOffset.UTC));
        int ratePerSecond = 5; // Example rate
        return (int) (durationInSeconds * ratePerSecond);
    }
    
}
