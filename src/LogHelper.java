import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

class LogHelper {

  private static final Path logPath = Paths.get(System.getenv("LOCALAPPDATA"), "flashcards", "Log", "Lernsoftware.log");
  private static Logger logFile;

  private LogHelper() {

    try {
      logFile = Logger.getLogger("LogFile");
      FileHandler fileHandler = new FileHandler(logPath.toString(), true);
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
