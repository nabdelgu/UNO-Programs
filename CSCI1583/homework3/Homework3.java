/**
*Noah Abdelguerfi
*CSCI 1583 Fall 2013
*Homework 3
*9/22/13
**/

import java.util.Scanner;

public class Homework3
{

	public static void main( String[] args )
	{

		Scanner input = new Scanner( System.in );
		
		//variable declarations
		int menuChoice = 0; 
		String studentName;
		//homework variables
		int homeworkGrade = 0;
		int homeworkSum = 0;
		int homeworkCount = 0;
		double homeworkAverage;
		//quiz variables
		int quizGrade = 0;
		int quizSum = 0;
		int quizCount = 0;
		double quizAverage;
		//test variables
		int testGrade = 0;
		int testSum = 0;
		int testCount = 0;
		double testAverage;
		//final average variable
		double finalAverage;
		
		System.out.println("1)Average grades for a new student");
		System.out.println("2)Quit");
		menuChoice = input.nextInt();

		while ( menuChoice == 1)
		{
			
			System.out.println("Enter student's name");
			studentName = input.next();
		
			System.out.println("You will be prompted to enter your student's grades in this order:"); 
			System.out.println("  homework, quizzes and then tests.\n");
			System.out.println("When you complete a grade category enter -1.\n");

			//homework average
			System.out.println("Enter student's homework grade");
			homeworkGrade = input.nextInt();
			while ( homeworkGrade != -1)
			{	
				homeworkSum += homeworkGrade;
				++homeworkCount; 
				System.out.println("Enter student's homework grade");
				homeworkGrade = input.nextInt();
			}//end of while

			homeworkAverage = (double)homeworkSum/homeworkCount;
		

			//quiz average
			System.out.println("Enter student's quiz grade");
			quizGrade = input.nextInt();
			while ( quizGrade != -1)
			{
				quizSum += quizGrade;
				++quizCount;
				System.out.println("Enter student's quiz grade");
				quizGrade = input.nextInt();	
			}//end of while

			quizAverage = (double)quizSum/quizCount;
		

			//test average
			System.out.println("Enter student's test grade");
			testGrade = input.nextInt();
			while ( testGrade != -1)
			{
				testSum += testGrade;
				++testCount; 
				System.out.println("Enter student's test grade");
				testGrade = input.nextInt();
			}//end of while

			testAverage = (double)testSum/testCount;
		
			//final average computation		
			finalAverage = 0.25*homeworkAverage + 0.25*quizAverage + 0.50*testAverage;
			System.out.printf(" The final average of student %S is: %4.2f\n", studentName, finalAverage);
			System.out.println("Enter 1 to continue or 2 to quit\n");
			menuChoice = input.nextInt();
		}//end of while	
	}
}	

