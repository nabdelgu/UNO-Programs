/**
  * Noah Abdelguerfi
  * Homework 3
  * Class implementing a binary tree using recursion
  * with in order traversal
  **/


/** Constructor:
 * Parameters: int value contains the data being stored  
 * BinaryTree instance variables do not contain sub-trees
 * initially, so they are set to null
 */
public class BinaryTree
{
	// Instances variables
	private int data;
	private BinaryTree leftSubtree;
	private BinaryTree  rightSubtree;

	/** Constructor:
 	**/

	public BinaryTree (int value)
	
	{
		this.data = value;
		this.leftSubtree = null;
		this.rightSubtree = null;
	} // end of 1 argument constructor

	/**
 	* Returns the value stored in this node
 	**/

	public int getData()
	{
		return this.data;
	} // end of getData() metod
	
	BinaryTree getLeftSubtree()
	{
		return this.leftSubtree;
	} // end of getLeftSubtree method
	
	BinaryTree getRightSubtree()
	{
		return this.rightSubtree;
	} // end of getTightSubtree method

	/**
 	*This method inserts a value to the tree
 	*Parameters: int value contains the value to be added
 	**/
	public void insert (int value)
	{
		int data = this.getData();
		if (value <= data)
		{
			if (this.leftSubtree == null)				// if the value is <= to data and the.leftSubtree is null insert the value to the left 
										
				this.leftSubtree = new BinaryTree(value);
			else
				this.leftSubtree.insert(value);
		} // end of if statment
		else if (value > data)
		{
			if (this.rightSubtree == null)
				this.rightSubtree = new BinaryTree(value);	// if the value is > the data and the the right subtree is null insert the value to the right.
			else
				this.rightSubtree.insert(value);
		} // end of else if
		
		else
		{
			throw new IllegalArgumentException("Error illegal argument");		// handles illegal argument exception
		} // end of else

	} // End of insert(int value) method
	
	/**
	*The method inOrder() traverses the tree following the in order
 	* traversal algorithm. When each node is "visited" by the
 	*algortihm, print the value at that node to the screen
	**/
	public void inOrder()
	{
		if (this.leftSubtree != null)
		{
			this.leftSubtree.inOrder();
		} // end of if statment
		System.out.println(this.getData());
		if (this.rightSubtree != null)
		{
			this.rightSubtree.inOrder();
		} // end of if statment
	} // End of inOrder() method

} //End of public class BinaryTree
