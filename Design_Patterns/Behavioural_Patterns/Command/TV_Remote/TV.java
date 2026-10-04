package Design_Patterns.Behavioural_Patterns.Command.TV_Remote;

public class TV {
    public void turnOn() {
        System.out.println("TV turned ON!");
    }

    public void turnOff() {
        System.out.println("Goodbye, TV turned OFF!");
    }

    public void changeChannel(int channel) {
        System.out.println("Channel changed to " + channel);
    }

    public void adjustVolume(int volume) {
        System.out.println("Volume set to " + volume);
    }

    public void adjustBrightness(int brightness) {
        System.out.println("Brightness level set to " + brightness);
    }
}
