package Design_Patterns.Behavioural_Patterns.Strategy.Payment;

import Design_Patterns.Behavioural_Patterns.Strategy.Payment.Strategies.PaymentStrategy;

public class PaymentProcessor {
    PaymentStrategy paymentStrategy;

    public PaymentProcessor(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment() {
        paymentStrategy.processPayment();
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
}
