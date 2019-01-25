import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

public class Main {

    private static final Scanner userInput = new Scanner(System.in);
    private static List<Flashcard> allFlashcards = new ArrayList<>();
    private static int iteration = 0;

    public static void main(String[] args) {

        //BEISPIELHAFTER ABLAUF DER SOFTWARE ALS KONSOLENANWENDUNG
        try{

            System.out.println(
                    "Willkommen in der Lernsoftware:\n" +
                            "Um eine Flashcard zu erstellen, geben Sie die 1 ein. \n" +
                            "Um mit dem Lernen zu beginnen, geben Sie die 2 ein \n" +
                            "Um das Programm zu  beenden, geben Sie die 3 ein. ");
            int userSelection = userInput.nextInt();

            while(userSelection != 3){
                if(iteration != 0){
                    System.out.println("1 Erstellen | 2 Lernen | 3 Beenden");
                    userSelection = userInput.nextInt();
                }
                iteration++;
                switch (userSelection){
                    case(1): //Karte erstellen
                        System.out.println("Geben Sie ein, was auf der Vorderseite stehen soll:");
                        userInput.nextLine();
                        String front = userInput.next();
                        System.out.println("Geben Sie ein, was auf der Rückseite stehen soll:");
                        userInput.nextLine();
                        String back = userInput.nextLine();

                        Flashcard newCard = new Flashcard(front, back);

                        newCard.setBack(back);
                        if((newCard.getFront() != null && (newCard.getBack() != null))){
                            allFlashcards.add(newCard);
                        }

                        break;
                    case(2): //Lernen
                        if (allFlashcards.size() == 0){
                            System.out.println("Sie haben noch keine Flashkarten erstellt!");
                            break;
                        }
                        for (Flashcard flashcard : allFlashcards) {

                            System.out.println("------------------------------------");
                            System.out.println("Vorderseite: " + flashcard.getFront());
                            System.out.println("Drücken Sie eine belibige Taste um die Rückseite zu sehen.");
                            if (userInput.next() != null) {
                                System.out.println("---------------");
                                System.out.println("Antwort: " + flashcard.getBack());
                                System.out.println("---------------");
                            }
                            System.out.println("Schwieriegkeit? \n" +
                                    "1. Leicht \n" +
                                    "2. OK \n" +
                                    "3. Schwierig \n" +
                                    "4. Direkt Wiederholen");
                            int difficulty = userInput.nextInt();
                            flashcard.setDifficulty(difficulty);
                        }
                        break;
                }
            }
            if(allFlashcards.size() > 0){
                System.out.println("------------------DEBUG---------------------");
                System.out.println("Flashkarte vorne:" + allFlashcards.get(0).getFront() +
                        "\n Flashkarte hinten:" +allFlashcards.get(0).getBack());
                System.out.println(allFlashcards.get(0).getDifficulty());
                System.out.println(Flashcard.count);
            }
        }
        catch(Exception ex){
            LogHelper.writeToLog(Level.INFO, "Fehler in Main: " +ex);
        }
        finally{
            LogHelper.writeToLog(Level.INFO, "Programm beendet.");
        }
    }
}
