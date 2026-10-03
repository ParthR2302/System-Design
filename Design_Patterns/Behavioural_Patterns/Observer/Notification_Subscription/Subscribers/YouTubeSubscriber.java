package Design_Patterns.Behavioural_Patterns.Observer.Notification_Subscription.Subscribers;

public class YouTubeSubscriber implements Subscriber {
  private String name; // Name of the subscriber

  public YouTubeSubscriber(String name) {
    this.name = name; // Initialize the subscriber with their name
  }

  @Override
  public void notifySubscriber(String video) {
    // When notified, this method will execute, and the subscriber watches the
    // new video
    System.out.println("Hey, " + name + "! New video " + video + " is uploaded for you to watch on YouTube");
  }
}
