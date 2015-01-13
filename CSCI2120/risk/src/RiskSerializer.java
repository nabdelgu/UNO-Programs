import java.io.*;

public class RiskSerializer {
	
	private RiskSerializer(){}
	
	public static Game loadGame(File file) throws IOException {
		ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
		Game game;
		try{									// method that loads a saved game
			game = (Game)stream.readObject();				// handles the ClassNotFoundException
		} catch (ClassNotFoundException e){
			stream.close();						// closes the InputStream
			throw new IOException ("Unable to parse the file as a game.");
		}
		stream.close();
		return game;
	} // end of method loadGame
	
	public static void saveGame(Game game, File file) throws IOException {
		ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
		stream.writeObject(game);							// method that saves the state of the game
		stream.close();				// closes the OutputStream
	} // end of method saveGame

} // end of class RiskSerializer
