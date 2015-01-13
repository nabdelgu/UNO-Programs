/**
*Noah Abdelguerfi
*12/5/2013
*Fall 2013 CS:1583
*Homwork8
*DeckOfCards class represents a deck of playing cards.
**/
 
import java.util.Random;

public class DeckOfCards
{
	private Card[] deck; // array of Card objects
	private int currentCard; // index of next Card to be dealt
	private static final int NUMBER_OF_CARDS = 52; // constant number of cards

	//random number generator
	private static final Random randomNumbers = new Random();

	// constructor fills deck of Cards
	public DeckOfCards()
	{

		Rank[] RANKS = Rank.values();
		Suit[] SUITS = Suit.values();


		deck = new Card[ NUMBER_OF_CARDS ];// create array of Card objects
		currentCard = 0; // set currentCard so first Card dealt is deck[ 0 ]
		
		// populate deck with Card objects
		for ( int count = 0; count < deck.length; count++ )
			deck[ count ] = new Card( RANKS[ count % 13 ], SUITS[ count / 13 ] );
	}// end DeckOfCards constructor
	
	// shuffle deck of Cards with one-pass algorithm
	public void shuffle()
	{
		// after shuffling, dealing should start at deck[ 0 ] again 
		
			currentCard = 0; // reintialize currentCard

		// for each Card, pick another random between 0 and 51
		for ( int first = 0; first < deck.length; first++ )
		{

		int second = randomNumbers.nextInt( NUMBER_OF_CARDS );
		
		// swap current Card with randomly selected Card
		Card temp = deck[ first ];
		deck[ first ] = deck[ second ];
		deck[ second ] = temp;
		}// end  of for statement

	}// end method shuffle
	
	//deal one Card
	public Card dealCard()
	{
		// determine whether Cards remain to be dealt
		if( currentCard < deck.length )
			return deck[ currentCard++ ];
		else
			return null; // return null to indicate that all Cards were dealt
	} // end method dealCard

} // end class DeckOfCards
 

