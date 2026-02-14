package Design_Patterns.Behavioural_Patterns.Chain_of_Responsibility.Logger;

import Design_Patterns.Behavioural_Patterns.Chain_of_Responsibility.Logger.ConcreteLoggers.DebugLogger;

public class LoggerMain {
    public static void main(String[] args) {
        AbstractLogger logger = new DebugLogger();

        logger.logMessage(LogLevel.INFO, "This is an information message.");
        logger.logMessage(LogLevel.DEBUG, "This is a debug message.");
        logger.logMessage(LogLevel.WARNING, "This is a warning message.");
        logger.logMessage(LogLevel.ERROR, "This is an error message.");

        System.out.println("\n--- Testing with FATAL log level ---\n");

        logger.logMessage(LogLevel.FATAL, "This is a fatal message.");
    }
}
