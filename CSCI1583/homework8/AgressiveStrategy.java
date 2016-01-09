/**
*Noah Abdelguerfi
*12/5/2013
*Fall 2013 CS:1583
*Homwork8
*This class sets the agressive strategy: The player will hit until he/she scores 21 or busts. Unless, his hand is worth 20 points in that case the player will stand.
**/

//Agressive strategy implements strategy
public class AgressiveStrategy implements Strategy{

// method to determine whether player will hit or stand 
public  int hitOrStand( int value ){

	if(value >= 20) // if the value is greater or equal to 20 the player will stand.
	{
		return Strategy.STAND;
	} // end of if statement
	
	else // if not the player will hit
		{
		 return Strategy.HIT;
		}  // end of else statment
	
	} // end of hitOrStand method

}// end of class AgressiveStrategy
