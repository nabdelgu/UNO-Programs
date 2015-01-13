import java.util.*;

public class NumericalAnalysis{


	
	private static boolean isAlmostEqual(final double a, final double b){
		return Math.abs(b-a) <= 0.000001;				// determines if value is close to 0
	}
	

	public double solveForZero (final Function f, double low, double high) throws ArithmeticException {

		
 
			if ( f.f(low) * f.f(high) > 0){
				
				System.out.print("Could not find the solution");
				System.exit(0);
			} // end of if
			else{

			for( int i = 0; i < 1000; i++ ){
			
				double middle = (low + high) / 2;			//determines value that makes function zero 

				if (isAlmostEqual(f.f(middle), 0))
					return middle;
				
				else if( f.f(low) * f.f(middle) < 0 )
				
					high = middle;
											
				else if( f.f(high) * f.f(middle) < 0 )
				
					low = middle;
			
			}//end for
			
		} //end else
		 
		return -1;
	} // end method solveForZero


} // class


