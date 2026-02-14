package Design_Patterns.Behavioural_Patterns.Chain_of_Responsibility.Logger;

public abstract class AbstractLogger {
    protected LogLevel logLevel;

    protected AbstractLogger nextLogger;

    public void logMessage(LogLevel logLevel, String message) {
        if(this.logLevel == logLevel) {
            writeMessage(message);
            // return; 
            // If return is commented out, the message will be passed to the next logger in the chain, allowing multiple loggers to handle the same message if they match the log level.
        }

        if(nextLogger != null) {
            this.nextLogger.logMessage(logLevel, message);
        }
    }

    public abstract void writeMessage(String message);
}
