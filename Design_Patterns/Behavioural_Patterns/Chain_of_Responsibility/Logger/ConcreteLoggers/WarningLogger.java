package Design_Patterns.Behavioural_Patterns.Chain_of_Responsibility.Logger.ConcreteLoggers;

import Design_Patterns.Behavioural_Patterns.Chain_of_Responsibility.Logger.AbstractLogger;
import Design_Patterns.Behavioural_Patterns.Chain_of_Responsibility.Logger.LogLevel;

public class WarningLogger extends AbstractLogger{
    public WarningLogger() {
        this.logLevel = LogLevel.WARNING;
        this.nextLogger = new ErrorLogger();
    }

    public void writeMessage(String message) {
        System.out.println("Warning Logger: " + message);
    }
    
}
