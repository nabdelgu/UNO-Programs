/**
*Noah Abdelguerfi
*12/5/2013
*Fall 2013 CS:1583
*Homwork8
*The Rank class defines all the Rank types and gives them 4 properties: name, order, symbol, and isFaceCard
**/
public enum Rank {
	
	
	// enums are declared to represent all the Ranks which contain the properties: name, order, symbol, and isFaceCard.
	ACE		("Ace","A",1,false),
	TWO		("Two","2",2,false),
	THREE		("Three","3",3,false),
	FOUR		("Four","4",4,false),
	FIVE		("Five","5",5,false),
	SIX		("Six","6",6,false),
	SEVEN		("Seven","7",7,false),
	EIGHT		("Eight","8",8,false),
	NINE		("Nine","9",9,false),
	TEN		("Ten","10",10,false),
	JACK		("Jack","J",11,true),
	QUEEN		("Queen","Q",12,true),
	KING		("King","K",13,true);

	/**A friendly name for the rank.*/
	public final String name;

	/*A short symbol for the rank.*/
	public final String symbol;

	public final int order;
	public final boolean isFaceCard;
	
	// 4 argument constructor
	private Rank(String name, String symbol, int order, boolean isFaceCard){
		this.name = name; // name variable is initialized
		this.order = order; // order variable is initialized
		this.symbol = symbol; // symbol variable is initialized
		this.isFaceCard = isFaceCard; // isFaceCard variable is initialized
	} // end of 4 argument constructor

} // end of class Rank
