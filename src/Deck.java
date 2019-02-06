import java.util.ArrayList;
import java.util.List;

public class Deck {

    private String name;
    private List<Flashcard> cards;
    static List<String> nameList = new ArrayList<>();

    Deck(String name, List<Flashcard> cards){
        this.name = name;
        this.cards = cards;
        nameList.add(name);
    }
    private void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public List<Flashcard> getCards(){ return cards;}
}
