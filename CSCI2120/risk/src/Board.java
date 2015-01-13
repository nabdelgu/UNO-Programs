import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.Serializable;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Board implements Serializable

{
	// declares hash maps
	protected HashMap<String, Country>  countries;
	private ArrayList<String> countriesString;	
	private HashMap<String, Continent> continents;
	
	public static final String ADJACENCIES_PATH = ".\\Adjacencies.txt";
	public static final String COUNTRIES_PATH = ".\\Countries.txt";
	public static final String CONTINENTS_PATH = ".\\Continents.txt";
	
	
	public Board() throws IOException

	{
		//instanciates the hash maps
		countries = new HashMap<String, Country>();
		countriesString = new ArrayList<String>();
		continents = new HashMap<String, Continent>();
		this.readInCountries();
		this.readInContinents();
		
		this.readInAdjacencies();
		
		//initialize all the continents with a name and a bonusValue
		String old = System.getProperty("line.separator");
		System.setProperty("line.separator", "\n");
		System.setProperty("line.separator", old);
		
	}//end of constructor
	
	
	public ArrayList<Country> getCountries(){
		return new ArrayList<Country>(this.countries.values());
	}
	
	public Country getCountryByName(String name){
		return this.countries.get(name);
	}
	
	
	public ArrayList<Continent> getContinents(){
		return new ArrayList<Continent>(this.continents.values());
	}
	
	public ArrayList<String> getCountryStrings(){
		return countriesString;
	}
	
					
	public void readInCountries() throws IOException{

		try{
			System.out.println(new File(".").getAbsolutePath());
			FileInputStream inputStream = new FileInputStream(COUNTRIES_PATH);
			DataInputStream in = new DataInputStream(inputStream);				//reads in countries.txt
			BufferedReader br = new BufferedReader(new InputStreamReader(in));	
			Scanner input = new Scanner(inputStream);
			
			while (input.hasNextLine() ){
				String name = input.nextLine().trim();	
				Country country = new Country(name);			//formats text and adds the counties it to the hash map
				countries.put(name, country);
				countriesString.add(name);
			}// end of while loop
				in.close(); // closes DataInputStream
		}// end of try block
		
		catch(FileNotFoundException e){

			System.out.println(e.getMessage() );		//FileNotFOundException handling

		}// end of catch block
		
		catch(IOException e){
			
			System.out.println(e.getMessage() );		//IOException handling
		}// end of catch block
			
	}// end of readInCountries method

	public void readInContinents()throws IOException{

		

		try{

			FileInputStream inputStream = new FileInputStream(CONTINENTS_PATH);
			DataInputStream in = new DataInputStream(inputStream);					// reads in continents.txt
			BufferedReader br = new BufferedReader(new InputStreamReader(in));	
			Scanner input = new Scanner(inputStream);

			while (input.hasNextLine() ){
				System.out.println(input.nextLine());
				String line = input.nextLine().trim();	
				String[] tokens = line.split(",");
				String name = tokens[0].trim();
				int value = Integer.parseInt(tokens[1].trim());					//formats the text and adds the country name and the value to the hashMap
				Continent continent = new Continent(name,value);
				this.continents.put(name, continent);
				for (int i = 2; i < tokens.length; i++){
					String countryName = tokens[i].trim();
					Country country = this.countries.get(countryName);
					country.setContinent(continent);
					continent.addCountry(country);
				}
			}

			in.close(); // closes DataInputStream 

		}	

		catch(FileNotFoundException e){

			System.out.println(e.getMessage() );	//FileNotFoundExeption handling

		} // end of catch block
		
		catch(IOException e){
			
			System.out.println(e.getMessage() );	//IOExption handling
		}// end of catch block
			
	}// end of readInContinents method
		
		public void readInAdjacencies()throws IOException{

			try{
				
				
				
				FileInputStream inputStream = new FileInputStream(ADJACENCIES_PATH);
				DataInputStream in = new DataInputStream(inputStream);					// reads in adjacenciees.txt
				BufferedReader br = new BufferedReader(new InputStreamReader(in));	
				Scanner input = new Scanner(inputStream);

				while (input.hasNextLine() ){
					
					String line = input.nextLine().trim();
					String[] tokens = line.split(",");
					Country country = this.countries.get(tokens[0].trim());
					for (int i = 1; i < tokens.length; i++){					// formats text and makes countries adjacent
						Country other = this.countries.get(tokens[i].trim() );
						country.makeAdjacentTo(other);					
					}// end of while loop
				}// end of method readInAdjacencies

				in.close();	// closes DataInputStream

			}// end of try block

			catch(FileNotFoundException e){

				System.out.println(e.getMessage() );	// FileNotFoundException handling

			}// end of catch block
			
			catch(IOException e){
				
				System.out.println(e.getMessage() );	// IOException handling
			}// end of catch block
				
		}// end of readInAdjacencies method
		

}//end of class Board

		
		 
	
