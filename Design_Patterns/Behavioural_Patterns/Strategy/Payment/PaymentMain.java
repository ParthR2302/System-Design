package Design_Patterns.Behavioural_Patterns.Strategy.Payment;

import Design_Patterns.Behavioural_Patterns.Strategy.Payment.Strategies.CreditCardPayment;
import Design_Patterns.Behavioural_Patterns.Strategy.Payment.Strategies.PayPalPayment;
import Design_Patterns.Behavioural_Patterns.Strategy.Payment.Strategies.PaymentStrategy;
import Design_Patterns.Behavioural_Patterns.Strategy.Payment.Strategies.StripePayment;

public class PaymentMain {
    public static void main(String[] args) {
        PaymentStrategy creditCardPaymentStrategy = new CreditCardPayment();
        PaymentStrategy payPalPaymentStrategy = new PayPalPayment();
        PaymentStrategy stripePaymentStrategy = new StripePayment();

        // For the above lines of code, we can create a Map to map Payment Type string with its corresponding Strategy
        // mp.get("Credit Card") -> give CreditCardPayment()

        PaymentProcessor paymentProcessor = new PaymentProcessor(creditCardPaymentStrategy);

        paymentProcessor.processPayment();

        paymentProcessor.setPaymentStrategy(payPalPaymentStrategy);
        paymentProcessor.processPayment();

        paymentProcessor.setPaymentStrategy(stripePaymentStrategy);
        paymentProcessor.processPayment();

        /*
            If at any point we want to introduce a new Payment Strategy,
            We just need to implement that strategy (it needs to implement the PaymentStrategy interface)
            Once implemented, we can directly start using its method using the PaymentProcessor
        */
    }
}
