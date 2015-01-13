import javax.swing.JFrame;

public class RiskButtonFrameTest
{
	public static void main( String[] args )
	{
		RiskButtonFrame riskButtonFrame = new RiskButtonFrame(); // creates a RiskButtonFrame object
		riskButtonFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); // sets the default close operation
		riskButtonFrame.setSize( 1000, 400 );	// sets the size of the gui
		riskButtonFrame.setVisible( true );  // sets the visibility of the gui to true
	} // end of main method

} // end of Risk
