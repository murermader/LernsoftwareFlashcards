import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

class LoadAndSaveData {

    private static final Path appDirectory = Paths.get(System.getenv("LOCALAPPDATA"), "flashcards");
    private static final Path savedFlashcardsPath = Paths.get(appDirectory.toString(),"Flashkarten.txt");

    void save(List<Flashcard> list){

        createFolderIfNeeded();
        try{
            FileOutputStream fileStreamOut = new FileOutputStream(savedFlashcardsPath.toString());
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStreamOut);
            objectStream.writeObject(list);
            objectStream.close();
        }
        catch(Exception ex){
            LogHelper.writeToLog(Level.INFO, "Fehler beim Erstellen des Ordners: " +ex);
        }
    }

    //readObject gibt eine Warnung, da nicht sichergestellt werden kann, ob das zurückgegebene Objekt
    //tatsächlich vom Typ List<Flashcard> ist. Kann unterdrückt werden, da das Objekt nur von diesem Typ sein kann.
    @SuppressWarnings("unchecked")
    List<Flashcard> read(){

        List<Flashcard> list = new ArrayList<>();
        try{
            FileInputStream fileStreamIn = new FileInputStream(savedFlashcardsPath.toString());
            ObjectInputStream objectStream = new ObjectInputStream(fileStreamIn);
            list = (List<Flashcard>) objectStream.readObject();
        }
        catch(Exception ex){
            LogHelper.writeToLog(Level.INFO, "Fehler beim Einlesen der Speicherdatei: " + ex);
        }
        return list;
    }

    private void createFolderIfNeeded(){

        try{
            File directory = appDirectory.toFile();
            if(!directory.exists()){
                boolean isDirectoryCreated = directory.mkdir();
                if(isDirectoryCreated){
                    LogHelper.writeToLog(Level.INFO, "Ordner erstellt!");
                }
            }
        }
        catch (Exception ex){
            LogHelper.writeToLog(Level.INFO, "Fehler beim Erstellen des Ordners: " + ex);
        }
    }
}
