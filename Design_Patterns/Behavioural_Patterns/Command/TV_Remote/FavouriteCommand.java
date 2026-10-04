package Design_Patterns.Behavioural_Patterns.Command.TV_Remote;

public class FavouriteCommand implements Command {

    private TV tv;
    private int channel;
    private int brightness;

    public FavouriteCommand(TV tv, int channel, int brightness) {
        this.tv = tv;
        this.channel = channel;
        this.brightness = brightness;
    }

    @Override
    public void execute() {
        tv.turnOn();
        tv.changeChannel(channel);
        tv.adjustBrightness(brightness);
    }
    
}
