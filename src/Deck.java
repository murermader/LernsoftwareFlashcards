import java.util.Date;
import java.util.List;

//Alle Sachen die nur ein Deck bzw. die Inhalte eines Decks betreffen.
public class Deck {

    private String name;
    private List<Flashcard> cards;

    //Konstruktur
    Deck(String name, List<Flashcard> cards) {

        setName(name);
        this.cards = cards;
    }

    //Getter & Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {

        if (name.contains(".txt")) {
            this.name = name.replace(".txt", "");
        } else {
            this.name = name;
        }
    }

    public List<Flashcard> getCards() {
        return cards;
    }

    public void setCards(List<Flashcard> cards) {
        this.cards = cards;
    }

    //Methoden
    public int getLength() {
        return cards.size();
    }

    //Entfernt alle Karten deren Abfragedatum noch nicht erreicht wurde
    public void ready() {

        Date date = new Date();
        date.getTime();
        for (Flashcard card : cards) {
            if (card.getRepetitionDate().after(date)) {
                cards.remove(card);
            }
        }
    }

    public void addCard(Flashcard card) {
        cards.add(card);
    }

    public void removeCard(Flashcard card) {
        cards.remove(card);
    }

    public void editCard(Flashcard card, String front, String back, boolean resetProgress) {

        int index;
        if (cards.contains(card)) {
            index = cards.indexOf(card);
            //Überprüfen ob Werte tatsächlich geändet wurden?
            //Vielleicht falsche Usereingabe etc
            cards.get(index).setFront(front);
            cards.get(index).setBack(back);

            if(resetProgress){

                cards.get(index).setRepetitionDate(new Date());
                cards.get(index).setLevel(0);
            }
        }
    }
}
