
import java.util.ArrayList;
import java.io.Serializable;
import java.awt.Color;

//The abstract player class.

public abstract class Player implements Serializable
{
	
	//the player attributes
	protected String name;
	protected Color color;
	private Card card;
	private Deck deck;
	protected ArrayList<Card> cards;
	protected ArrayList<Country> playerCountries;
	protected ArrayList<Continent> playerContinents;
	//protected int countryArmies;
	//number of armies
	
	public int unplacedArmies;
	
	public Player(){
		this.playerCountries = new ArrayList<>();
	
	}
	
	public ArrayList<Card> getCards(){
		return this.cards;						//method returns an array of cards
	}// end of method getCards
	
	// return the size of the players hand
	public int getCardHandSize(){
		return this.cards.size();
	}
	
	public int getUnplacedArmies(){
		return this.unplacedArmies;
	}
	public Deck getDeck(){
		return this.deck;
	}
	
	public int decrementArmies( int numArmiesLost ){
		
		unplacedArmies -=  numArmiesLost;			// decrement players armies
		
		return unplacedArmies;
	}// end of method decrementArmies
	
	public int incrementArmies( int numArmiesGained ){
		
		unplacedArmies += numArmiesGained;			// increments players armies
		
		return unplacedArmies;
	}// end of method incrementArmies

	public void addCountry( Country country ){
		
		playerCountries.add(country);		// adds a country
		
	}// end of method addCountry
	
	public void addCoutry( ArrayList<Country> countriesList) {
		playerCountries.addAll(countriesList);		// adds multiple country
	}// end of method addCountry
	
	public void removeCountry( Country country ){
		
		playerCountries.remove(country); 		//removes a country
	}// end of method removeCountry
	
	public void addContinent( Continent continent ){
		
		playerContinents.add(continent);			// adds a continent
	}//end of method addContinent
	
	public void removeContinents( Continent continent ){
		
		playerContinents.remove(continent);			// removes a continent
	}// end of method removeContinents
	
	public ArrayList<Country> getOwnedCountries(){
		return this.playerCountries;
	}
	
	public String getContriesAsString(){
		return playerCountries.toString();
	}
	
	
	public abstract Action getNextAction();{						
														//Actions done by computer or human player.
	}// end of method getNextAction
	
	
	public abstract Country getAttackingCountry();		// method that gets attacking country
														
	public abstract Country getAttackedCountry();		//method that get the attacking country
														
	public abstract int getAttackDiceCount();			//method that gets the attackers dice count
	
	public abstract int getDefendDiceCount();			//method gets the defenders dice count
		
	public abstract Country getClaimedCountry();		//method claims unoccupied territory
														
	public abstract Country getOldCountry();		//method gets old countries
													
	public abstract Country getNewCountry();		//method gets new countries
														
	public abstract int getMovedArmyCount();		//method determines the moved army count 
	
	public void setName( String name ){
		this.name = name;							//method sets the players name
	}// end of method setName
	
	public String getName(){
		
		return name;
	}
	
	public void setColor (Color color){
		this.color = color;							//method sets the players color
	}// end of method setColor
	
	public Color getColor(){
		
		return color;
	}
	
}// end of class Player
