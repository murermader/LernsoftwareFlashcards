import java.util.Date;
import java.util.logging.Level;

public class Flashcard implements java.io.Serializable {

    //Klassenvariablen
    static int count = 0;

    //Konstruktor
    public Flashcard(String front, String back) {
        this.front = front;
        this.back = back;
        count++;

        id = 0;
        isLearned = false;
        repetitionDate = new Date();
        LogHelper.writeToLog(Level.INFO, "Flashkarte mit ID:\""+ id + "\" erstellt.");
    }

    //Instanzvariablen
    private String front;
    private String back;
    private int id;
    private int difficulty;
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
}