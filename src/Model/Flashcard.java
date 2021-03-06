package Model;

import java.util.Date;
import java.util.logging.Level;

//Lernkarte
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
    private static int count = 0;

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
        //Model.LogHelper.writeToLog(Level.INFO, "Flashkarte mit ID:\""+ id + "\" erstellt.");
    }

    //Copy Constructor
    public Flashcard(Flashcard anotherFlashcard){

        this.front = anotherFlashcard.front;
        this.back = anotherFlashcard.back;
        this.level = anotherFlashcard.level;
        this.isLearned = anotherFlashcard.isLearned;
        this.repetitionDate = anotherFlashcard.repetitionDate;
        this.difficulty = anotherFlashcard.difficulty;
    }


    //Getter & Setter
    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setLevel(int level) {

        if (level < 11) {
            this.level = level;
        } else {
            LogHelper.writeToLog(Level.INFO, "Level kann nicht größer als 10 gesetzt werden");
            this.level = 9;
        }
    }

    public int getLevel() {
        return level;
    }

    public boolean getLearned() {
        return isLearned;
    }

    public void setLearned(boolean isLearned) {
        this.isLearned = isLearned;
    }

    public Date getRepetitionDate() {
        return repetitionDate;
    }

    public void setRepetitionDate(Date repetitionDate) {
        this.repetitionDate = repetitionDate;
    }

    //updateInterval
    public void updateInterval() {

        switch (difficulty) {
            case (0): //Difficulty 0 --> direkt nocheinmal wiederholen, "kein update"
                break;
            case (1): //Difficulty 1 --> leicht, zwei Stufen nach oben
                setLevel(level + 2);
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
                    setLevel(level - 1);
                }
                updateTime();
                break;
        }
    }

    public String returnTimeIntervalAsString(int level) {

        switch (level) {
            case (0):
                return "10min";
            case (1):
                return "30min";
            case (2):
                return "1h";
            case (3):
                return "5h";
            case (4):
                return "9h";
            case (5):
                return "1d";
            case (6):
                return "5d";
            case (7):
                return "25d";
            case (8):
                return "4m";
            case (9):
                return "2y";
        }
        return null;
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
        setRepetitionDate(date);
        LogHelper.writeToLog(Level.INFO,
                "Nächstes Abfragedatum für Karte (" + front + ") ist am: " + date + " | Level: " + level);
    }
}