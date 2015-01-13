
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

// option to select the number of players 
public class PlayerSelectMenu extends JFrame
{
	// declaring a JComboBox and JLabel
	private JComboBox numberOfPlayers;
	private JLabel textLabel;
	
	
	private static final String[] playerNumbers = { "--Select--", "3", "4", "5", "6" };		// array for the number of players
	public PlayerSelectMenu()
	{

	super( "New Game" ); // call to super class
	setLayout( new FlowLayout() ); // sets the layout
	

	numberOfPlayers = new JComboBox( playerNumbers ); // adds the playerNumbers to the JComboBox numberOfPlayers 
        add(numberOfPlayers); // adds the numberOfPlayers JComboBox
	textLabel = new JLabel( "Select the number of users" );
	add( textLabel ); // adds text label

		numberOfPlayers.addItemListener(
		new ItemListener()
		{
			
			private PlayerSelectMenu playerSelectMenu;

			public void itemStateChanged( ItemEvent event)
			{
					if (event.getStateChange() == ItemEvent.SELECTED ){
						
						String s = numberOfPlayers.getSelectedItem().toString();
						int n = Integer.parseInt(s);
						try {
							if (n != 0){
								
								Game game = new Game(n);
								try {
									game = new Game(n);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}		// passes the game object the number of players
								
														
								CustomPlayerAttributes playerAttributes = new CustomPlayerAttributes(game);		// Item listener for the number of players selected
								playerAttributes.setExtendedState(JFrame.MAXIMIZED_BOTH); 					 // sets the gui to display full screen
								playerAttributes.setVisible(true);										// sets the visiblity to true
								playerSelectMenu.setVisible( false );
								playerSelectMenu.dispose();	
								
								
							
								 		// sets the visibilty of the PlayerSelectMenu to false	
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} // end of  nested if statement
					}// end of if statement
		
			} // end of method itemStateChanged

			
		} // end of anonymous
		
	); // end of actionListener to get the number of players
	

	} // end of constructor

} // end of class PlayerSelectMenu
