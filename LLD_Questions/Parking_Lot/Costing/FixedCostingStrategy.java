package LLD_Questions.Parking_Lot.Costing;

import LLD_Questions.Parking_Lot.Entities.Ticket;

public class FixedCostingStrategy implements CostingStrategy {

    @Override
    public int calculateCost(Ticket ticket) {
        return 100;
    }
    
}
