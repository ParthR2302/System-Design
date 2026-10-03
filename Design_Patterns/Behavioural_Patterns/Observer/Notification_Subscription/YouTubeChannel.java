package Design_Patterns.Behavioural_Patterns.Observer.Notification_Subscription;

import Design_Patterns.Behavioural_Patterns.Observer.Notification_Subscription.Subscribers.Subscriber;

public interface YouTubeChannel {
    void addSubscriber(Subscriber subscriber);
    void removeSubscriber(Subscriber subscriber);
    void notifySubscribers();
}
