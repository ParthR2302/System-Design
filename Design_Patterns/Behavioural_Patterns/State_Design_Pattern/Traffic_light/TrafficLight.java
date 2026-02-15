package Design_Patterns.Behavioural_Patterns.State_Design_Pattern.Traffic_light;

public class TrafficLight {
    TrafficLightState state;

    public TrafficLight() {
        this.state = new RedState();
    }

    public void setState(TrafficLightState state) {
        this.state = state;
    }

    public void action() {
        state.action(this);
    }
}
