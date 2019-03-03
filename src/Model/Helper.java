package Model;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

//Hilfsklasse mit Methoden wie OS herausfinden, Dateien (Model.User, Decks) speichern und auslesen,
//SampleDecks erstellen etc. Alles was nützlich sein könnte und nicht in die anderen Klassen passt.
public class Helper {

  private Path flashcardsDirectory;
  private Path logDirectory;
  private static boolean onlyShowOnce = true;

  private static final Path LOG_DIRECTORY_WINDOWS = Paths
      .get(System.getenv("LOCALAPPDATA"), "flashcards", "Log");
  private static final Path FLASHCARDS_DIRECTORY_WINDOWS = Paths
      .get(System.getenv("LOCALAPPDATA"), "flashcards");
  private static final Path FLASHCARDS_DIRECTORY_LINUX = Paths
      .get(System.getProperty("user.home"), "Library", "Application Support", "flashcards");
  private static final Path LOG_DIRECTORY_LINUX = Paths
      .get(System.getProperty("user.home"), "Library", "Application Support", "flashcards", "Log");

  public Helper() {

    if (getOperationSystemNameLowerCase().equals("windows")) {
      flashcardsDirectory = FLASHCARDS_DIRECTORY_WINDOWS;
      logDirectory = LOG_DIRECTORY_WINDOWS;
      if(onlyShowOnce){
        LogHelper.writeToLog(Level.INFO, "OS als Windows erkannt. Benutze Windows-spezifische Pfade.");
        onlyShowOnce = false;
      }

    } else if (getOperationSystemNameLowerCase().equals("osx") || getOperationSystemNameLowerCase()
        .equals("linux")) {
      flashcardsDirectory = FLASHCARDS_DIRECTORY_LINUX;
      logDirectory = LOG_DIRECTORY_LINUX;
      if(onlyShowOnce){
        LogHelper.writeToLog(Level.INFO, "OS als Linux erkannt. Benutze UNIX-spezifische Pfade.");
        onlyShowOnce = false;
      }

    } else if(getOperationSystemNameLowerCase().equals("underterminded")){
      if(onlyShowOnce){
        LogHelper.writeToLog(Level.INFO, "Betriebsystem konnte nicht ermittelt werden.");
        onlyShowOnce = false;
      }
    }
  }

  public Path getFlashcardsDirectory() {
    return flashcardsDirectory;
  }

  public Path getLogDirectory() {
    return logDirectory;
  }

  //Gibt eine Liste mit den Dateinamen der Decks zurück (ohne Dateiendung).
  public List<String> getDeckNames() {

    try {

      List<String> fileNames = new ArrayList<>();
      File directory = new File(flashcardsDirectory.toString());
      File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
      if (files != null) {
        for (File file : files) {
          fileNames.add(file.getName());
        }
      }
      return fileNames;

    } catch (Exception ex) {
      LogHelper.writeToLog(Level.INFO, "fehler");
    }
    return null;
  }


  public void switchScene(ActionEvent event, String scene) {

    try{
      Parent manageUserView = FXMLLoader.load(getClass().getClassLoader().getResource("View/" + scene));
      Scene practiceViewScene = new Scene(manageUserView);
      Stage window1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
      window1.setScene(practiceViewScene);
      window1.show();
    } catch (Exception ex) {
      LogHelper.writeToLog(Level.INFO, "Fehler beim Szenenwechseln auf Szene " + scene);
    }
  }

  //TODO: Testen auf "False Positives", "mögliche Fehler"
  public String getOperationSystemNameLowerCase() {

    String os = System.getProperty("os.name").toLowerCase();
    if (os.contains("win")) {
      //Betriebssystem ist Windows-basiert
      return "windows";
    } else if (os.contains("os x") || (os.contains("os x"))) {
      //Betriebssystem ist Apple OSX
      return "osx";
    } else if (os.contains("nix") || os.contains("aix") || os.contains("nux")) {
      //Betriebssystem ist Linux/Unix basiert
      return "linux";
    }
    return "undertermined";
  }

  @SuppressWarnings("unchecked")
  public List<String> getUsersFromFile() {

    List<String> users;
    File usersFile = new File(Paths.get(flashcardsDirectory.toString(),
        "Users.txt").toString());
    if (usersFile.exists()) {

      try {
        FileInputStream fileStreamIn = new FileInputStream(Paths.get(flashcardsDirectory.toString(),
            "Users.txt").toString());
        ObjectInputStream objectStream = new ObjectInputStream(fileStreamIn);
        users = (List<String>) objectStream.readObject();
        objectStream.close();
        return users;

      } catch (ClassCastException ex) {
        LogHelper.writeToLog(Level.INFO,
            "Die gespeicherten Model.User sind womöglich veraltet. Bitte legen Sie neue Model.User an." + ex);

      } catch (Exception ex) {
        LogHelper.writeToLog(Level.INFO, "Fehler beim Einlesen der Users Datei" + ex);
      }
    }
    //Im Fehlerfall wird null zurückgegeben
    return null;
  }

