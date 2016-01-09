/**
*Noah Abdelguerfi
*CSCI 1583 Fall 2013
*Homework 2
*9/12/13
**/

import java.util.Scanner;

public class Homework2 {

	public static void main( String[] args ){

		Scanner input = new Scanner( System.in );
		
		//declaration of variables
		double firstNumber;
		double secondNumber;
		int operation;
		double result;


		//Print staments
		System.out.println("Enter 1 for addition, 2 for subtraction,   3 for multiplication, 4 for division, and  5 for modulus");
		operation = input.nextInt();

		System.out.println("Enter the first number:");
		firstNumber = input.nextInt();

		System.out.println("Enter the second number:");
		secondNumber = input.nextInt();

		//Compute result based on user input.
		if (operation == 1){
			result = (firstNumber + secondNumber);		//addition
			System.out.printf("The sum is: %f\n", result);
		}
		else if (operation == 2){
			result = (firstNumber-secondNumber);		//subtraction
			System.out.printf("The difference is: %f\n",result);
		}		
		else if (operation == 3){
			result = (firstNumber * secondNumber);		//multiplication
			System.out.printf("The product is: %f\n",result);
		}
		else if (operation == 4){
			result = (firstNumber/secondNumber);		//division
			System.out.printf("The quotient is: %f\n" ,result);
		}
		else if (operation == 5){
			result = (firstNumber%secondNumber);		//modulus
			System.out.printf("The remainder is: %f\n" ,result);
		}
	}
}
