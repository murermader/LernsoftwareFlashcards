import java.util.Date;
import java.util.logging.Level;

public class Flashcard implements java.io.Serializable {

  //Abfrageintervall
  private static final int TEN_MINUTES_IN_MILLIS = 600000;            //0
  private static final int THIRY_MINUTES_IN_MILLIS = 1800000;         //1
  private static final int ONE_HOUR_IN_MILLIS = 3600000;              //2
  private static final int FIVE_HOURS_IN_MILLIS = 18000000;           //3
  private static final int NINE_HOURS_IN_MILLIS = 32400000;           //4
  private static final int ONE_DAY_IN_MILLIS = 86400000;              //5
  private static final int FIVE_DAYS_IN_MILLIS = 432000000;           //6
  private static final long TWENTY_FIVE_DAYS_IN_MILLIS = 2160000000L; //7
  private static final long FOUR_MONTHS_IN_MILLIS = 10518984000L;     //8
  private static final long TWO_YEARS_IN_MILLIS = 63113904000L;       //9

  //Klassenvariablen
  public static int count = 0;

  //Instanzvariablen
  private String front;
  private String back;
  private int difficulty;
  private int level;
  private boolean isLearned;
  private Date repetitionDate;

  //Konstruktor
  public Flashcard(String front, String back) {
    this.front = front;
    this.back = back;
    count++;
    level = 0;
    isLearned = false;
    repetitionDate = new Date();
    //LogHelper.writeToLog(Level.INFO, "Flashkarte mit ID:\""+ id + "\" erstellt.");
  }

  //Getter & Setter
  String getFront() {
    return front;
  }

  void setFront(String front) {
    this.front = front;
  }

  String getBack() {
    return back;
  }

  void setBack(String back) {
    this.back = back;
  }

  int getDifficulty() {
    return difficulty;
  }

  void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }

  void setLevel(int level) {
    if(level < 10){
      this.level = level;
    } else{
      LogHelper.writeToLog(Level.INFO, "Level kann nicht größer als 10 gesetzt werden");
      this.level = 9;
    }
  }

  int getLevel() {
    return level;
  }

  boolean getLearned() {
    return isLearned;
  }

  void setLearned(boolean isLearned) {
    this.isLearned = isLearned;
  }

  Date getRepetitionDate() {
    return repetitionDate;
  }

  void setRepetitionDate(Date repetitionDate) {
    this.repetitionDate = repetitionDate;
  }

  //updateInterval
  void updateInterval() {
    switch (difficulty) {
      case (0): //Difficulty 0 --> direkt nocheinmal wiederholen, "kein update"
        break;
      case (1): //Difficulty 1 --> leicht, zwei Stufen nach oben
        setLevel(level+2);
        updateTime();
        break;
      case (2): //Difficulty 2 --> OK, eine Stufe nach oben
        setLevel(level++);
        updateTime();
        break;
      case (3): //Difficulty 3 --> Schwer, Stufe beibehalten
        updateTime();
        break;
      case (4): //Difficulty 4 --> Sehr schwer, Stufe um eins verringern
        if (level > 0) {
          level = level - 1;
        }
        updateTime();
        break;
    }
  }

  public long returnTimeInterval(int level){

    long newTime = 0;
    switch (level) {
      case (0):
        newTime = TEN_MINUTES_IN_MILLIS;
        break;
      case (1):
        newTime = THIRY_MINUTES_IN_MILLIS;
        break;
      case (2):
        newTime = ONE_HOUR_IN_MILLIS;
        break;
      case (3):
        newTime = FIVE_HOURS_IN_MILLIS;
        break;
      case (4):
        newTime = NINE_HOURS_IN_MILLIS;
        break;
      case (5):
        newTime = ONE_DAY_IN_MILLIS;
        break;
      case (6):
        newTime = FIVE_DAYS_IN_MILLIS;
        break;
      case (7):
        newTime = TWENTY_FIVE_DAYS_IN_MILLIS;
        break;
      case (8):
        newTime = FOUR_MONTHS_IN_MILLIS;
        break;
      case (9):
        newTime = TWO_YEARS_IN_MILLIS;
        break;
    }
    return newTime;
  }

  private void updateTime() {

    Date date = new Date();
    long newTime = 0;

    switch (this.level) {
      case (0):
        newTime = TEN_MINUTES_IN_MILLIS;
        break;
      case (1):
        newTime = THIRY_MINUTES_IN_MILLIS;
        break;
      case (2):
        newTime = ONE_HOUR_IN_MILLIS;
        break;
      case (3):
        newTime = FIVE_HOURS_IN_MILLIS;
        break;
      case (4):
        newTime = NINE_HOURS_IN_MILLIS;
        break;
      case (5):
        newTime = ONE_DAY_IN_MILLIS;
        break;
      case (6):
        newTime = FIVE_DAYS_IN_MILLIS;
        break;
      case (7):
        newTime = TWENTY_FIVE_DAYS_IN_MILLIS;
        break;
      case (8):
        newTime = FOUR_MONTHS_IN_MILLIS;
        break;
      case (9):
        newTime = TWO_YEARS_IN_MILLIS;
        break;
    }
    date.setTime(System.currentTimeMillis() + newTime);
    LogHelper.writeToLog(Level.INFO,
        "Nächstes Abfragedatum für Karte (" + front + ") ist am: " + date + " Level: " + level);
  }
}