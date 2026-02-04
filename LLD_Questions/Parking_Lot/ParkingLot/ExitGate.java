package LLD_Questions.Parking_Lot.ParkingLot;

import LLD_Questions.Parking_Lot.Costing.CostComputation;
import LLD_Questions.Parking_Lot.Entities.Ticket;
import LLD_Questions.Parking_Lot.Payment.Payment;

public class ExitGate {
    private final CostComputation costComputation;
    
    public ExitGate(CostComputation costComputation) {
        this.costComputation = costComputation;
    }

    public void completeExit(ParkingBuilding parkingBuilding, Ticket ticket, Payment paymentMethod) {
        double amount = calculatePrice(ticket);

        boolean success = paymentMethod.pay(amount);

        if(!success) {
            throw new RuntimeException("Payment failed. Cannot exit.");
        } 

        parkingBuilding.release(ticket);
        System.out.println("Exit completed");
    }

    public double calculatePrice(Ticket ticket) {
        return costComputation.calculateCost(ticket);
    }
}
