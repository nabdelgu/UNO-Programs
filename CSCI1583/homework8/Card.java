/**
*Noah Abdelguerfi
*12/5/2013
*Fall 2013 CS:1583
*Homwork8
*The Card class: returns a string of Card representing a player's hand
**/

public class Card
{
	private Rank rank; // face of card ("Ace","Deuce", ...)
	private Suit suit; // suit of card ("Hearts", "Diamonds", ...)

	// two-argument constructor initializes card's face and suit
	public Card( Rank cardRank, Suit cardSuit )
	{
		rank = cardRank; // initialize face of card
		suit = cardSuit; // initialize suit of card
	} // end of two argument constructor
	
	//return string of Card
	public String toString()
	{
		return rank.name + "of" + suit.name;
	}//end method toString

	// returns a rank
	public Rank getRank(){
		return this.rank;
	} // end of method getRank
	
	// end of method getSuit
	public Suit getSuit(){
		return this.suit;
	} // end of method getSuit

} // end class Card
