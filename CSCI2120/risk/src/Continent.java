
import java.util.*;
import java.io.Serializable;

public class Continent implements Serializable{
	
	//continent attributes
	private String name;
	private int bonusValue;
	private static ArrayList<Country> countries;	
	
	public Continent (String name, int bonusValue){
		this.name = name;
		this.bonusValue = bonusValue;					//3 argument constructor
		this.countries = new ArrayList<Country>();
	}//end of 3 argument constructor
	
	public void addCountry(Country country){
		this.countries.add(country);			// adds a country to an array list of countries 
	}//end of method addCountries

	public boolean continentsControlled(){
		
		return false;							//determine if continent is controlled
	}//end of method continentsControlled 
	
	
	public String getName(){
		
		return name;						// returns the continents name
		
	}// end of method getName
	
	public int getBonusArmies(){
		
		return bonusValue;								// returns the continents bonusArmies
	}// end of method getBonusArmies
	
	public static ArrayList<Country> getCountries(){
		
		return countries;								// returns an array of countries
	}// end of method getCountries
	
	
}//end of class Continent

