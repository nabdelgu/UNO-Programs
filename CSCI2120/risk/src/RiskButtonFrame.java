
import java.awt.FlowLayout;
import java.io.*;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class RiskButtonFrame extends JFrame
{
	// creates 3 Jbuttons
	private JButton newGame;
	private JButton loadGame;
	private JButton quit;
	

	public RiskButtonFrame()
	{

		super( "Main Menu" );  // call to the super class
		setLayout( new FlowLayout() );	 // sets the layout of the gui
		newGame = new JButton( "New Game" );		
		add( newGame );			
		loadGame = new JButton( "Load Game" );		//instantiates and adds all 3 buttons	 	
		add( loadGame );
		quit = new JButton( "Quit" );
		add( quit );
		
		ButtonHandler handler = new ButtonHandler();
		newGame.addActionListener( handler );
		loadGame.addActionListener( handler );		// button handlers for each button
		quit.addActionListener( handler );
	}
		private class ButtonHandler implements ActionListener{

			public void actionPerformed( ActionEvent event ){

				if( event.getSource() == newGame ){
					PlayerSelectMenu menu = new PlayerSelectMenu();		// action listener for newGame button
																			// opens PlayerSelectMenu upon action
					menu.setVisible( true );								// sets the visibility of the gui to true
					menu.setExtendedState(JFrame.MAXIMIZED_BOTH);			// sets the gui to open to gull screen
				} // end of if statement

				if( event.getSource() == loadGame ){
					javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
					int result = chooser.showOpenDialog(RiskButtonFrame.this);
					if (result == JFileChooser.APPROVE_OPTION){
						File file = chooser.getSelectedFile();
						try {
							Game game = RiskSerializer.loadGame(file);		//Opens a JFile chooser to allow the user to select a saved game
							MainPlayScreen screen = new MainPlayScreen(game);	// try catch block to catch IOExceoption
							screen.setVisible(true);
						} catch (IOException e){
							JOptionPane.showMessageDialog(RiskButtonFrame.this,e.getMessage());
						} //  end of if statement
						
					}
				} // end of if statement

				if( event.getSource() == quit ){
					
					dispose();			// action listener for the quit button
									// exits the gui upon action
				}// end of the if statement
				
				

			} // end of ButtonHandler class
		
		
		} // end of constructor

} // end of RiskButtonFrame
		 
