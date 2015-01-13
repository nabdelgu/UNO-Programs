
import javax.swing.JFrame;

public class PlayerSelectMenuTest
{

	public static void main( String[] args )
	{

		PlayerSelectMenu playerSelectMenu = new PlayerSelectMenu(); // create a PlayerSelectMenu object
		playerSelectMenu.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); // sets the default close operation
		playerSelectMenu.setSize( 1000,400 );	// sets the size of the gui
		playerSelectMenu.setVisible( true ); // sets the visibility of the gui to true
	} // end of main method

} // end of class PlayerSelectMenuTest
