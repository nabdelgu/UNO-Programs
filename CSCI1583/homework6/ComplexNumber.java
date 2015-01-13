/**
*Noah Abdelguerfi
*CSCI 1583 Fall 2013
*Homework 6
*10/31/13
*defines an object for a complex number
**/

public class ComplexNumber 
{
	private float a; //instance variable that stores the value in a
	private float b; //instance variable that stores the value in b
	
	//constructor
	public ComplexNumber (float a , float b) 
	{
		this.a = a; // stores the value in a
		this.b = b; // stores the value in b	
	}
	
	//method for addition of complex numbers	
	public ComplexNumber add( ComplexNumber other)
	{
		float newA = a + other.a;
		float newB = b + other.b;	
		return new ComplexNumber(newA, newB);
	}
	
	//method for subtraction of complex numbers
	public ComplexNumber subtract( ComplexNumber other )
	{
		float newA = a - other.a;
		float newB = b - other.b;	
		return new ComplexNumber(newA, newB); 
	}

	public float getA() // method to return a
	{
		return this.a;
	}
	public float getB() // method to return b
	{
		return this.b;

	}	

	//method for multiplication of complex numbers
	public ComplexNumber multiply( ComplexNumber other )
	{
		float newA = this.a * other.a - b * other.b;
		float newB = this.b * other.a + a * other.b;	
		return new ComplexNumber(newA, newB);
	}

	//method for division of complex numbers
	public  ComplexNumber divide( ComplexNumber other )
	{
		float d = other.a * other.a + other.b * other.b;
		float newA = (a * other.a + b* other.b) / d;
		float newB = (b * other.a - a * other.b) / d;	
		return new ComplexNumber(newA, newB);

	}
	
	// adds real and imaginary parts
	public String toString()
	{
		return this.a + "+" + this.b + "i";
	
	}

}//end method ComplexNumber
