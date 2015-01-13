
import java.util.ArrayList;
import java.io.Serializable;

public class Country implements Serializable
{
	
	//Country attributes
	private String name;
	private Continent continent;
	private ArrayList<Country> adjacentCountries;
	
	//Game Attributes.
	protected int numOfArmies;
	private Player playerOccupant;	

	public Country( String name, Continent continent){
		this.name = name;
		this.continent = continent;
		this.adjacentCountries = new ArrayList<Country>();				//2 argument constructor
		this.numOfArmies = 0;
		this.playerOccupant = null;
	}//end of 2 argument constructor
	
	@Override
	public String toString(){
		return this.name;
	}
	
	public Country(String name){
		this(name,null);				// 1 argument constructor
	}// end of 1 argument constructor
	
	public void setContinent(Continent continent){
		this.continent = continent;					// sets the continent
	}// end of method setContinent
	
	public Continent getContinent(){
		return this.continent;			// gets the continent
	}// end of method getContinent
	
	public void makeAdjacentTo(Country country){
		this.adjacentCountries.add(country);		// makes counties adjacent
	}// end of method makeAdjacentTo
	
	public String getName(){
		
		return name;			// gets the countries name
	}// end of method get name
	
	public ArrayList<Country> getAdjacencies(){
		return this.adjacentCountries;		// gets the adjacent counties
	}// end of method getAdjacencies
	
	public int getBonusArmies(){
		
		return numOfArmies;			// gets the number of bonus armies
	}// end of method getBunesArmies

	
	
}//end of class Country
	
