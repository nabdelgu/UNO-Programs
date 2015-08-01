/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 17                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example17;


/*****************************************************************************/
public class Example17
{
	static boolean isClosed = false;


	/*******************************************/
	public static void main(String[] args)
	{
		Example17_Config.init();

		while(!isClosed)
			Thread.yield();

		Example17_Config.showDialogOption();
	}


	/*---------------------------------------------------*/
	// JavaExe_I_SplashScreenManagement

	/*******************************************/
	public static int sphGetTickCount()
	{
		return 250;
	}

	/*******************************************/
	public static void sphFinish()
	{
		isClosed = true;
	}

	/*******************************************/
	public static String[] sphGetProgressBarInfo()
	{
		return new String[]
			{
				 "0", "-1", "-2", "20"
				,"20"
			};
	}
}
