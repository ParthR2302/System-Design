package Design_Patterns.Behavioural_Patterns.Chain_of_Responsibility.Logger.ConcreteLoggers;

import Design_Patterns.Behavioural_Patterns.Chain_of_Responsibility.Logger.AbstractLogger;
import Design_Patterns.Behavioural_Patterns.Chain_of_Responsibility.Logger.LogLevel;

public class ErrorLogger extends AbstractLogger {
    public ErrorLogger() {
        this.logLevel = LogLevel.ERROR;
        this.nextLogger = null; // No next logger since this is the highest level
    }

    @Override
    public void writeMessage(String message) {
        System.out.println("Error Logger: " + message);
    }
    
}
