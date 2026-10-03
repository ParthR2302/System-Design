package Design_Patterns.Behavioural_Patterns.Observer.Notification_Subscription;

import java.util.ArrayList;
import java.util.List;

import Design_Patterns.Behavioural_Patterns.Observer.Notification_Subscription.Subscribers.Subscriber;

public class YouTubeChannelImpl implements YouTubeChannel {

    List<Subscriber> subscribers = new ArrayList<>();
    String video;

    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void uploadVideo(String video) {
        this.video = video;
        notifySubscribers();
    }

    @Override
    public void notifySubscribers() {
        for(Subscriber subscriber : subscribers) {
            subscriber.notifySubscriber(video);
        }
    }
}
