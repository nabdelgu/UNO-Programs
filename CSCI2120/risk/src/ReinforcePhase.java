import javax.swing.JFrame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

// allows players to placed remaining unplaced armies
public class ReinforcePhase extends JFrame {
	
	
	private JLabel numOfTurns;
	private JLabel playerTurn, playerArmies;
	private JButton reinforce;
	private Game game;
	private JComboBox selectPlayersCountries;
	private JLabel playerReinforce;
	private JLabel namePlayer;
	
	// resets JLabel playerTurn and players armes
	private void refresh(){
		playerArmies.setText("Armies: " + this.game.getCurrentPlayer().unplacedArmies);
		playerTurn.setText("Turn " + this.game.getTurnNumber() +"		" + " Player: " + this.game.getCurrentPlayer().getName());
		this.repaint();
		selectPlayersCountries.repaint();
	}// end of refresh
	
	// gets the next players countries
	private void changePlayerCountries(){
		
		selectPlayersCountries.removeAllItems();
		for(Country country : this.game.getCurrentPlayer().getOwnedCountries()){
			selectPlayersCountries .addItem(country.toString() );
		}
		this.repaint();
		selectPlayersCountries.repaint();
		
	}// end of changePlayerCountries
	
	public ReinforcePhase(Game game){
		
		super("Select countries to reinforce");
		this.game = game;
		setLayout( new FlowLayout() );
		
		//instantiation
		playerReinforce = new JLabel("Select countries to reinforce");
		playerArmies = new JLabel("Armies: " + this.game.getCurrentPlayer().unplacedArmies);
		reinforce = new JButton("OK");
	
		playerTurn = new JLabel();
		this.selectPlayersCountries = new JComboBox<String>(Game.listToStringArray(this.game.getCurrentPlayer().getOwnedCountries() ));
		selectPlayersCountries.setMaximumRowCount(7);
		
		// adds components
		add(selectPlayersCountries);
		add(playerReinforce);
		add(reinforce);
		add(playerTurn);
		add(playerArmies);
	
		
		
		
		refresh();		// refreshes text
		
		reinforce.addActionListener(
				
				new ActionListener()
				{

					public void actionPerformed( ActionEvent event) 
					{
						
						
							Game game = ReinforcePhase.this.game;				
							
							String name = (String)ReinforcePhase.this.selectPlayersCountries.getSelectedItem();		// gets country selected
							Country country = game.getBoard().getCountryByName(name);								// gets the country object
							int index = ReinforcePhase.this.selectPlayersCountries.getSelectedIndex();				// gets selected index
							game.countryArmies.put(country, game.countryArmies.get(country)+1);						// adds a army to that country
							game.getCurrentPlayer().decrementArmies(1);												// decrements currentPlayers armies
				 			JOptionPane.showMessageDialog( null, "You reinforced:" + name + " with 1 army" );		// JOpton pain displaying country being reinforced
													
							
							
							game.incrementTurn();						// increments turn		
							ReinforcePhase.this.refresh();				// updates JLabel
							changePlayerCountries();					// gets the next players countries
							
							// not more unplaced armies go to next phase
							if (game.getCurrentPlayer().getUnplacedArmies() == 0 ){
								//Done. Go to next phase.
								//game.getCurrentPlayer().incrementArmies(3);
						
								System.out.println("Launching Game Phase");
								MainPlayScreen mainPlayScreen = new MainPlayScreen(game);
								mainPlayScreen.setVisible( true );
								mainPlayScreen.setExtendedState(JFrame.MAXIMIZED_BOTH);
								ReinforcePhase.this.setVisible( false );
								ReinforcePhase.this.dispose();						
															
							}// end of if statement														
							
					} // end of method action performed
													
				} // end of anonymous class
								
		);	// end of action listener

	
	
	}// end of constructor
	
	
}// end of class
