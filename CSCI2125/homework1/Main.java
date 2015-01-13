public class Main {

	public static final Function CONSTANT = new Function(){
		public double f(double x){			//constant function
			return 10;
		}
	};

	public final static Function SINE = new Function(){
		public double f(double x){
			return Math.sin(x);			// sine function
		}
	};


	public final static Function POLYNOMIAL = new Function(){
		public double f(double x){
			return 3*x*x - 15*x + 2;		//polynomial function
		}
	};

	public static void main(String[] args){
		NumericalAnalysis n = new NumericalAnalysis();
		try {
			double x = n.solveForZero(SINE, Math.PI-1,Math.PI+1);
			System.out.println(x); //Should be 2*PI

		}// end of try block
		 catch (ArithmeticException e){
			System.out.println("We failed: " + e.getMessage());
		}// end of catch block

	}// end of main

}// end of class Main
