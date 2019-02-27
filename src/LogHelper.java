import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

class LogHelper {

    private static Logger logFile;

    private LogHelper() {

        try {

            Helper helper = new Helper();
            Path logDirectory = Paths.get(helper.getLogDirectory().toString(), "Lernsoftware.log");
            logFile = Logger.getLogger("LogFile");
            FileHandler fileHandler = new FileHandler(logDirectory.toString(), true);
            SimpleFormatter formatter = new SimpleFormatter();
            logFile.addHandler(fileHandler);
            fileHandler.setFormatter(formatter);

        } catch (Exception ex) {
            System.out.println("Exception Beim LogHelper" + ex);
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

    static void writeToLog(Level level, String message) {
        getLogger().log(level, message);
    }
}
