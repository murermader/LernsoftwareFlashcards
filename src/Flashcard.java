import java.util.Date;
import java.util.logging.Level;

public class Flashcard implements java.io.Serializable {

    //Abfrageintervall
    private static final int tenMinutesInMillis = 600000;           //0
    private static final int thiryMinutesInMillis = 1800000;        //1
    private static final int oneHourInMillis = 3600000;             //2
    private static final int fiveHoursInMillis = 18000000;          //3
    private static final int nineHoursInMillis = 32400000;          //4
    private static final int oneDayInMillis = 86400000;             //5
    private static final int fiveDaysInMillis = 432000000;          //6
    private static final long twentyFiveDaysInMillis = 2160000000L; //7
    private static final long fourMonthsInMillis = 10518984000L;    //8
    private static final long twoYearsInMillis = 63113904000L;      //9

    //Klassenvariablen
    static int count = 0;

    //Konstruktor
    public Flashcard(String front, String back) {
        this.front = front;
        this.back = back;
        count++;
        id = 0;
        level = 0;
        isLearned = false;
        repetitionDate = new Date();
        LogHelper.writeToLog(Level.INFO, "Flashkarte mit ID:\""+ id + "\" erstellt.");
    }

    //Instanzvariablen
    private String front;
    private String back;
    private int id;
    private int difficulty;
    private int level;
    private boolean isLearned;
    private Date repetitionDate;

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
    int getId() {
        return id;
    }
    void setId(int id) {
        this.id = id;
    }
    int getDifficulty() {
        return difficulty;
    }
    void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    void setLevel(int level){ this.level = level; }
    int getLevel(){ return level; }
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
    void updateInterval(){

        switch(difficulty){
            case(0): //Difficulty 0 --> direkt nocheinmal wiederholen, "kein update"
                break;
            case(1): //Difficulty 1 --> leicht, Zwei Stufen nach oben
                level = level + 2;
                updateTime();
                break;
            case(2): //Difficulty 2 --> OK, eine Stufe nach oben
                level++;
                updateTime();
                break;
            case(3): //Difficulty 3 --> Schwer, Stufe beibehalten
                updateTime();
                break;
            case(4): //Difficulty 4 --> Sehr schwer, Stufe um eins verrinngern
                if(level > 0){
                    level = level - 1;
                }
                updateTime();
                break;
        }
    }

    private void updateTime(){

        Date date = new Date();
        long newTime = 0;

        switch(this.level){
            case(0):
                newTime = tenMinutesInMillis;
                break;
            case(1):
                newTime = thiryMinutesInMillis;
                break;
            case(2):
                newTime = oneHourInMillis;
                break;
            case(3):
                newTime = fiveHoursInMillis;
                break;
            case(4):
                newTime = nineHoursInMillis;
                break;
            case(5):
                newTime = oneDayInMillis;
                break;
            case(6):
                newTime = fiveDaysInMillis;
                break;
            case(7):
                newTime = twentyFiveDaysInMillis;
                break;
            case(8):
                newTime = fourMonthsInMillis;
                break;
            case(9):
                newTime = twoYearsInMillis;
                break;
        }
        date.setTime(System.currentTimeMillis() + newTime);
        LogHelper.writeToLog(Level.INFO,
                "Nächstes Abfragedatum für Karte (" + id + ") ist am: " + date);
    }
}