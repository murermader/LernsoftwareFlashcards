import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

class Helper {

    private static final Path appDirectory = Paths.get(System.getenv("LOCALAPPDATA"), "flashcards");

    List<String> getDeckNames(){
        try{
            boolean succes;
            List<String> fileNames = new ArrayList<>();
            File directory = new File(appDirectory.toString());
            if(directory.exists()){
                succes = true;
            }
            else{
                succes = directory.mkdir();
                if(!succes){
                    LogHelper.writeToLog(Level.INFO, "Directory konnte nicht erstellt werden.");
                }
                else{
                    LogHelper.writeToLog(Level.INFO, "Ordner erfolgreich erstellt.");
                }
            }
            if(succes){
                File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
                if(files != null){
                    for (File file: files) {

                        fileNames.add(file.getName());
                    }
                }
            }
            return fileNames;
        }catch(Exception ex){
            LogHelper.writeToLog(Level.INFO, "Fehler beim Beschaffen der Decknamen" +ex);
            return null;
        }
    }

    void createSampleDecks(){
        try{
            List<Flashcard> list = new ArrayList<>();
            String[] names = {"test1.txt", "test2.txt", "test3.txt"};

            for (int i = 0; i < 100; i++) {

                int random1 = (int)(Math.random() * 50 + 1);
                int random2 = (int)(Math.random() * 100 + 1);
                list.add(new Flashcard(Integer.toString(random1), Integer.toString(random2)));
            }

            for (String name: names) {
                saveListAsFile(list, name);
            }
        }
        catch(Exception ex) {
            LogHelper.writeToLog(Level.INFO, "Fehler beim Erstellen von SampleData" + ex);
        }
    }

    void saveListAsFile(List<Flashcard> cards, String deckName){

        createFolderIfNeeded();
        try{
            FileOutputStream fileStreamOut = new FileOutputStream(Paths.get(appDirectory.toString(), deckName).toString());
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStreamOut);
            objectStream.writeObject(cards);
            objectStream.close();
        }
        catch(Exception ex){
            LogHelper.writeToLog(Level.INFO, "Fehler beim Erstellen des Ordners: " +ex);
        }
    }

    //readObject gibt eine Warnung, da nicht sichergestellt werden kann, ob das zurückgegebene Objekt
    //tatsächlich vom Typ List<Flashcard> ist. Kann unterdrückt werden, da das Objekt nur von diesem Typ sein kann.
    @SuppressWarnings("unchecked")
    List<Flashcard> FlashcardListFromFile(String deckName){

        List<Flashcard> list = new ArrayList<>();
        try{
            FileInputStream fileStreamIn = new FileInputStream(Paths.get(appDirectory.toString(), deckName).toString());
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
