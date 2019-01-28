
public class datumTest {
    public static void main(String[] args) {

        Flashcard card = new Flashcard("Vorderseite", "Rückseite");
        System.out.println("repetitionDate: " + card.getRepetitionDate());
        System.out.println("level: " + card.getLevel());
        card.setDifficulty(2);
        card.updateInterval();
        System.out.println("repetitionDate: " + card.getRepetitionDate());
        System.out.println("level: " + card.getLevel());
    }
}
