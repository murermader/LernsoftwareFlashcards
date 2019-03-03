package Model;

import java.io.Serializable;

public class UserStats implements Serializable {

  private String name;
  private int numberOfDecks;
  private int numberOfCards;
  private int lenght;
  private Helper helper = new Helper();
  private Data data = new Data();

  public UserStats(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getNumberOfDecks() {
    return numberOfDecks;
  }

  public void setNumberOfDecks(int numberOfDecks) {
    this.numberOfDecks = numberOfDecks;
  }

  public int getNumberOfCards() {
    return numberOfCards;
  }

  public void setNumberOfCards() {
    for (Deck deck : data.getListOfDecks()) {
      int l = deck.getLength();
      lenght += l;
    }
    numberOfCards = lenght;
  }
}