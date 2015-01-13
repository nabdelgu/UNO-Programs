import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Game implements Serializable
{
	
	static Scanner scanner = new Scanner(System.in);
	
	protected enum GamePhase { SETTING_UP, REINFORCING, MAIN_PHASE, GAME_OVER }	// enum of GamePhases

	//game reference variables
	private Board board;
	private Deck theDeck;
	private Game game;
	
	//game attributes
	protected String countryString = null;
	protected ArrayList<Player> players;
	protected HashMap<Country,Integer> countryArmies;
	protected HashMap<Country,Player> countryOccupants;
	protected ArrayList<Country> unoccupiedCountries;
	private Player currentPlayer;
	private int currentPlayerNumber;
	private int turnNumber = 0;
	protected GamePhase gamePhase;
	private int initialArmiesPerPlayer;

	
	public static String[] listToStringArray(ArrayList<? extends Object> objects){
		String list[] = new String[objects.size()];
		for (int i = 0; i < objects.size(); i++){
			list[i] = objects.get(i).toString();
		}
		return list;
	}
	
	
	public ArrayList<Country>getUnoccupiedCountries(){
		return unoccupiedCountries;
	}
	
	public Board getBoard(){
		return this.board;
	}
	
	
	
	Game() throws IOException {
		this.board = new Board();
		this.gamePhase = GamePhase.SETTING_UP;
		this.countryArmies = new HashMap<>();
		this.countryOccupants = new HashMap<>();
		for (Country country : this.board.getCountries()){			//no argument instructor
			this.countryArmies.put(country, 0);					// adds country armies and country occupants
			this.countryOccupants.put(country, null);
		}// end of for loop
		this.unoccupiedCountries = new ArrayList<Country>();
		System.out.println("Countries...");
		for (Country c : this.board.getCountries()){
			System.out.println(c.getName());
			this.unoccupiedCountries.add(c);
		}
		
	}// end of no argument constructor
	
	public Game(ArrayList<Player> players) throws IOException {
		this();
		this.players = players;						// 1 argument constructor
		initialArmiesPerPlayer = -5*this.players.size() + 40;
		this.currentPlayer = this.players.get(0);
		this.currentPlayerNumber = 0;
		for (Player p : players){
			p.incrementArmies(initialArmiesPerPlayer);
		}
	}// end of 1 argument constructor

	
	public Game( int numOfPlayers) throws IOException {
		
		this();		
		this.players = new ArrayList<Player>();
		initialArmiesPerPlayer = -5*this.players.size() + 40;
		System.out.println(initialArmiesPerPlayer);
		for (int i = 0; i < numOfPlayers; i++){
			Player p = new HumanPlayer("Player " + (i+1) );		// 1 argument constructor
			p.incrementArmies(initialArmiesPerPlayer);
			this.players.add(p);
		}//end of for statement
		
		this.currentPlayer = this.players.get(0);
		this.currentPlayerNumber = 0;
		
	}//end of 1 argument constructor
	public ArrayList<Player> addPlayer(Player player){
		
		players.add(player);
		return players;
		
	}
	
	public Player getPlayer(int index){
		return this.players.get(index);			// returns players index
	}// end getPlayer methodPlayer
	
	public Player getCurrentPlayer(){
		return this.currentPlayer;		//returns current player
	}// end of getCurrentPlayer method
	
	public void startTurn(Player player) {
		
										//give player armies, etc.
	}//end of method startTurn

	 // simulates a battle
	 public String resolveBattle(Country attacker, int numAttackDice, Country defender, int numDefendDice){
		 Integer resultsDefend[] = Dice.rollDie(numDefendDice);
		 Integer resultsAttack[] = Dice.rollDie(numAttackDice);
		 
		 for (int i = 0; i < Math.min(numAttackDice,numDefendDice); i++){
			 System.out.println("Attacker: " + resultsAttack[i]);
			 System.out.println("Defneder: " + resultsDefend[i]);
		 }
		 Player attackingPlayer = this.countryOccupants.get(attacker);
		 Player defendingPlayer = this.countryOccupants.get(defender);
		 int battles = Math.min(numAttackDice,numDefendDice);
		 for (int i = 0; i < battles; i++){
			 int attackScore = resultsAttack[i];
			 int defendScore = resultsAttack[i];
			 Player winningPlayer = (attackScore > defendScore) ? attackingPlayer : defendingPlayer;
			 Player losingPlayer = (winningPlayer == attackingPlayer) ? defendingPlayer : attackingPlayer;
			 //remove losing side's piece	

				final String s = ("Player: " + this.countryOccupants.get(attacker).getName() + "lost 1 army");
				//attacker wins
				final String a = ("Player: " + this.countryOccupants.get(defender).getName() + "lost 1 army" );
				// not enough armies to attack
				final String r = ("You do not have enough armies to attack");
				// player wins
				final String w = ("Player: " + this.countryOccupants.get(attacker).getName() + "won" + "\n" + "You now control" + defender.toString() );
			 			MainPlayScreen mainPlayScreen = null; 	 
				if(this.countryArmies.get(attacker) == 1){
						return r;						
				}
			 
				else if( winningPlayer == attackingPlayer ){
					countryArmies.put(defender, this.countryArmies.get(defender) - 1 );
					
					return a;
				}// end of if statement
				else if( winningPlayer == defendingPlayer  ){
					countryArmies.put(attacker, this.countryArmies.get(attacker) - 1 );
					
					return s;
				}// end of else if statement
				else if( attackScore == defendScore ){
					countryArmies.put(attacker, this.countryArmies.get(attacker) - 1 );
					
					return s;
					
				}// end of else statement
			 // end of if statement 
			 
				
		
		
			
			if(countryArmies.get(defender) == 0){
				 defendingPlayer.removeCountry(defender);
				 attackingPlayer.addCountry(defender);
				 this.countryOccupants.put(defender, attackingPlayer);
				 this.countryArmies.put(defender, 1);
				 this.countryArmies.put(attacker, this.countryArmies.get(attacker) - 1);
				 System.out.print("Player" + countryOccupants.get(attacker).getName() + "now occupies " + defender.toString() );
				 
				 mainPlayScreen.attackFromComboBox.addItem(defender);
				 mainPlayScreen.attackToComboBox.removeItem(defender);
				 
				 Card card = new Card();
				 attackingPlayer.getDeck().addToPlayersCards(card);			
				mainPlayScreen.refreshAttack();
				 
				 
				// return w;
			 }
			
			
			
			 //remove losing side's piece
			 //defender wins

		/*
			if(this.countryArmies.get(attacker) == 1){
				return r;
				
			}
					
			else if( players winningPlayer == players attackingPlayer ){
				countryArmies.put(defender, this.countryArmies.get(defender) - 1 );
				return a;
				
			}// end of if statement
			else if( winningPlayer = defendingPlayer  ){
				countryArmies.put(attacker, this.countryArmies.get(attacker) - 1 );
				
				return s;

			}// end of else if statement
			else if( attackScore == defendScore ){
				countryArmies.put(attacker, this.countryArmies.get(defender) - 1 );
		
				return s;


			}// end of else statement
			
		 // end of if statement
			else if(countryArmies.get(defender) == 0){
				 defendingPlayer.removeCountry(defender);
				 attackingPlayer.addCountry(defender);
				 this.countryOccupants.put(defender, attackingPlayer);
				 this.countryArmies.put(defender, 1);
				 this.countryArmies.put(attacker, this.countryArmies.get(attacker) - 1);
				 Card card = new Card();
				 attackingPlayer.getDeck().addToPlayersCards(card);			
				
				 return w;
			 }
			 
			*/
			 
			 //Find the country that contains zero.
	 	}// end of for loop
		 ///clean up ownership.
		return null;
		
		 		//if either countries no longer have any armies then give it to the player who does		 
	}// end of method resolve battle
		 //simulate battle
		 //remove armies

	 
 public Country getCountryFromText(String prompt){
		
		boolean found = false;
		Country country = null;
		while(!found){
			System.out.print(prompt);
			String s = Game.scanner.nextLine().trim();
			if (s.isEmpty()){
				System.out.print("Invalid country name.");		// gets a country from text
			}// end of if statement
			else{
				country = this.board.getCountryByName(s);
				if (country == null){
					System.out.println("Country not found.");
				}// end of if statement
				else{
					found = true;
				}// end of else
			}// end of if statement
		}// end of while loop
		return country;
		 	
 }// end of method getCountryFromText
 
 private Country getUnoccupiedCountry(){
	 for (Country c : this.unoccupiedCountries){
		System.out.println(c.getName());
	}
	 boolean found = false;
	 while (!found){
		 Country country = getCountryFromText("Choose country to occupy: ");
		 if (this.unoccupiedCountries.contains(country)){					//gets a unocuppiedCountry from text
			 return country;
		 }//  of if statement
		 else {
			 System.out.println("Country is already taken. Please enter another.");
		 }// end of else
	 }// end of while loop
	 return null;
 }// end of method getUnoccupiedCountry
 
 public int getTurnNumber(){
	 return this.turnNumber;
 }
 
 
 public static int getInteger(int min, int max, String prompt){
	 boolean found = false;
	 while (!found){
		 System.out.print(prompt);
		 String s = scanner.nextLine();
		 try {
			 Integer n = Integer.parseInt(s.trim());
			 if (n < min || n > max){										//prompts user to enter a integer
				 System.out.println("Please enter a valid choice.");
			 }// end of if statement
			 else{
				 return n;
			 }// end of else statement
		 }//end of try block
		 catch (Exception e){
			 System.out.println("Please enter a valid number.");
		 }// end of catch block
	 }// end of while loop
	 return -1;
 }// end of method getInteger
 
 private Country getCountryOwnedByPlayer(Player player, String prompt){
	 boolean found = false;
	 while (!found){
		 Country country = getCountryFromText(prompt);
		 if (this.currentPlayer.getOwnedCountries().contains(this.countryOccupants.get(country))){					// gets the country owned by player
			 return country;
		 }// end of if statement
		 else{
			 System.out.println("You must select a country you own. Please try again.");
		 }// end of else statement
	 }// end of while loop
	 return null;
 }// end of method getCountryOwnedByPlayer
 
 	public void incrementTurn(){
 		this.turnNumber++;
 		this.currentPlayerNumber = (this.currentPlayerNumber + 1) % this.players.size();
		this.currentPlayer = this.players.get(this.currentPlayerNumber);
 	}
 
	// simulates players playing each stage of the game
	public void takeTextTurn() {
		System.out.println("=============================");
		System.out.println("Turn " + this.turnNumber + ": Player " + this.currentPlayerNumber);
		System.out.println("=============================");
		this.turnNumber++;
		switch(this.gamePhase){
		// each player claimed unoccupied countries until there are none left
			case SETTING_UP:
				Country countryToOccupy = this.getUnoccupiedCountry();
				this.unoccupiedCountries.remove(countryToOccupy);
				this.countryArmies.put(countryToOccupy,1);
				this.currentPlayer.decrementArmies(1);		
				this.currentPlayer.addCountry(countryToOccupy);
				if (this.unoccupiedCountries.isEmpty()){
					//All countries have been chosen. Go to the next phase.
					this.gamePhase = GamePhase.REINFORCING;
				}// end of if statement
				break;
				
				// each player reinforces owned countries until there are no unplacedArmies
			case REINFORCING:	
				Country reinforcedCountry = this.getCountryOwnedByPlayer(this.currentPlayer, "Choose a country to reinforce: ");
				this.countryArmies.put(reinforcedCountry, this.countryArmies.get(reinforcedCountry)+1);
				this.currentPlayer.decrementArmies(1);														
				if (this.game.getPlayer(currentPlayerNumber+1 % this.players.size()).getUnplacedArmies() == 0){
					//All players have placed their armies. Move on to main phase.
					this.gamePhase = GamePhase.MAIN_PHASE;
				}// end of if statement
				break;
				// players have the option to Trade in cards, attack, fortify, or pass 
			case MAIN_PHASE:
				
				int newArmies = Math.max(3,(int)this.currentPlayer.getOwnedCountries().size()/3);
				this.currentPlayer.incrementArmies(newArmies);
				System.out.print("Game options:\n1. Trade in cards\n2. Attack\n3. Fortify\n4. Pass");
				int choice = this.getInteger(1,4,"Turn option: ");
				if(choice == 1 ){
					//trade in cards
					ArrayList<Card>playersCards = currentPlayer.getCards();
					int armies = theDeck.tradeInCards(playersCards);
					int numOfArmies = currentPlayer.incrementArmies(armies);
				}// end of if statement
				else if(choice == 2){
					//attack
					
					Country countryToAttackFrom = getCountryFromText("Select country to attack from");
					
					if(currentPlayer.playerCountries.contains(countryToAttackFrom)){
						//continue
						//countryToAttack
					Country countryToAttack = getCountryFromText("Select country to attack");
						//numberOfArmiesToAttackWith
					int numberOfArmiesToAttackWith = getInteger(1,Math.min(3,this.countryArmies.get(countryToAttackFrom)-1),"Select the number of armies to attack with");
					//attacker number of dice to roll
					Integer numberOfDiceToRollAttacker = getInteger(1,3,"Select the number if dice you want to roll......atttacker");
					Integer resultsAttacker[] = Dice.rollDie(numberOfDiceToRollAttacker);
					//defender number of dice to roll					
					Integer numberOfDiceToRollDefender = getInteger(1,3,"Select the number if dice you want to roll......defender");
					Integer resultsDefender[] = Dice.rollDie(numberOfDiceToRollDefender);
										
						System.out.print(".......battle is commmencing");
						resolveBattle(countryToAttackFrom, numberOfDiceToRollAttacker, countryToAttack, numberOfDiceToRollDefender);										
					}// end of else if statement
					
					
					else if( choice == 3 ){
						// fortify
						//country to fortify
						Country countryToMoveArmiesFrom = getCountryFromText("Select country to fortify from");
						Country countryToForify = getCountryFromText("Select country to fortify");
						//number of armies to fortify with
						Integer numberOfArmiesToFortifyWith = getInteger(1,200,"Select the number of armies to fortify with");
						//decrement countryArmies from were armies are moved form
						this.countryArmies.put(countryToMoveArmiesFrom, this.countryArmies.get(countryToMoveArmiesFrom)-1);
						//increment country armies of country to fortify
						this.countryArmies.put(countryToForify, this.countryArmies.get(countryToForify)+1);
						
					}// end of else if statement
					else if(choice == 4){
						System.out.print("......turn passed");
						this.gamePhase = GamePhase.MAIN_PHASE;
					}// end of else if statement
					
					if(this.currentPlayer.playerCountries.contains(board.countries)){
					this.gamePhase = GamePhase.GAME_OVER;
					}// end of if statement
					break;
				}// end 
				// game is over
			case GAME_OVER:
				System.out.print("The winning player is" + this.currentPlayer );
				break;
			default:
				break;
		}
		this.currentPlayerNumber = (this.currentPlayerNumber + 1) % this.players.size();
		this.currentPlayer = this.players.get(this.currentPlayerNumber);
	}//end of method takeTurn
	
	
	public Player determineWinner(Player player, Player player2){
				
		return player;					// method determines the winner
	}//end of method determineWinner
	public static void addaArmy(int numberOfArmiesToAdd ){
	
	
										//method gives armies to players
	}//end of method addArmy
	public int getNumOfPlayers(){

		

		return players.size();			// determines number of players playing

	}//end of method getNumOfPlayers

}//end of class Game
