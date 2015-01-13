/**
*Noah Abdelguerfi
*CSCI 1583 Fall 2013
*Homework 4
*10/4/13
**/

import java.util.Scanner;

public class Homework4
{

	//prints user instructions
	public static void printUserInstructions() {
		System.out.println("You will be prompted to enter your student's grades in this order:"); 
		System.out.println("  homework, quizzes and then tests.\n");
		System.out.println("When you complete a grade category enter -1.\n");
	}

	//gets users choice
	public static int getUserChoice(Scanner scanner)
	{
		System.out.println("1)Average grades for a new student");
		System.out.println("2)Quit");
		return scanner.nextInt();
	}

	//recieve user name 
	public static void main( String[] args )
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter student's name");
		String studentName = scanner.next(); 
		while (getUserChoice(scanner) == 1){
			printUserInstructions();

			//vaiables for averages
			double homeworkAverage = getAssignmentGradeAverage (scanner, "homework");
			double quizAverage = getAssignmentGradeAverage (scanner, "quiz");
			double testAverage = getAssignmentGradeAverage (scanner, "test");
			double finalAverage = getOverallAverage(homeworkAverage, quizAverage, testAverage);
			System.out.printf("The overall average for %s is %4.2f\n", studentName, finalAverage);
		}
		//End of program.
	}

	//Recieves users grades continuosly
	public static double getAssignmentGradeAverage (Scanner scanner, String assignmentType)
	{		
		//assignment variables
		int assignmentGrade = 0;
		int assignmentSum = 0;
		int assignmentCount = 0;

		System.out.println("Enter student's " + assignmentType + " Grade");
		assignmentGrade = scanner.nextInt();
		
		//continuosly promps user until -1 is entered
		while ( assignmentGrade != -1)
		{	
			assignmentSum += assignmentGrade;
			++assignmentCount;
			System.out.println("Enter student's " + assignmentType + " Grade");
			assignmentGrade = scanner.nextInt();
		}

		//returns average of assignment catagory
		return (double)assignmentSum/assignmentCount;
	}

	//Return the overall average
	public static double getOverallAverage ( double homeworkAverage, double quizAverage, double testAverage)
	{	
		//homework weighted 25%
		//quizes weighted 25%
		//tests weighted 50%
		return 0.25*homeworkAverage + 0.25*quizAverage + 0.5*testAverage;
	}
}


	
	
	
			


			
		
				





