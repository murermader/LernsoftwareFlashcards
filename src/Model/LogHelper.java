package Model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogHelper {

    private static final Path LOG_DIRECTORY_LINUX = Paths.get(System.getProperty("user.home"), "Library", "Application Support", "flashcards", "Log", "Lernsoftware.log");
    private static final Path LOG_DIRECTORY_WINDOWS = Paths.get(System.getenv("LOCALAPPDATA"), "flashcards", "Log", "Lernsoftware.log");
    private static Logger logFile;

    public LogHelper() {

        try {
            String os = System.getProperty("os.name");
            Path logDirectory;
            if(os.contains("windows")){
                logDirectory = LOG_DIRECTORY_WINDOWS;
            } else {
                logDirectory = LOG_DIRECTORY_LINUX;
            }
            logFile = Logger.getLogger("LogFile");
            FileHandler fileHandler = new FileHandler(logDirectory.toString(), true);
            SimpleFormatter formatter = new SimpleFormatter();
            logFile.addHandler(fileHandler);
            fileHandler.setFormatter(formatter);

        } catch (Exception ex) {
            System.out.println("Exception Beim Model.LogHelper" + ex);
        }
    }

    private static Logger getLogger() {

        if (logFile == null) {
            try {

                new LogHelper();

            } catch (Exception ex) {
                System.out.println("Fehler beim Logging: " + ex);
            }
        }
        return logFile;
    }

    public static void writeToLog(Level level, String message) {
        getLogger().log(level, message);
    }
}
