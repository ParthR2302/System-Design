package Design_Patterns.Behavioural_Patterns.Strategy.Payment.Strategies;

public class CreditCardPayment implements PaymentStrategy {

    @Override
    public void processPayment() {
        System.out.println("Inside Payment Processing method of Credit Card");
        
    }
    
}
