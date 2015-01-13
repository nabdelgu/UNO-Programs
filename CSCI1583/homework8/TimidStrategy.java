/**
*Noah Abdelguerfi
*12/5/2013
*Fall 2013 CS:1583
*Homwork8
*This class sets the TimidStrategy: The player will hit until he busts or his hand has a value of 14 or greater.
**/

// Timid strategy implements strategy
public class TimidStrategy implements Strategy{


	public  int hitOrStand( int value ){

		if( value >= 14 ) // if the value is greater than or equal to 14 the player will stand
		{

			return Strategy.STAND;	
		} // end of if statement
		
		else // if not the player will hit
		{
			 return Strategy.HIT;
		} // end of else statement
	}

}// end of class TimidStrategy
