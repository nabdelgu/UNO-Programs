import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.io.Serializable;

public class Dice implements Serializable{
	
	private Dice(){}
		
	static Random randomNumbers = new Random(5);	//random number generator
	
	//maximum and minimum values of a dice
	public static final int MIN = 1;
	public static final int MAX = 6;

	
	public static Integer[] rollDie(int numberOfDice ){
		
		Integer results[] = new Integer[numberOfDice];
		for (int i = 0; i < numberOfDice; i++){					// method simulates the roll of the dice taking the number of rolls as a parameter
			results[i] = randomNumbers.nextInt(MAX-MIN) + MIN;	 
		}//end of for loop
		Arrays.sort(results,Collections.reverseOrder());
		return results;
		//Return the rolls of a number of dice.
	}//end of method rollDie

}//end of class Dice


