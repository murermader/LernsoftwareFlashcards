import java.util.Date;

public class datumTest {
    public static void main(String[] args) {

        //Beispiel Initialisierung Flashkarte
        //Flashcard card = new Flashcard("Vorderseite", "Rückseite");

        //Test Datum Vergleichen
        Date date = new Date();
        date.setTime(System.currentTimeMillis() + 10000);
        //System.out.println(card.getRepetitionDate().before(date));
        System.out.println("date objekt: " + date);
        //System.out.println("repetitionDate: " + card.getRepetitionDate());
    }
}
