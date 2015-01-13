
import java.io.IOException;

import javax.swing.JFrame;



public class CustomPlayerAttributesTest 
{
	
		public static void main( String[] args ) throws IOException
		{

		CustomPlayerAttributes customPlayerAttributes = new CustomPlayerAttributes(new Game(3) ); // creates a object of customPlayerAttributes
		customPlayerAttributes.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); ////sets the default close operation
		customPlayerAttributes.setSize( 1000, 400 );  //sets the size of the the gui
		customPlayerAttributes.setVisible( true );  // sets the visibility of the gui to true
		}// end of main method
		
} // end of class CustomPlayerAttributes
