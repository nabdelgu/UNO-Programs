/*
 * JUnit test for the exercise 2.18 from
 * the textbook.
*/ 

import static org.junit.Assert.*;
// Import the annotations that JUnit tests can use
import org.junit.Test; 
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;

// @Test flags a method as a test method.
// @Before indicates that a method will be run before every
//  test method is run.
// @BeforeClass indicates that a method will be run once before
//  any of the other methods in the test suite are run.
// @After indicates that a method will be run after every
//  test method is run.
// @AfterClass indicates that a method will be run once after
//  all the other methods in the test suite finish..

public class TestNumericalAnalysis{
    
        NumericalAnalysis myAnalyzer = new NumericalAnalysis();

	public static final Function CONSTANT = new Function(){
		public double f(double x){				//CONSTANT function
			return 10;
		}
	};

	public final static Function SINE = new Function(){
		public double f(double x){				//SINE function
			return Math.sin(x);
		}
	};


	public final static Function POLYNOMIAL = new Function(){
		public double f(double x){				//POLYNOMIAL fuction
			return 3*x*x - 15*x + 2;
		}
	};

    // Methods flagged with the @Before annotation will be
    @Before // before every test method is run.
    public void setUp(){
       // myAnalyzer = new NumericalAnalysis();
    }

    // Methods flagged with the @Test annotation are the 
    @Test // test methods and will run when the JUnit test is run	
    public void testSolveForZeroSINE(){
        double ans = myAnalyzer.solveForZero(SINE, 2*Math.PI-1,2*Math.PI+1);		//tests if answer is 2PI over the interval {2PI - 1, 2PI + 1}
        assertEquals(6.283185307179586, ans, 0.001); // check that the answer returned			
                                      // is == (1 +- 0.01)
    }	// end of method testSolveForZeroSINE

     @Test // test methods and will run when the JUnit test is run	
    public void testSolveForZeroSINE2(){
        double ans = myAnalyzer.solveForZero(SINE, Math.PI-1,Math.PI+1);		//tests if answer is PI over the interval {PI - 1, PI + 1}
        assertEquals(3.141592653589793, ans, 0.001); // check that the answer returned			
                                      // is == (1 +- 0.01)
    } // end of method testSolveForZeroSINE2	
	
      @Test
      public void testSolveForZeroPOLYNOMIAL(){
        double ans = myAnalyzer.solveForZero(POLYNOMIAL, 0,1);				//tests if answer is 0.1370922327041626 over the interval {0,1}			
        assertEquals(0.1370922327041626, ans, 0.001); // check that the answer returned
                                      // is == (1 +- 0.01)
    } // end if method testSolveForZeroPOLYNOMIAL
	
       @Test
       public void testSolveForZeroPOLYNOMIAL2(){
        double ans = myAnalyzer.solveForZero(POLYNOMIAL, 4,5);				//tests if answer is 4.862907767295837 over the interval {4,5}
        assertEquals(4.862907767295837, ans, 0.001); // check that the answer returned
                                      // is == (1 +- 0.01)
    } // testSolveForZeroPOLYNOMIAL2

}
