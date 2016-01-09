/**
*Noah Abdelguerfi
*CSCI 1583 Fall 2013
*Homework 5
*10/23/13
**/

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Homework5
{

	//Run the program.
	public static void main(String[] args)
	{
	
		//Import grid from file.
		int [][] grid = loadGridFromFilename(args[0]);
		final int ROWS = grid.length;
		final int COLS = grid[0].length;
		
		//Display the grid.
		displayGrid(grid);
		
		//Simulation runs until halted by user
		while (true)
		{
		
			//Temporary grid created
			int[][] temporaryGrid = new int[ROWS][COLS];
			
			//Each cell in the grid is prossesed
			for (int i = 0; i < ROWS; i++)
			{
				for (int j = 0; j < COLS; j++)
				{
					temporaryGrid[i][j] = getNextCellState(grid,i,j);
				}
			} //Stops when we've called getNextCellState on every grid item.
			
			//temporaryGrid becomes grid.
			grid = temporaryGrid;
			
			//Display grid.
			displayGrid(grid);
		}
	}
	
	//This procedure will display an integer grid
	public static void displayGrid(int [][] grid)
	{
		//loop through grid's rows
		for (int i = 0; i < grid.length; i++)
		{
			//loop through columns of current row
				for (int j = 0; j < grid[ i ].length; j++)
				{
					System.out.printf("%d   ", grid [i] [j]);
				}
				System.out.println(); // start a new line at the end of each row
		} //end of outer loop
	} // end of displayGrid method


	//This procedure scans a file, parse the contents and create and return an integer array.
	public static int[][] loadGridFromFilename(String filename)
	{
		Scanner input = null;
		try
		{
			input = new Scanner (new File(filename)); //Create a file Scanner and read in each line.
		} 
			catch (FileNotFoundException e)
		{
			System.out.println("File not found!");
			System.exit(1);
		}
		int rows;
		int columns;
		
		columns = input.nextInt(); //read number of columns
		rows = input.nextInt(); //read number of rows

		int [] [] grid = new int [rows] [columns];//inititalize a 2d array

		while (input.hasNext() )           //parse each line and set each 1 or 0 in the array.
		{
			for (int i = 0; i < grid.length; i++)
			{
					//loop through columns of current row
					for (int j = 0; j < grid[ i ].length; j++)
					{
					grid [ i ] [ j ] = input.nextInt();
					}
			} //end of outer loop
			
		} //end of while loop

		return grid; //returns an array
		} // end of loadGridFromFilename method


	//This procedure returns the count of the number of live adjacent cells.
	static int getLiveAdjacentCount(int[][] grid, int row, int column)
	{
		int count = 0;// will store the number of adjacent live cells
		int highestRowIndex = grid.length - 1; //highest row index in grid
		int highestColumnIndex = grid [0].length - 1; //highest column index in the grid
		int [] [] adjacentCells = {                      //adjacent cells without wrap around correction
					  {row-1, column-1},
					  {row-1, column  },
      					  {row-1, column+1},
					  {row  , column-1},
					  {row  , column+1},
					  {row+1, column-1},
					  {row+1, column  },
					  {row+1, column+1}
					 };
		for (int i = 0; i < adjacentCells.length; i++)  //adjacent cells with wrap around
			{
				if (adjacentCells [i] [0] == -1)
					adjacentCells [i] [0] = highestRowIndex;
				if (adjacentCells [i] [1] == -1)
					adjacentCells [i] [1] = highestColumnIndex;
				if (adjacentCells [i] [0] > highestRowIndex)
					adjacentCells [i] [0] = 0;
				if (adjacentCells [i] [1] > highestColumnIndex)
					adjacentCells [i] [1] = 0;
			}
					
		for (int i = 0; i < adjacentCells.length; i++) //counts the number of live adjacents cells using wrap around
			{
				int xCoordinate = adjacentCells[i] [0];
				int yCoordinate = adjacentCells[i] [1];	
				if (grid [xCoordinate] [yCoordinate] == 1) 
					++count;
			} //end of outer loop
		return count; // returns the number of live adjacent cells using wrap around when necessary
	}// end of getLiveAdjacentCount method


	//Perform one simulation of the grid. Returns whether the cell will survive.
	//Returns 1 if the cell survives and zero if the cell dies
	static int getNextCellState(int [][] grid, int row, int column)
	{
		
		int liveCount = getLiveAdjacentCount(grid,row,column); // count the number of live adjacent cells
		int nextState = 0; // cell's next state. It will be 1 when the cell will live and 0 if the cell is to die
	
		if (grid [row] [column] == 1)
		{
			switch ( liveCount)
			{
				case 0:
				case 1:
					nextState = 0; //cell dies because of under-population
					break;

				case 2:
				case 3:
					nextState = 1; //cell lives on to next generation
					break;

				default: // more than three live neighbours
					nextState = 0; //cell dies by overcrowding
			}
		}
		else if ( liveCount == 2 || liveCount == 3)
		{
			nextState = 1; // cell becomes alive by reproduction
		}
		
		//Determine whether this cell should survive...
		return nextState;
	} //end of getNextCellState method

} //end of Homework5 program
