import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Deck {

  private String name;
  private List<Flashcard> cards;
  private static List<String> nameList = new ArrayList<>();

  Deck(String name, List<Flashcard> cards) {
    setName(name);
    this.cards = cards;
    nameList.add(name);
  }

  private void setName(String name) {
    if (name.contains(".txt")) {
      this.name = name.replace(".txt", "");
    } else {
      this.name = name;
    }
  }

  //Entfernt alle Karten deren Abfragedatum noch nicht erreicht wurde
  void ready() {

    Date date = new Date();
    date.getTime();
    for (Flashcard card : cards) {
      if (card.getRepetitionDate().after(date)) {
        cards.remove(card);
      }
    }
  }

  void addCard(Flashcard card) {
    cards.add(card);
  }

  String getName() {
    return name;
  }

  void setCards(List<Flashcard> cards) {
    this.cards = cards;
  }

  List<Flashcard> getCards() {
    return cards;
  }
}
