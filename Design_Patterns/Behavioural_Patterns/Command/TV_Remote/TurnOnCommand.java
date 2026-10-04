package Design_Patterns.Behavioural_Patterns.Command.TV_Remote;

public class TurnOnCommand implements Command {

    private TV tv;

    public TurnOnCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOn();
    }
    
}
