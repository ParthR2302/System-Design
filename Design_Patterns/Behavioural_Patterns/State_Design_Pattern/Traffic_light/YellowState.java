package Design_Patterns.Behavioural_Patterns.State_Design_Pattern.Traffic_light;

public class YellowState implements TrafficLightState {
    @Override
    public void action(TrafficLight trafficLight) {
        System.out.println("Yellow Light - Slow down!");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        trafficLight.setState(new RedState());
    }
}
