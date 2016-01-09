/**
*Noah Abdelguerfi
*11/15/2013
*Fall 2013 CS:1583
*Homwork7
*This class creates a queue using dynamic arrays. Class provides 5 public methods; push, expandArray, pop, peek, isEmpty, size. 
**/

public class Queue 
{

	private String[] queueArray;
	private int queueSize;

	// Constructor initializes queueArray to an array of 10 strings. The queueSize is initialized to 0.
	public Queue()
		{
		this.queueArray = new String[10];
		this.queueSize = 0;
		}

	//The method push(String stringValue) pushes stringValue onto the top of the queue.
	public void push(String stringValue)
	{
	if (( queueArray.length-1) == queueSize)
                {
                	expandArray();	       
                }

		this.queueSize++;
		this.queueArray[queueSize] = stringValue;	
	}//end of method push
	
	//The method expandArray doubles the size of the queueArray.
	private void expandArray()
	{

		String[] temp = new String[queueSize*2];

		for( int i = 0; i < queueArray.length; i++)
			{
			temp[i] = this.queueArray[i];
			}
		this.queueArray = temp;
	}//end of method expandArray

	//If the queue is not empty, method pop() returns the top of the queue. Otherwise, it returns  "queue empty".
	public String pop()
	{
		String popItem;

		if( queueSize == 0)
			{
			popItem = "queue empty";
			}
		else
			{
			popItem = this.queueArray[1];
		for( int i = 0; i < queueSize; i++)
			{
			this.queueArray[i] = this.queueArray[i+1];
			}
			this.queueSize--;

			}
		return popItem;	
	}// end of method pop

	//If the stack is not empty, method peek() returns a copy of the top of the queue. Otherwise, it returns  "queue empty".
	public String peek()
	{
		String peekItem;
		
		if( queueSize == 0)
			{
			peekItem = "queue empty";
			}
		else
			{
			peekItem = this.queueArray[1];
			}
		return peekItem;
	}//end of method peek
	
	// The method isEmpty returns a boolean value true if the array is empty. Otherwise, it returns false.
	public boolean isEmpty()
	{
	
		boolean empty = true;	

		if( queueSize > 0) 
		{		
			empty = false;
		}
		return empty;

	}//end of method isEmpty

	// The method size() returns the size of the array as a integer.
	public int size()
	{
		return queueSize;
	}//end of method size
	
}//end of class Queue

