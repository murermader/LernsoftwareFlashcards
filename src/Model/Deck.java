package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

// Ein Model.Deck ist eine Zusammenfassung von mehreren Flashcards, die einem Model.User zugeteilt wurde.
public class Deck implements Serializable {

    private String name;
    private List<Flashcard> cards;
    private String owner;

    //Konstruktur
    public Deck(String name, List<Flashcard> cards, String owner) {
        this.owner = owner;
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

        try {
            Date date = new Date();
            date.getTime();
            List<Flashcard> toRemove = new ArrayList<>();
            for (Flashcard card : cards) {
                if (card.getRepetitionDate().after(date)) {
                    toRemove.add(card);
                }
            }
            cards.removeAll(toRemove);

        } catch (Exception ex) {
            LogHelper.writeToLog(Level.INFO, "Fehler beim Aufbereiten des Decks. " + ex);
        }
    }

    //Sortiert das Model.Deck nach dem Datum. Kleinstes Datum zuerst.
    public void sort() {

        try {
            if (cards != null) {
                cards.sort(Comparator.comparing(Flashcard::getRepetitionDate));
                LogHelper.writeToLog(Level.INFO, "Karten von Model.Deck " + name + " sortiert.");

            } else {
                LogHelper.writeToLog(Level.INFO, "Karten von Model.Deck " + name + " nicht sortiert (null).");
            }
        } catch (Exception ex) {
            LogHelper.writeToLog(Level.INFO, "Fehler beim Sortieren der Karten" + ex);
        }
    }

    public Flashcard getCardByName(String front){
        for (Flashcard flashcard: cards)
        {
         if (front.equals(flashcard.getFront())){
             return flashcard;
         }
        }
        return null;
    }

    public void addCard(Flashcard card) {
        cards.add(card);
    }

    public void removeCard(Flashcard card) {
        cards.remove(card);
    }

    public String getOwner() {
        return owner;
    }
}
