/**
*Noah Abdelguerfi
*12/5/2013
*Fall 2013 CS:1583
*Homwork8
*The BlackJack class simulates a round of BlackJack and returns which player won, or if the game is tied.
**/

public class BlackJack

{

	private DeckOfCards deck;
	private Player dealer;
	private Player player;
	private Strategy myPlayerStrategy;
	private DealerStrategy myDealerStrategy;

	// five-argument constructor
	public BlackJack(Strategy playerStrategy){
		this.deck = new DeckOfCards(); // creates a new deck of cards and assigns it to deck
		this.deck.shuffle(); // method shuffles the deck
		this.dealer = new Player(new DealerStrategy()); // assigns the dealer strategy to player 
		this.player = new Player(playerStrategy); // assigns a selected playerStrategy to player 
		this.myPlayerStrategy = playerStrategy;
	} //end of five argument constructor

	// a round of blackjack is played: a list of cards that are drawn are outputted. It also shows players taking turns until it determins a winner.
	public void playRound(){
		drawTwoCards();
		System.out.println("INITIAL DEALING");
		System.out.println("Player: " + player.handToString());
		System.out.println("Dealer: " + dealer.handToString());
		System.out.println("PLAYER TURN");
		playForPlayer(this.player);
		System.out.println("DEALER TURN");
		playForPlayer(this.dealer);
		Player winningPlayer = getWinningPlayer();
		if (winningPlayer == this.player){
		
		System.out.println(" The player wins" );

		} // end of if
		else if (winningPlayer == this.dealer){
		
		System.out.println(" The dealer wins");

		} // end of else if
		else{

		System.out.println("The game is tied");

		} // end of else
	}// end of playRound method

	//draw two cards for player and dealer
	public void drawTwoCards (){
		player.giveCard ( deck.dealCard ());
		dealer.giveCard ( deck.dealCard ());
		player.giveCard ( deck.dealCard ());
		dealer.giveCard ( deck.dealCard ());
	}

	//player plays: outputs whether the player and dealer will hit or stand, and when they bust
	public void playForPlayer (Player player){
		if (!playerHasBusted(player)){
			int hitOrStand = player.takeTurn();
			System.out.println (hitOrStand == Strategy.HIT ? "HIT" : "STAND");
			if (hitOrStand == Strategy.HIT)
			{
				player.giveCard ( deck.dealCard ());
				System.out.println((player == this.player ? "Player: " : "Dealer: ") + player.handToString());
				playForPlayer(player);
			} // end of if statement

		} // end of playForPlayer method

		else {
			System.out.println("BUSTED");
		} // end of else stament
	}	
	// sets when the player will bust: if the CurrentValue is greater than 21
	private boolean playerHasBusted (Player player) {
		return (player.getCurrentValue() > 21);
	}
	// determines which player won
	private Player getWinningPlayer()
		{
		boolean playerBusted = playerHasBusted(this.player);
		boolean dealerBusted = playerHasBusted(this.dealer);
		if (playerBusted || dealerBusted){
			//Someone busted.
			if (playerBusted && dealerBusted){
				return null;
			} // end of if statement

			else if (playerBusted){
				return this.dealer;
			} // end of else if statement

			else {
				return player;
			} // end of else statement

		} // end of if
		else{
			//Nobody busted:
			if (player.getCurrentValue() > dealer.getCurrentValue()){
				return this.player;
			} // end of if 
			else if (player.getCurrentValue() < dealer.getCurrentValue()){
				return this.dealer;
			} // end of else if 
			else {
				return null;
			} // end of else
		
		} // end of else statement

	} // end of getWinningPlayer method 
		
} // end of class BlackJack
