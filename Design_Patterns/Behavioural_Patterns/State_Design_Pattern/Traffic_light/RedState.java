package Design_Patterns.Behavioural_Patterns.State_Design_Pattern.Traffic_light;

public class RedState implements TrafficLightState {
    @Override
    public void action(TrafficLight trafficLight) {
        System.out.println("Red light is on");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        trafficLight.setState(new GreenState());
    }
}
