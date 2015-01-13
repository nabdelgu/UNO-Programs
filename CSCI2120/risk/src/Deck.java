
import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;
import java.io.Serializable;

public class Deck implements Serializable
{
	// deck attributes
	protected ArrayList<Card> cards;
	private Player player;
	ArrayList<Card> cardsTradedIn;
	int timesTradedIn = 0;
	private Game game;
	
	public int tradeInCards( ArrayList<Card> playersCards) {
		//Check cards?
		for (Card card : playersCards)
		{
			this.cardsTradedIn.add(card);		
		}//end of for loop
		int armies = 5;
		if (timesTradedIn < 5)								//increases the number of armies every time cards are traded in
		{
			armies = 4 + 2*timesTradedIn;
		}// end of if statement
		if (timesTradedIn == 5)
		{
			armies = 15;
		}// end of if statement
		timesTradedIn++;
		return armies;
	}// end of method tradeInCards
	
	public Deck(Board board){
		this.cards = new ArrayList<Card>();
		Design[] designs = Design.values();
		ArrayList<Country> countries = Continent.getCountries();
		int i = 0;
		//Add regular cards for each country.
		for (Country country : countries){
			Design design = designs[i];
			Card card = new Card(design,country);
			this.cards.add(card);
			i = (i + 1) % Design.values().length;
		}// end of for loop										// populates the deck with cards
		//Add wild cards.
		for (i = 0; i < 30; i++){
			cards.add(new Card());
		}// end of for loop
			
	}// end 1 argument constructor
	
	public void Shuffle(){
		Collections.shuffle(this.cards);					// shuffles the deck
	}//end of method Shuffle
	
	public Card drawCard(){
		return this.cards.remove(this.cards.size()-1);		//draws a Card
	}//end of method Card
	
	public void add(Card card){		// adds a cards to the deck
		
		cards.add(card);
		
	}// end of method add
	public ArrayList<Card> getCards(){
		return this.cards;
	}
	
	public void addToPlayersCards( Card card){
		
	 this.player.cards.add(card);	// add a card to a players hand and removes it from the deck
	 cards.remove(card);
	 
	}
	

} // end of class Deck