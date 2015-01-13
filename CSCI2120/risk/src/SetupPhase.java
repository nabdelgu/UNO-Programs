import javax.swing.JFrame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

// allows the user to select the countries they wish to occupy
public class SetupPhase extends JFrame {
	
	
	private JLabel numOfTurns;
	private JLabel playerTurn;
	
	private JButton occupy;
	private Game game;
	private JComboBox selectCountry;
	
	private JLabel playerCount;
	private JLabel namePlayer;
	private SetupPhase setupPhase;
	private JLabel playerArmies;

	// repaints the player turn and player name
	private void refresh(){
		playerTurn.setText("Turn " + this.game.getTurnNumber() + ": Player " + this.game.getCurrentPlayer().getName());
		playerArmies.setText(("Armies: " + this.game.getCurrentPlayer().unplacedArmies));
		this.repaint();
		selectCountry.repaint();
	}// end of refresh method
	
	public SetupPhase(Game game){
		
		super("Select countries to occupy");
		this.game = game;
		setLayout( new FlowLayout() );
	

		// instantiation		
		playerCount = new JLabel("Select countries to occupy");
		occupy = new JButton("OK");	
		playerTurn = new JLabel();
		selectCountry = new JComboBox<String>(Game.listToStringArray(this.game.unoccupiedCountries));
		selectCountry.setMaximumRowCount(7);
		playerArmies = new JLabel(("Armies: " + this.game.getCurrentPlayer().unplacedArmies));
		
		add(selectCountry);
		add(playerCount);
		add(occupy);
		add(playerTurn);
		add(playerArmies);
		
		
		
		
		refresh();
		
		occupy.addActionListener(
				
				new ActionListener()
				{

					public void actionPerformed( ActionEvent event)
					{
							
							Game game = SetupPhase.this.game;
							String name = (String)SetupPhase.this.selectCountry.getSelectedItem();			// gets selected country
							Country country = game.getBoard().getCountryByName(name);						// gets the country object
							game.unoccupiedCountries.remove(country);										// removes from unoccupied countries
							int index = SetupPhase.this.selectCountry.getSelectedIndex();
							System.out.println(index);	
							selectCountry.removeItemAt(index);												//	removes selected country from the JComboBox
							game.getCurrentPlayer().addCountry(country);									// gives the country to the current player
							game.countryOccupants.put(country, game.getCurrentPlayer());					// sets the country occupants to 
							game.countryArmies.put(country, 1);												// adds a army to that country
							game.getCurrentPlayer().decrementArmies(1);
				 			JOptionPane.showMessageDialog( null, "You now occupy:"   + name );				// JOptionPane to display country being occupied
							if (SetupPhase.this.game.unoccupiedCountries.isEmpty()){
								//Done. Go to next phase.
								ReinforcePhase reinforcePhase = new ReinforcePhase(game);
								reinforcePhase.setVisible( true );										// unoccupied countries empty. Go to next phase
								reinforcePhase.setExtendedState(JFrame.MAXIMIZED_BOTH);
								SetupPhase.this.dispose();
								setupPhase.setVisible( false );
								setupPhase.dispose();
								
							} // end of if statement
							
							
							
							game.incrementTurn();			// increments turn				
							SetupPhase.this.refresh();		// refreshes SetupPhase
							
							
					}// end of method actonPerformed
							
						
							
							
							
						
				}//end of anonymous class		
				
		);// end of action listener
	
	
	
	}// end of constructor
	
	
}// end of class
	
	

	
	
		
			
		


	



	
		









