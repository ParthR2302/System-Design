package LLD_Questions.Parking_Lot.Payment;

public class CashPayment implements Payment {
    @Override
    public boolean pay(double amount) {
        System.out.println("Paid " + amount + " in cash.");
        return true;
    }
}