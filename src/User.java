import java.io.Serializable;

public class User implements Serializable {

  private String name;
  private int numberOfDecks;
  private int numberOfCards;
  private int cardsLearned;
  private long timeSpentLearning; //Zeiteinheit?

  User(String name){
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCardsLearned() {
    return cardsLearned;
  }

  public void setCardsLearned(int cardsLearned) {
    this.cardsLearned = cardsLearned;
  }

  public long getTimeSpentLearning() {
    return timeSpentLearning;
  }

  public void setTimeSpentLearning(long timeSpentLearning) {
    this.timeSpentLearning = timeSpentLearning;
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

  public void setNumberOfCards(int numberOfCards) {
    this.numberOfCards = numberOfCards;
  }
}
