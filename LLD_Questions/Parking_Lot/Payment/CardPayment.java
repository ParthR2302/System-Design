package LLD_Questions.Parking_Lot.Payment;

public class CardPayment implements Payment {
    @Override
    public boolean pay(double amount) {
        System.out.println("Paid " + amount + " using card.");
        return true;
    }
    
}
