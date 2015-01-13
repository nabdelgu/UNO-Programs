/**
*Noah Abdelguerfi
*12/5/2013
*Fall 2013 CS:1583
*Homwork8
*This class sets the agressive strategy: The player will hit until he/she scores 21 or busts. If the palyer's hand is worth 20 points, the player will stand.
**/

public class Player

{

     	private Card[] hand;
	private int handSize;
     	private Strategy myStrategy;

 	// two argument constructor
	public Player( Strategy strategy){
		
		this.myStrategy = strategy; // the strategy chosen by the user is assigned to myStrategy
		this.handSize = 0; // the hand size is initialized to 0
		this.hand = new Card[15]; // an array of 15 cards is created
	
	} // end of two argument constructor
     	// method gives a card to the player
	public void giveCard(Card newCard){

		this.hand[this.handSize] = newCard;
		this.handSize ++;
	
	} // end of method giveCard

	// handToString to represent the Card value. If the value is 21 with a hand size of 2 it will return BLACKJACK
	public String handToString(){
		String s = "";
		for (int i = 0; i < handSize; i++){
			Card card = this.hand[i];
			s += card.getRank().symbol + card.getSuit().symbol;
			if (i < handSize -1 ){
				s += ", ";
			} // end of if statment
		
		} // end if for statment
		int value = this.getCurrentValue();
		String status = ((value == 21 && handSize == 2) ? "BLACKJACK" : value + "");
		return s + " > " + status;
	} // end of methd handToString
	
	// method gets the current card value of the hand
	public int getCurrentValue(){
		int sum = 0;
		boolean aceValue = false;
		for( int i = 0; i < handSize; i++ )
		{
			Card card = this.hand[i];
			int cardValue = card.getRank().order;
			if (card.getRank().isFaceCard){
				cardValue = 10;
			} // end of if statment
			if( card.getRank() == Rank.ACE)
			{	
				aceValue = true;	
			} // end of if statment
			sum += cardValue;
		} // in of for loop
		if (sum <= 11 && aceValue){
			sum += 10;
		} // end of if statment
		return sum;
	} // end of method getCurrentValue

 

     public int takeTurn(){
	  int handTotal = this.getCurrentValue();
          return myStrategy.hitOrStand(handTotal);
     } // end of method takeTurn

} // end of class player
