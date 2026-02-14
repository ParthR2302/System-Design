package Design_Patterns.Behavioural_Patterns.Chain_of_Responsibility.Logger.ConcreteLoggers;

import Design_Patterns.Behavioural_Patterns.Chain_of_Responsibility.Logger.AbstractLogger;
import Design_Patterns.Behavioural_Patterns.Chain_of_Responsibility.Logger.LogLevel;

public class InfoLogger extends AbstractLogger{
    public InfoLogger() {
        this.logLevel = LogLevel.INFO;
        this.nextLogger = new WarningLogger();
    }

    public void writeMessage(String message) {
        System.out.println("Info Logger: " + message);
    }
}
