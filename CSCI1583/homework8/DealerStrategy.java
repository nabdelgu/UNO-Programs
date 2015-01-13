/**
*Noah Abdelguerfi
*12/5/2013
*Fall 2013 CS:1583
*Homwork8
*This class sets the DealerStrategy: The player will hit until he busts or his hand has a value of 17 or greater.
**/

// Dealer strategy is a implementation of strategy
public class DealerStrategy implements Strategy
{

	public  int hitOrStand( int value ){

		if( value >= 17 ) // if the value is greater than or equal to 17 the player will stand
		{
			 return Strategy.STAND;
		}// end of if statement
	
		else // if not the player will hit 
		{
			 return Strategy.HIT;

		}// end of else statement

	} // end of hitOrStand method

} // end of class DealerStrategy


