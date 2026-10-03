package Design_Patterns.Behavioural_Patterns.Observer.Notification_Subscription;

import Design_Patterns.Behavioural_Patterns.Observer.Notification_Subscription.Subscribers.EmailSubscriber;
import Design_Patterns.Behavioural_Patterns.Observer.Notification_Subscription.Subscribers.Subscriber;
import Design_Patterns.Behavioural_Patterns.Observer.Notification_Subscription.Subscribers.YouTubeSubscriber;

public class YouTubeMain {
    public static void main(String[] args) {
        YouTubeChannelImpl youTubeChannel = new YouTubeChannelImpl();

        Subscriber subscriber1 = new EmailSubscriber("user1@mail.com");
        Subscriber bob = new YouTubeSubscriber("NoobMaster69");
        Subscriber subscriber3 = new EmailSubscriber("user2@mail.com");

        youTubeChannel.addSubscriber(subscriber1);
        youTubeChannel.addSubscriber(bob);

        youTubeChannel.uploadVideo("Video-1");
        System.out.println();

        youTubeChannel.addSubscriber(subscriber3);
        youTubeChannel.uploadVideo("Video-2");
        System.out.println();

        youTubeChannel.removeSubscriber(bob);
        youTubeChannel.uploadVideo("Leh | Bike Ride Vlog");
        System.out.println();

        /*
            If any new type of subscription comes, we just need to code that subscription type (it needs to implement Subscriber interface).
            No other changes are needed anywhere else
            We can simply start using that subscription type in add, remove and notify method
        */
    }
}
