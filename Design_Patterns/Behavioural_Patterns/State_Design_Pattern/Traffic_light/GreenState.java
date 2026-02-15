package Design_Patterns.Behavioural_Patterns.State_Design_Pattern.Traffic_light;

public class GreenState implements TrafficLightState {
    @Override
    public void action(TrafficLight trafficLight) {
        System.out.println("Green Light - Go!");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();    
        }
        trafficLight.setState(new YellowState());
    }
}
