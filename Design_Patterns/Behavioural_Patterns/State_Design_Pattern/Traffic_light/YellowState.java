package Design_Patterns.Behavioural_Patterns.State_Design_Pattern.Traffic_light;

public class YellowState implements TrafficLightState {
    @Override
    public void action(TrafficLight trafficLight) {
        System.out.println("Yellow Light - Slow down!");
        try {
            int i = 2;
            while(i > 0) {
                System.out.print(i-- + " ");
                Thread.sleep(1000);
            }
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        trafficLight.setState(new RedState());
    }
}
