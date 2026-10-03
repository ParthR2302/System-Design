package Design_Patterns.Behavioural_Patterns.Observer.Notification_Subscription.Subscribers;

public class EmailSubscriber implements Subscriber {

    private String email;

    public EmailSubscriber(String email) {
        this.email = email;
    }

    @Override
    public void notifySubscriber(String video) {
        System.out.println("Sending email to: " + email + " New video uploaded: " + video);
    }
    
}
