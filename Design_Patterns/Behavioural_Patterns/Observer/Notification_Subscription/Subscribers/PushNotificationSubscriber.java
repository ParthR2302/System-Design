package Design_Patterns.Behavioural_Patterns.Observer.Notification_Subscription.Subscribers;

public class PushNotificationSubscriber implements Subscriber {

    private String userDevice;

    public PushNotificationSubscriber(String userDevice) {
        this.userDevice = userDevice;
    }

    @Override
    public void notifySubscriber(String video) {
        System.out.println("Sending push notification to: " + userDevice + " New video uploaded: " + video);
    }
    
}
