import java.util.ArrayList;
import java.util.List;

public class Deck {

    private String name;
    private List<Flashcard> cards;
    static List<String> nameList = new ArrayList<>();

    Deck(String name, List<Flashcard> cards){
        setName(name);
        this.cards = cards;
        nameList.add(name);
    }
    private void setName(String name){
        if(name.contains(".txt")){
            this.name = name.replace(".txt", "");
        }
        else{
            this.name = name;
        }
    }
    public String getName(){
        return name;
    }
    public List<Flashcard> getCards(){ return cards;}
}
