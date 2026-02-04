package LLD_Questions.Parking_Lot.Costing;

import LLD_Questions.Parking_Lot.Entities.Ticket;

public interface CostingStrategy {

    public int calculateCost(Ticket ticket);
}
