import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

class LogHelper {

    private static final Path appDirectory = Paths.get(System.getenv("LOCALAPPDATA"), "flashcards", "Log");
    private static final Path logPath = Paths.get(appDirectory.toString(),"Lernsoftware.log");
    private static Logger logFile;

    private LogHelper(){

        try{
            logFile = Logger.getLogger("LogFile");
            FileHandler fileHandler = new FileHandler(logPath.toString(), true);
            SimpleFormatter formatter = new SimpleFormatter();
            logFile.addHandler(fileHandler);
            fileHandler.setFormatter(formatter);
        }
        catch(Exception e){
            System.out.println("Exception Beim LogHelper");
        }
    }

    private static Logger getLogger(){
        if(logFile == null){
            try {
                new LogHelper();
            }
            catch(Exception e){
                System.out.println("Fehler beim Logging: " +e);
            }
        }
        return logFile;
    }

    static void writeToLog(Level level, String message){
        getLogger().log(level, message);
    }
}
