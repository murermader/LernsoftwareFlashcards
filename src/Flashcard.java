import java.text.SimpleDateFormat;
import java.util.Date;

public class Flashcard {

    //Konstruktor
    public Flashcard(String front, String back) {
        //Eingabe des Users
        this.front = front;
        this.back = back;

        //soll dynamisch erstellt werden
        id = 0;
        isLearned = false;
        repetitionDate = new Date();
        //repetitionDate = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    }

    //Instanzvariablen
    private String front;
    private String back;
    private int id;
    private int difficulty;
    private boolean isLearned;
    private Date repetitionDate;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public boolean getLearned() {
        return isLearned;
    }

    public void setLearned(boolean isLearned) {
        this.isLearned = isLearned;
    }

    Date getRepetitionDate() {
        return repetitionDate;
    }

    public void setRepetitionDate(Date repetitionDate) {
        this.repetitionDate = repetitionDate;
    }
}