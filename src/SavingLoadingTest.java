import java.util.ArrayList;
import java.util.List;

public class SavingLoadingTest {
    public static void main(String[] args) {

        List<Flashcard> list = new ArrayList<>();
        Helper helper = new Helper();

        List<String> neueListe = helper.getDeckNames();

        for (String name: neueListe) {
            System.out.println(name);
        };

        //Belastungstest
        for (int i = 0; i < 10; i++) {

            int random3 = (int)(Math.random() * 70 + 1);
            int random5 = (int)(Math.random() * 800 + 1);
            list.add(new Flashcard(Integer.toString(random3), Integer.toString(random5)));
        }

        helper.saveListAsFile(list, "test4.txt");

        List<Flashcard> newList;
        newList = helper.FlashcardListFromFile("test2.txt");

        for (Flashcard card : newList) {
            System.out.println("---------A N F A N G--------");
            System.out.println("Vorderseite: " +card.getFront());
            System.out.println("Rückseite: " +card.getBack());
            System.out.println("ID: " +card.getId());
            System.out.println("Difficulty: " +card.getDifficulty());
            System.out.println("RepetitionDate: " +card.getRepetitionDate());
            System.out.println("isLearned: " +card.getLearned());
        }
    }
}
