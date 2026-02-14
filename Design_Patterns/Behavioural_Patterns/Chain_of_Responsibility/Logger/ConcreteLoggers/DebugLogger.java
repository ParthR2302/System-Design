package Design_Patterns.Behavioural_Patterns.Chain_of_Responsibility.Logger.ConcreteLoggers;

import Design_Patterns.Behavioural_Patterns.Chain_of_Responsibility.Logger.AbstractLogger;
import Design_Patterns.Behavioural_Patterns.Chain_of_Responsibility.Logger.LogLevel;

public class DebugLogger extends AbstractLogger  {

    public DebugLogger() {
        this.logLevel = LogLevel.DEBUG;
        this.nextLogger = new InfoLogger();
    }

    public void writeMessage(String message) {
        System.out.println("Debug Logger: " + message);
    }
}
