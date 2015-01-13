import java.io.IOException;

import javax.swing.JFrame;

public class ReinforcePhaseTester {
	// tester for ReinforcePhase
	public static void main(String[] args) throws Exception{
		Game game = null;
		
		try{
		game = new Game(2);	
		ReinforcePhase reinforcePhase = new ReinforcePhase(game);
		reinforcePhase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		reinforcePhase.setSize(300, 450 );
		reinforcePhase.setVisible( true );
		}// end of try block
		catch( IOException e){
			
		System.out.print( e.getMessage() );
		}// end of catch block
		
	}// end of main

}// end of class reinforce phase
