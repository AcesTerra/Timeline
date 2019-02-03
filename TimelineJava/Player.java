import java.util.ArrayList;

/**
 *
 * @author Alfredo Emmanuel Garcia Falcon
 */
public class Player {
    private String name;
    ArrayList<Card> cards = new ArrayList<>();
    boolean winner = false;
    
    public Player(String name)
    {
        setName(name);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
