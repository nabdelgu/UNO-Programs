import junit.framework.*;
import org.junit.*;
import java.util.ArrayList;
import java.util.Iterator;

public class BinaryTreeJUnitTesting extends TestCase {
	
	public BinaryTreeJUnitTesting(){} // do nothing constructor
	
	protected void setUp(){} // do nothing setUp method	
	
	public void Testinsert( ){
		
		BinaryTree root = new BinaryTree(10);
		root.insert(5);
		assertTrue(root.getLeftSubtree().getData() == 5);
		root.insert(11);
		assertTrue(root.getRightSubtree().getData() == 11);		// JUnit test for the insert method
		root.insert(4);
		assertTrue(root.getLeftSubtree().getData() == 4);
		root.insert(7);
		assertTrue(root.getRightSubtree().getData() == 7);
		root.insert(14);
		assertTrue(root.getRightSubtree().getData() == 14 );
		
	} // end of method insertTest 
	
	public void TestinOrder(){
		BinaryTree root = new BinaryTree(10);
		root.insert(5);
		System.out.println("Value should be: 5 10");
		root.inOrder();
		root.insert(11);
		System.out.print("Value should be: 5 10 11");	//JUnit test for the inOrder method
		root.inOrder();
		root.insert(4);
		System.out.print("Value should be: 4 5 10 11");
		root.inOrder();
		root.insert(7);
		System.out.print("Value should be: 4 5 7 10 11");
		root.inOrder();
		root.insert(14);
		System.out.print("Value should be: 4 5 7 10 11 14");
		root.inOrder();		
			
	} // end of method inOrderTest
	
	
	public void TestgetData(){
		BinaryTree root = new BinaryTree(10);
		assertTrue(root.getData() == 10);
		root.insert(5);
		assertTrue(root.getLeftSubtree().getData() == 5);
		root.insert(11);
		assertTrue(root.getRightSubtree().getData() == 11);			// JUnit test for the getDataTest tree
		root.insert(4);
		assertTrue(root.getLeftSubtree().getData() == 4);
		root.insert(7);
		assertTrue(root.getRightSubtree().getData() == 7);
		root.insert(14);
		assertTrue(root.getRightSubtree().getData() == 14);
		
	} // end of method getDataTest
	
	
} // end of class BinaryTreeJUnotTesting


	
