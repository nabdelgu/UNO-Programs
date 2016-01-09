/**
*Noah Abdelguerfi
*11/15/2013
*Fall 2013 CS:1583
*Homwork7
*This class creates a stack using dynamic arrays. Class provides 5 public methods; push, expandArray pop, peek, isEmpty, size. 
**/

public class Stack 
{

	private String[] stackArray;
	private int stackSize;

	// Constructor initializes stackArray to an array of 10 strings. The stackSize is initialized to 0.
	public Stack()
		{
		this.stackArray = new String[10];
		this.stackSize = 0;
		}

	//The method push(String stringValue) pushes stringValue onto the top of the stack.
	public void push(String stringValue)
	{
	if ( (stackArray.length - 1) == stackSize)
                {
                	expandArray();	       
                }

		this.stackSize++;
		this.stackArray[stackSize] = stringValue;	
	}//end of method push
	
	//The method expandArray doubles the size of the stackArray.
	private void expandArray()
	{

		String[] temp = new String[stackSize*2];

		for( int i = 0; i < stackArray.length; i++)
			{
			temp[i] = this.stackArray[i];
			}
		this.stackArray = temp;
	}//end of method expandArray

	//If the stack is not empty, method pop() returns the top of the stack. Otherwise, it returns  "stack empty".
	public String pop()
	{
		String popItem;

		if( stackSize == 0)
			{
			popItem = "stack empty";
			}
		else
			{
			popItem = this.stackArray[stackSize];
			this.stackSize--;
			}
		return popItem;	
	}// end of method pop

	//If the stack is not empty, method peek() returns a copy of the top of the stack. Otherwise, it returns  "stack empty".
	public String peek()
	{
		String peekItem;
		
		if( stackSize == 0)
			{
			peekItem = "stack empty";
			}
		else
			{
			peekItem = this.stackArray[stackSize];
			}
		return peekItem;
	}//end of method peek
	
	// The method isEmpty returns a boolean value true if the array is empty. Otherwise, it returns false.
	public boolean isEmpty()
	{
	
		boolean empty = true;	

		if( stackSize > 0) 
		{		
			empty = false;
		}
		return empty;

	}//end of method isEmpty

	// The method size() returns the size of the array as a integer.
	public int size()
	{
		return stackSize;
	}//end of method size
	
}//end of class Stack
