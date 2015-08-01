/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 18                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example18;


import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example18_Config extends Examples_Config
{
	static final String[] lstLbl =
		{
			 "Initializing..."
			,"Loading resources..."
			,"Loading configuration..."
			,"Preparing to run..."
		};

	public static boolean isFinishThreadInit = false;
	public static int progressValue = 0;
	public static final int progressMaxValue = 20;
	static final int numStepValue = (progressMaxValue / lstLbl.length);


	/*******************************************/
	public static void init()
	{
		init("Example18");

		while(!isFinishThreadInit)
			Thread.yield();
	}

	/*******************************************/
	public static String getCurrentLbl()
	{
		int ndx = (progressValue / numStepValue);

		ndx = Math.max(0, ndx);
		ndx = Math.min(ndx, lstLbl.length-1);

		return lstLbl[ndx];
	}

	/*******************************************/
	public static Runnable getThreadInitApp()
	{
		return new Runnable()
			{
				/*******************************************/
				public void run()
				{
					for(int i=0;i <= progressMaxValue;i++)
					{
//...
						try { Thread.sleep(250); }
						catch(Exception ex) {}
//...

						progressValue++;
					}

					isFinishThreadInit = true;
				}
			};
	}
}
