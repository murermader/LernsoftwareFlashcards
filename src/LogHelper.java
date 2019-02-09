import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

class LogHelper {

  private static final Path appDirectory = Paths
      .get(System.getenv("LOCALAPPDATA"), "flashcards", "Log");
  private static final Path logPath = Paths.get(appDirectory.toString(), "Lernsoftware.log");
  private static Logger logFile;

  private LogHelper() {

    try {
      logFile = Logger.getLogger("LogFile");
      CreateFolderIfNeeded();
      FileHandler fileHandler = new FileHandler(logPath.toString(), true);
      SimpleFormatter formatter = new SimpleFormatter();
      logFile.addHandler(fileHandler);
      fileHandler.setFormatter(formatter);
    } catch (Exception ex) {
      System.out.println("Exception Beim LogHelper" + ex);
    }
  }

  private void CreateFolderIfNeeded() {

    boolean success;
    File directory = new File(appDirectory.toString());

    if (directory.exists()) {
      success = true;
    } else {
      success = directory.mkdir();
    }
    if (success) {
      LogHelper.writeToLog(Level.INFO, "Ordner für das Log erfolgreich erstellt.");
    } else {
      //Ordner für das Log konnte nicht erstellt werden. Den User darauf aufmerksam machen
      //dass, das Log nicht funktioniert?
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
