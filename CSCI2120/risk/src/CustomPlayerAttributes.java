import javax.swing.JLabel;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class CustomPlayerAttributes extends JFrame {
	
	
	private static JLabel playerName[] = new JLabel[] 
	{ 
		new JLabel("player1"), new JLabel("Player2"), new JLabel("Player3"),new JLabel("Player4"), new JLabel("Player5"), new JLabel("Player6"),
		// array of player JLabels
	};


	private final JComboBox colorBox[];		// JCombo box of colors
	private static final String[] colorNames =  { "Black", "Blue", "Cyan", "Gray","Green","Magenta","Orange","Pink","Red","White","Yellow" };   //array of colors
	private static final Color[] colors = { Color.BLACK, Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.black.RED, Color.WHITE, Color.yellow };     //array of color names
	

	private JButton buttonOk; // button to execute to next stage
	
	 private JTextField fields[] = new JTextField[]  
	{  
			 new JTextField( "Enter Player1's name"),new JTextField( "Enter Player2's name"), new JTextField( "Enter Player3's name"),new JTextField( "Enter Player4's name"),new JTextField( "Enter Player5's name"),  new JTextField( "Enter Player6's name")    //array JTextFields for players names    
			
	 }; 
	 private JLabel label[] = new JLabel[]
	{
		new JLabel("Player1"), new JLabel("Player2"), new JLabel("Player3"), new JLabel("Player4"), new JLabel("Player5"), new JLabel("Player6")    // JLabels of players
	};
	 
	private Game game; // game object
	
	public CustomPlayerAttributes( Game game )
	{
		super( "Player Setup "); // call to super class
		this.game = game;		// creates an instance of game
		BoxLayout layout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		setLayout( layout );	// set layout
		
		this.colorBox = new JComboBox[ 6 ];
	
		setLayout( new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS) );
	
		int pc = game.getNumOfPlayers();

		for( int i = 0; i < 6; i++ ){
			colorBox[ i ] = new JComboBox( colorNames );
			add( label[i] );			// determines the number of labels, colorBoxes, and textField to add
			this.add(colorBox[ i ]);
			add( fields[i] );
			
			if( pc >= i + 1)	
				{
					fields[i].setEnabled( true );
					colorBox[i].setEnabled(true);
				} //end of if statement
			else									//determines the number of players 
													//dispays the number of fields and colorBoxes accordingly
				{
					fields[i].setEnabled( false );
					colorBox[ i ].setEnabled(false);
	            } // end of else statment

         } // end of for loop
			
			
		buttonOk = new JButton( "Ok");		//instantiates the buttonOK
		add( buttonOk );					//adds the buttonOk
		buttonOk.addActionListener(
			new ActionListener()
			{
				
				public void actionPerformed( ActionEvent event ){
					
					if( event.getSource() == buttonOk){
						Game game =  CustomPlayerAttributes.this.game;			// action listener for the buttonOk. Opens the MainPlayerScreen
						
						for ( int i = 0; i <= 6; i++){					// saves the player color and player name
							if (i < game.getNumOfPlayers() ){
								Player player = game.getPlayer(i);
								Color color = CustomPlayerAttributes.this.colors[CustomPlayerAttributes.this.colorBox[i].getSelectedIndex()];
								String name = CustomPlayerAttributes.this.fields[i].getText();
								
								player.setColor(color);
								player.setName(name);
								}// end of if statement
							else{
								
								SetupPhase setupPhase = new SetupPhase(game);
								setupPhase.setExtendedState(JFrame.MAXIMIZED_BOTH);
								setupPhase.setVisible( true );
								CustomPlayerAttributes customPlayerAttributes = null;
								customPlayerAttributes.setVisible( false );
							}
							
						} // end of for loop			
					} // end of if statement
					
					
					
					
				}// end of actionPerformed
			
			} // end of anonymous class
				
	);// end of ActionListener		
					
				
				
				
				} // end of 1 argument constructor	
	
	} // end of class CustomPlayerAttributes
				
				
				
				
				
			
		
		
		
	
		
		
		
		
		
		
		
		









