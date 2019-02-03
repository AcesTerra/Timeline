
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.print.DocFlavor;

/**
 *
 * @author Alfredo Emmanuel Garcia Falcon
 */
public class Game {
    
    public static void main(String[] args) throws FileNotFoundException, IOException
    {   
        //Declare deck, graveyard, table and players
        ArrayList<Card> deck = new ArrayList<>();
        ArrayList<Card> table = new ArrayList<>();
        ArrayList<Card> graveyard = new ArrayList<>();
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Integer> playersWithNoCards = new ArrayList<>();
        int positionOfDeck = 0;
        int indexOfWinner = 0;
        
        boolean thereIsAWinner = false;
        boolean cardPlacedCorrectly = false;
        
        //Read file
        String readedCard;
        
        BufferedReader br = null;
	FileReader fr = null;
        
        fr = new FileReader("src/cards.txt");
	br = new BufferedReader(fr);
        
        String currentLine;
        
        //Read every line of txt archive and create a card with name and date and add it to deck
        while((currentLine = br.readLine()) != null)
        {
            readedCard = currentLine;
            String[] splitedCard = readedCard.split("/");
            String name = splitedCard[0];
            String sEventDate = splitedCard[1];
            int eventDate = Integer.parseInt(sEventDate);
            deck.add(new Card(name, eventDate));
        }
                
        fr.close();
        br.close();
        
        //Shuffle decj
        Collections.shuffle(deck);
        
        //Print suffled deck
        for(int i = 0; i < deck.size(); i++)
        {
            System.out.print("Name: " + deck.get(i).getName() + " Date: " + deck.get(i).getEventDate() + "\n");
        }
        
        //Declare the number of players
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of players");
        int numberOfPlayers = scan.nextInt();
        scan.nextLine();
        for(int i = 0; i < numberOfPlayers; i++)
        {
            System.out.println("Enter the name of player " + (i+1));
            String playerName = scan.nextLine();
            players.add(new Player(playerName));
        }
        
        //Print name of players
        for(int i = 0; i < players.size(); i++)
        {
            System.out.println(players.get(i).getName());
        }
        
        //Assign cards to players
        for(int i = 0; i < players.size(); i++)
        {
            for(int j = 0; j < 4; j++)
            {
                players.get(i).cards.add(deck.get(positionOfDeck));
                positionOfDeck++;
            }
        }
        
        //Print players cards
        for(int i = 0; i < players.size(); i++)
        {
            System.out.println(players.get(i).getName());
            for(int j = 0; j < 4; j++)
            {
                System.out.println(players.get(i).cards.get(j).getName());
            }
        }
        
        //Remove assigned cards
        for(int i = 0; i < (numberOfPlayers*4); i++)
        {
            deck.remove(0);
        }
        
        //Puts a card in the table and removes it from deck
        table.add(deck.get(0));
        deck.remove(0);
        
        //Print deck without assigned cards
        for(int i = 0; i < deck.size(); i++)
        {
            System.out.println(deck.get(i).getName());
        }
        
        //Main game
        while(thereIsAWinner == false)
        {
            //Read every player and place the card
            for(int i = 0; i < players.size(); i++)
            {
                //Print the cards on table
                System.out.println("Table: ");
                for(int j = 0; j < table.size(); j++)
                {
                    System.out.print((j+1) + ")" + table.get(j).getName() + "-" + table.get(j).getEventDate() + " ");
                }
                System.out.println("");
                //Print the name of player
                System.out.println(players.get(i).getName() + " it´s your turn and here are your cards");
                //Print every card of player
                for(int j = 0; j < players.get(i).cards.size(); j++)
                {
                    System.out.print((j+1) + ")" + players.get(i).cards.get(j).getName() + " ");
                }
                //Enter the position of card to place minus 1 because arraylist position start in cero.
                System.out.println("\nEnter the card you want to place");
                int positionOfCardToPlace = scan.nextInt() - 1;
                //Enter the position of table to place the card
                System.out.println("Enter 0 if you want to place it at the beggining, Enter 99 if you want to place it at the end or enter befor which card you want to place it");
                int positionOfCardOnTable = scan.nextInt();
                //If player enters the first position
                if(positionOfCardOnTable == 0)
                {
                    if(players.get(i).cards.get(positionOfCardToPlace).getEventDate() < table.get(positionOfCardOnTable).getEventDate())
                    {
                        table.add(positionOfCardOnTable, players.get(i).cards.get(positionOfCardToPlace));
                        cardPlacedCorrectly = true;
                    }
                }
                //If player enters the last position
                if(positionOfCardOnTable == 99)
                {
                    if(players.get(i).cards.get(positionOfCardToPlace).getEventDate() > table.get(table.size()-1).getEventDate())
                    {
                        table.add(players.get(i).cards.get(positionOfCardToPlace));
                        cardPlacedCorrectly = true;
                    }
                }
                //If player enters the position of card 1
                if(positionOfCardOnTable == 1)
                {
                    if(players.get(i).cards.get(positionOfCardToPlace).getEventDate() < table.get(positionOfCardOnTable-1).getEventDate())
                    {
                        table.add(positionOfCardOnTable-1, players.get(i).cards.get(positionOfCardToPlace));
                        cardPlacedCorrectly = true;
                    }
                }
                //If player enters a position different of 0, 1 or last
                if(positionOfCardOnTable > 1 && positionOfCardOnTable < 99)
                {
                    if(players.get(i).cards.get(positionOfCardToPlace).getEventDate() < table.get(positionOfCardOnTable-1).getEventDate())
                    {
                        table.add(positionOfCardOnTable-1, players.get(i).cards.get(positionOfCardToPlace));
                        cardPlacedCorrectly = true;
                    }
                }
                //If card placed correctly it removes form hand of player
                if(cardPlacedCorrectly == true)
                {
                    System.out.println("Card placed correctly!");
                    players.get(i).cards.remove(positionOfCardToPlace);
                    //If player runs out of cards it adds the index of player to an arraylist
                    if(players.get(i).cards.size() == 0)
                    {
                        playersWithNoCards.add(i);
                        players.get(i).winner = true;
                    }
                }
                //If player place incorrectly a card it will move it to graveyard and it will take another one from deck
                if(cardPlacedCorrectly == false)
                {
                    System.out.println("Card placed incorrectly!");
                    System.out.println(players.get(i).cards.get(positionOfCardToPlace).getName() + "-" + players.get(i).cards.get(positionOfCardToPlace).getEventDate());
                    graveyard.add(players.get(i).cards.get(positionOfCardToPlace));
                    players.get(i).cards.remove(positionOfCardToPlace);
                    players.get(i).cards.add(deck.get(0));
                    deck.remove(0);
                }
                cardPlacedCorrectly = false;
                if(deck.size() == 0)
                {
                    Collections.shuffle(graveyard);
                    deck.addAll(graveyard);
                    graveyard.clear();
                }
            }
            if(playersWithNoCards.size() == 1)
            {
                thereIsAWinner = true;
                indexOfWinner = playersWithNoCards.get(0);
            }
            if(playersWithNoCards.size() > 1)
            {
                for(int i = 0; i < playersWithNoCards.size(); i++)
                {
                    players.get(playersWithNoCards.get(i)).cards.add(deck.get(0));
                    deck.remove(0);
                    if(deck.size() == 0)
                    {
                        Collections.shuffle(graveyard);
                        deck.addAll(graveyard);
                        graveyard.clear();
                    }
                }
                playersWithNoCards.clear();
            }
        }
        System.out.println("The winner is " + players.get(indexOfWinner).getName());
    }
}