  public void saveUsersToFile(List<String> users) {

    try {
      if (users != null) {
        FileOutputStream fileStreamOut = new FileOutputStream(Paths.get(
            flashcardsDirectory.toString(),
            "Users.txt").toString());
        ObjectOutputStream objectStream = new ObjectOutputStream(fileStreamOut);
        objectStream.writeObject(users);
        objectStream.close();
      }

    } catch (Exception ex) {
      LogHelper.writeToLog(Level.INFO, "Fehler beim Speichern der Model.User" + ex);
    }
  }

  public void saveUserTimeToFile(List<String> userTime) {

    try {
      if (userTime != null) {
        FileOutputStream fileStreamOut = new FileOutputStream(Paths.get(
                flashcardsDirectory.toString(),
                "UserStats.txt").toString());
        ObjectOutputStream objectStream = new ObjectOutputStream(fileStreamOut);
        objectStream.writeObject(userTime);
        objectStream.close();
      }

    } catch (Exception ex) {
      LogHelper.writeToLog(Level.INFO, "Fehler beim Speichern von der Zeit" + ex);
    }
  }
/*

  public void saveUserTimeToFile(Map<String, Integer> time) {

    try {
      if (time != null) {
        FileOutputStream fileStreamOut = new FileOutputStream(Paths.get(
                flashcardsDirectory.toString(),
                "UserStats.txt").toString());
        ObjectOutputStream objectStream = new ObjectOutputStream(fileStreamOut);
        objectStream.writeObject(time);
        objectStream.close();
      }

    } catch (Exception ex) {
      LogHelper.writeToLog(Level.INFO, "Fehler beim Speichern der Zeit" + ex);
    }
  }
*/

  public void saveDeckToFile(Deck deck) {

    if (deck != null) {

      try {
        FileOutputStream fileStreamOut = new FileOutputStream(
            Paths.get(flashcardsDirectory.toString(), deck.getName() + ".txt").toString());
        ObjectOutputStream objectStream = new ObjectOutputStream(fileStreamOut);
        objectStream.writeObject(deck);
        objectStream.close();
        LogHelper.writeToLog(Level.INFO,
            "Model.Deck " + deck.getName() + " (" + deck.getOwner() + ") gespeichtert.");

      } catch (Exception ex) {
        LogHelper.writeToLog(Level.INFO, "Fehler beim Erstellen des Ordners: " + ex);
      }
    } else {
      LogHelper.writeToLog(Level.INFO, "Model.Deck nicht gespeichert. Entweder war das Model.Deck leer, "
          + "oder das Model.Deck hatte keinen Namen.");
    }
  }

  //readObject gibt eine Warnung, da nicht sichergestellt werden kann, ob das zurückgegebene Objekt
  //tatsächlich vom Typ List<Model.Flashcard> ist. Kann unterdrückt werden, da das Objekt nur von diesem Typ sein kann.
  public Deck getDeckFromFile(String deckName) {

    Deck deck;
    Object object;

    try {

      FileInputStream fileStreamIn = new FileInputStream(
          Paths.get(flashcardsDirectory.toString(), deckName).toString());
      ObjectInputStream objectStream = new ObjectInputStream(fileStreamIn);
      object = objectStream.readObject();
      if(object instanceof Deck){
        deck = (Deck)object;
      } else {
        LogHelper.writeToLog(Level.INFO, "Die Datei konnte nicht erkannt werden.");
        return null;
      }
      objectStream.close();

    } catch (ClassCastException ex) {
      LogHelper.writeToLog(Level.INFO,
          "Die gespeicherten Stapel sind womöglich veraltet. Bitte legen Sie neue Stapel an." + ex);
      return null;

    } catch (Exception ex) {
      LogHelper.writeToLog(Level.INFO, "Fehler beim Einlesen der Speicherdatei: " + ex);
      return null;
    }
    return deck;
  }

  public void createDirectories() {

    try {

      File directory = new File(logDirectory.toString());

      if (!directory.exists()) {

        boolean isDirectoryCreated = directory.mkdirs();
        if (isDirectoryCreated) {
          LogHelper.writeToLog(Level.INFO, "Ordner erstellt in " + directory.toString());
        } else {
          LogHelper.writeToLog(Level.INFO,
              "Ordner konnte nicht erstellt werden. Vorgesehener Pfad: " + directory.toString());
        }
      }

    } catch (Exception ex) {
      LogHelper.writeToLog(Level.INFO, "Fehler beim Erstellen des Ordners: " + ex);
    }
  }

  public void createSampleDeck(String deckName, int length, String owner) {

    try {

      List<Flashcard> list = new ArrayList<>();
      Deck deck = new Deck(deckName, list, owner);

      for (int i = 0; i < length; i++) {

        int random1 = (int) (Math.random() * 50 + 1);
        int random2 = (int) (Math.random() * 100 + 1);
        //list.add(new Model.Flashcard(Integer.toString(random1), Integer.toString(random2)));
        deck.addCard(new Flashcard(Integer.toString(random1), Integer.toString(random2)));
      }
      saveDeckToFile(deck);

    } catch (Exception ex) {
      LogHelper.writeToLog(Level.INFO, "Fehler beim Erstellen von SampleData" + ex);
    }
  }
}


