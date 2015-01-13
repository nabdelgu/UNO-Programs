/**
*Noah Abdelguerfi
*12/5/2013
*Fall 2013 CS:1583
*Homwork8
*The DeckTest clas: shuffles and deales cards.
**/

public class DeckTest
{

	// execute application
	public static void main( String[] args )
	{
		DeckOfCards myDeckOfCards = new DeckOfCards(); // create a deck of cards

		for ( int i = 0; i < 52; i++ )
		{

			System.out.printf( "%-18s", myDeckOfCards.dealCard());

			if ( i % 4 == 0) // output newline every 4 cards
				System.out.println();

		} //end for loop

	} // end  of main

} // end of class DeckTest

		

	
