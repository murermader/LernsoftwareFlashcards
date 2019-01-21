import java.util.Date;

public class Flashcard {

    //Konstruktor
    public Flashcard(String front, String back) {
        //Eingabe des Users
        this.front = front;
        this.back = back;

        //ID muss einmalig sein & darf nur einmal vergeben werden.
        //ID darf erst vergeben werden, nachdem die schon benutzten IDs bekannt sind.
        id = 0;
        //Überhaupt nötig? Man kann ja am Datum erkennen (wann die Karte wieder gelernt werdenn soll)
        //ob die Karte "schon gelernt" ist oder nicht
        isLearned = false;
        repetitionDate = new Date();
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
        //Überprüfung valide Eingabe.
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        //Überprüfung valide Eingabe.
        this.back = back;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        //Überprüfung ob ID schon vorhanden ist
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
        //Überprüfung ob neues Datum vor altem Datum liegt.
        this.repetitionDate = repetitionDate;
    }
}