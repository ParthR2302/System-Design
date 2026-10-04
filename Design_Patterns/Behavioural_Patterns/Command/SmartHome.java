package Design_Patterns.Behavioural_Patterns.Command;

import java.util.ArrayList;

/*
Class List
- SmartHome
- Command (Interface)
- Light
- Fan
- LightCommand
- FanCommand
- RemoteController
*/

public class SmartHome {
    public static void main(String[] args) {
        Light light = new Light();
        Fan fan = new Fan();
        RemoteController remoteController = new RemoteController();
        remoteController.setCommand(0, new LightCommand(light));
        remoteController.setCommand(1, new FanCommand(fan));

        remoteController.pressButton(0);
        remoteController.pressButton(1);
        remoteController.pressButton(1);
        remoteController.pressButton(1);
        remoteController.pressButton(0);
        remoteController.pressButton(0);
        remoteController.pressButton(0);
        remoteController.pressButton(1);

        remoteController.pressButton(3);
        remoteController.pressButton(10);

        remoteController.setCommand(2, new StarCommand(light, fan));
        remoteController.pressButton(2);
    }
}

interface Command {
    void execute();

    void undo();
}

class Light {
    public void on() {
        System.out.println("Light turned ON");
    }

    public void off() {
        System.out.println("Light turned OFF");
    }
}

class Fan {
    public void on() {
        System.out.println("Fan turned ON");
    }

    public void off() {
        System.out.println("Fan turned OFF");
    }
}

class LightCommand implements Command {
    private Light light;

    public LightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}

class FanCommand implements Command {
    private Fan fan;

    public FanCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.on();
    }

    @Override
    public void undo() {
        fan.off();
    }
}

class StarCommand implements Command {

    private Light light;
    private Fan fan;

    public StarCommand(Light light, Fan fan) {
        this.light = light;
        this.fan = fan;
    }

    @Override
    public void execute() {
        light.on();
        fan.on();
    }

    @Override
    public void undo() {
        light.off();
        fan.off();
    }
    
}

class RemoteController {
    private static final int numButtons = 4;
    private ArrayList<Command> buttons = new ArrayList<>(numButtons);
    boolean[] buttonPressed = new boolean[numButtons];

    public RemoteController() {
        for (int i = 0; i < numButtons; ++i) // We cannot do (i < buttons.size()), as after new ArrayList<>(numButtons),
                                             // the size of the buttons ArrayList is still 0
        {
            buttons.add(null);
            buttonPressed[i] = false;
        }
    }

    public void setCommand(int idx, Command command) {
        if (idx >= 0 && idx < buttons.size()) {
            buttons.set(idx, command);
            buttonPressed[idx] = false; // It can happen that idx button was assigned to a command and was on pressed
                                        // state
        }
    }

    public void pressButton(int idx) {
        if (idx >= 0 && idx < buttons.size() && buttons.get(idx) != null) {
            if (buttonPressed[idx] == false) {
                buttons.get(idx).execute();
                buttonPressed[idx] = true;
            } else {
                buttons.get(idx).undo();
                buttonPressed[idx] = false;
            }
        } else {
            System.out.println("No commands assigned at button " + idx);
        }
    }
}