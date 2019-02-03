/**
 *
 * @author Alfredo Emmanuel Garcia
 */
public class Card {
    private String name;
    private int eventDate;

    public Card(String name, int eventDate)
    {
        setName(name);
        setEventDate(eventDate);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEventDate() {
        return eventDate;
    }

    public void setEventDate(int eventDate) {
        this.eventDate = eventDate;
    }
    
    
}
