package Design_Patterns.Behavioural_Patterns.State_Design_Pattern.Traffic_light;

public class TrafficLightMain {
    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();
        int i=6;
        while (i > 0) {
            trafficLight.action();
            i--;
        }
    }
}
