import java.io.IOException;

import javax.swing.JFrame;

// tester for SetupPhase
public class SetupPhaseTester {
	
	public static void main ( String[] args ) {
		Game game = null;
		try {
			game = new Game(2);
			SetupPhase setupPhase = new SetupPhase(game);
			setupPhase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setupPhase.setSize(350, 150);
			setupPhase.setVisible( true );
		} catch (IOException e){
			System.out.println("Failed: " + e.getMessage());
		}// end of catch block

	}// end of main
}// end of class SetupPhaseTester
