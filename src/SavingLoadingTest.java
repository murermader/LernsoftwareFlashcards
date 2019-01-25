import java.util.ArrayList;
import java.util.List;

public class SavingLoadingTest {
    public static void main(String[] args) {

        List<Flashcard> list = new ArrayList<>();
        LoadAndSaveData data = new LoadAndSaveData();

        //Belastungstest
        for (int i = 0; i < 10000; i++) {

            int random1 = (int)(Math.random() * 50 + 1);
            int random2 = (int)(Math.random() * 100 + 1);
            list.add(new Flashcard(Integer.toString(random1), Integer.toString(random2)));
        }

        data.save(list);

        List<Flashcard> newList;
        newList = data.read();

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
