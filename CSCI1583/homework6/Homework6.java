/**
*Noah Abdelguerfi
*11/31/2013
*Fall 2013 CS:1583
*Homwork6
*User selction of operation
**/

import java.util.Scanner;

public class Homework6
{
	public static void main( String[] args )
	{
		//scanner to obtain input from the command window
		Scanner input = new Scanner( System.in );
		
		//declaration of complex numbers
		ComplexNumber firstComplex;                  
		ComplexNumber secondComplex;
		ComplexNumber resultComplex;
		
		//declaration of floats and int
		float a;
		float b;
		int operation;
		
		// user selects operation to perform
		System.out.println( "Press 1 for addition, 2 for subtraction, 3 for multiplication, 4 for division, and press -1 to quit");
		operation = input.nextInt();
		while(operation != -1){
		
		//prompt user for the first real and imaginary value for the first complex number
		System.out.println( "Enter the real value of the first complex number: ");
		a = input.nextFloat();
		System.out.println( "Enter the imaginary value of the first complex number: ");
		b = input.nextFloat();
		
		//creates the first array of complex number objects
		firstComplex = new ComplexNumber ( a, b );
		
		//prompt user for real and imaginary value  for the second complex number
		System.out.println( "Enter the  real value of the second complex number: ");
		a = input.nextFloat();
		System.out.println( "Enter the  imaginary value of the second complex number: ");
		b = input.nextFloat();
		
		//creates the second array of complex number objects
		secondComplex = new ComplexNumber( a, b );
		
		//output the first and second complex number
		System.out.println( "The first complex numbers is: " + firstComplex );
		System.out.println( "The second complex numbers is: " + secondComplex );
		
		// output of the addition of two complex numbers	
		if( operation == 1){
		resultComplex = firstComplex.add( secondComplex );			
		System.out.println("The sum of the two complex numbers is " + resultComplex );
		}

		//output of the subtraction of two complex numbers
		else if(operation == 2){
		resultComplex = firstComplex.subtract( secondComplex );		
		System.out.println("The difference of the two complex numbers is " + resultComplex);
		}

		//output of the multiplication of two complex numbers
		else if(operation == 3){
		resultComplex = firstComplex.multiply( secondComplex );		
		System.out.println("The product of the two complex numbers is " + resultComplex);
		}

		//output of the division of two complex numbers
		else if(operation == 4)	{	
		resultComplex = firstComplex.divide( secondComplex );		
		System.out.println("The quotient of the two complex numbers is " + resultComplex);
		}
		//re-promt user to continue calculations or exit.
		System.out.println( "Press 1 for addition, 2 for subtraction, 3 for multiplication, 4 for division, and press -1 to quit");
		operation = input.nextInt();
		
		}//end of while

	}//end method main

}// end method Homeowork6
