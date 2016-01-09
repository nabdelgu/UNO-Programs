/**
*Noah Abdelguerfi
*12/5/2013
*Fall 2013 CS:1583
*Homwork8
* The main class prompts the user to either play or quit. If play is selected the user is prompted again to enter a player strategy. If the user input is not valid he will be prompted to enter another value
**/
import java.util.Scanner;

public class Main
{

	public static void main(String[] args){


		Scanner input = new Scanner( System.in );
		int playOrQuit = 1; // playOrQuit assigned to 1
		while (playOrQuit == 1){

			// if user enters 1 the game will be played and if 2 is entered the game will be quit

			System.out.println(" Press 1 to play or 2 to quit:" );
			playOrQuit = getNumber("Enter choice:",input,1,2);
			
			if (playOrQuit == 1){

			// if the player decides to play: the player will then be prompted to enter a player strategy: dealer, timid, and aggresive.

				System.out.println ("Enter the player strategy:");
				System.out.println ("\t1. Dealer\n\t2. Timid\n\t3. Aggressive");
				int playerStrategy = getNumber("Enter choice:",input,1,3);
				Strategy strategy = null;
				switch (playerStrategy){
					case 1:
						strategy = new DealerStrategy();
						break;
					case 2:
						strategy = new TimidStrategy();
						break;
					case 3:
						strategy = new AgressiveStrategy();
						break;
					default:
						//Will never happen.
						break;
				} // end of switch statement 
				BlackJack blackjack = new BlackJack(strategy); // creates a new BlackJack game
				blackjack.playRound();
			} // end of if statement
		} // end of while statement

	} // end of main

	// if the user input is lower or higher than the valid values, the user will be prompted to enter another value
	private static int getNumber(String prompt, Scanner input, int min, int max){
		System.out.print(prompt);
		int number = input.nextInt();
		while (number < min || number > max){
			System.out.print("Please enter a valid choice: ");
			number = input.nextInt();
		} // end of while
		return number;
	} // end of method getNumber

} // end of class Main
