import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.lang.IllegalArgumentException;

 public class BinaryTreeSerializer{
	 
	 private BinaryTreeSerializer() {} // do nothing constructor
	 
	 public static void main (String [] args)
	 {
		 String test = args[0];		// name file ArrayList elements are passed	
		 try{
			 //setup(test); // here we create the new file if necessary
			 ArrayList<Integer> list = listFromFile(new File(test));	// add a list from a file into the ArrayList	
			 BinaryTree root = treeFromList(list);		// passes the list to treeFromListMethod and creates a BinaryTree from it
			 root.inOrder();		// puts the elements of the tree in ascending order
		 } catch (IOException e){
			 System.out.println(e.getMessage());		//catches IOException
		 } // end of catch block
		 catch( IllegalArgumentException e ){
			 System.out.println(e.getMessage());
		 }
	 } // end of main
	 
	 public static void setup(String filename) throws IOException {
		 ArrayList<Integer> list = new ArrayList<>();
		 
		 list.add(1);
		 list.add(12);										// adds elements to the array list
		 list.add(4);
		 list.add(19);
		 list.add(30);
		 serializeList(list,new File(filename));			// calls serializeList list. Which serializes it to a file.
		
	 } // end of setup method
	
	public static void serializeList(ArrayList<Integer> list, File file) throws IOException {
		FileOutputStream fileOut = new FileOutputStream( file );		// a FileOutputStream which is passed a file
		ObjectOutputStream output = new ObjectOutputStream( fileOut );	// a ObjectOutputStram which is passed a file output stream
		output.writeObject(list);		// writes the objects in the file to the list
		output.close();			// closes ObjectOutput stream
	} // end of serializeList method
	
	public static BinaryTree treeFromList(ArrayList<Integer> list)throws IllegalArgumentException{
		if (list.isEmpty()){
			throw new IllegalArgumentException("List cannot be empty.");	// handles the list empty exception
		} // end of if statment
		BinaryTree root = new BinaryTree(list.get(0));
		for( int i = 1; i < list.size(); i++ )		// for loop to insert list values into the root
			root.insert(list.get(i));
		return root;
	} // end of treeFromList method
	
	public static ArrayList<Integer> listFromFile(File file) throws IOException {
        
        try {
        	ObjectInputStream input = new ObjectInputStream(new FileInputStream(file)); // creates and object inputStream and passes it a file
        	Object object = input.readObject(); // read objects from a input stream
        	input.close();	// closes the input stream
        	ArrayList<Integer> list = (ArrayList<Integer>)object; // passes the what in the object file to the ArrayList
        	return list;
        } // end of try block
        catch (Exception e)
        {
        	throw new IOException (e.getMessage());		// handles any possible exceptions
        } // end of catch block
	}// end of listFromFile method
 } // end of class BinaryTreeSerializer




	

