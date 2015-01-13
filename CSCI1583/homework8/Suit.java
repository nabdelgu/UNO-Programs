/**
*Noah Abdelguerfi
*12/5/2013
*Fall 2013 CS:1583
*Homwork8
*The Suiit class sets what the suit values are and gives them a symbol
**/
public enum Suit {
	
	// enums are declared to represent all the suits and there properties: name and symbol
	HEARTS		("Hearts","H"),
	DIAMONDS	("Diamonds","D"),
	SPADES		("Spades","S"),
	CLUBS		("Clubs","C");

	public final String name;
	public final String symbol;
	
	// two argument constructor
	private Suit(String name, String symbol){
		this.name = name; // initialize the name variabe
		this.symbol = symbol; // initialize the symbol variable
	} // end of two argument constructor

} // end of class Suit
