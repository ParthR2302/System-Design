package Design_Patterns;

abstract class LogProcessor {

    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    LogProcessor nextLoggerProcessor;

    LogProcessor(LogProcessor loggerProcessor) {

        this.nextLoggerProcessor = loggerProcessor;

    }

    public void log(int logLevel, String message) {

        if (nextLoggerProcessor != null) {
            nextLoggerProcessor.log(logLevel, message);
        }
        else {
            System.out.println("No next level logProcessor available");
        }
    }
}

class InfoLogProcessor extends LogProcessor {
    InfoLogProcessor(LogProcessor logProcessor) {
        super(logProcessor);
    }
    
    @Override
    public void log(int logLevel, String message) {
        if(logLevel == INFO) {
            System.out.println("INFO: " + message);
        }
        else super.log(logLevel, message);
    }
}

class DebugLogProcessor extends LogProcessor {
    DebugLogProcessor(LogProcessor logProcessor) {
        super(logProcessor);
    }
    
    @Override
    public void log(int logLevel, String message) {
        if(logLevel == DEBUG) {
            System.out.println("DEBUG: " + message);
        }
        else super.log(logLevel, message);
    }
}

class ErrorLogProcessor extends LogProcessor {
    ErrorLogProcessor(LogProcessor logProcessor) {
        super(logProcessor);
    }
    
    @Override
    public void log(int logLevel, String message) {
        if(logLevel == ERROR) {
            System.out.println("ERROR: " + message);
        }
        else super.log(logLevel, message);
    }
}

public class LogProcessorClient {
    public static void main(String[] args) {
        LogProcessor logProcessor = new InfoLogProcessor(new DebugLogProcessor(new ErrorLogProcessor(null)));
        
        logProcessor.log(1, "Info log!");
        logProcessor.log(2, "Debug log!");
        logProcessor.log(3, "Error log!");
        logProcessor.log(4, "External log!");
    }
}