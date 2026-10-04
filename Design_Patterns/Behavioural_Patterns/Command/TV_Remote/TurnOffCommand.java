package Design_Patterns.Behavioural_Patterns.Command.TV_Remote;

public class TurnOffCommand implements Command {

    private TV tv;

    public TurnOffCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {tv.turnOff();
    }

}
