import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import java.util.Random;

public class Main implements Serializable {
	Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args){
		
		try {
	 		Game game = new Game(3);
	 		
	 		while (game.gamePhase != game.gamePhase.GAME_OVER){
	 			game.takeTextTurn();								// if the game is not over keep taking turns
	 		}// end of while loop
	 		
			// end of try block
					// handles except		
			// end of catch block
		} catch (IOException e){
			System.out.println(e.getMessage());
		}
 	}// end of Main
 	
	
} // end of class Main