/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 5                                                           ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example5;


import java.io.*;

import com.devwizard.javaexe.examples.common.service.*;


/*****************************************************************************/
public class Example5_ServiceManagement extends Examples_ServiceManagement
{
	/*******************************************/
	public static boolean serviceInit()
	{
		app = new Example5();

		return true;
	}

	/*******************************************/
	public static String[] serviceGetInfo()
	{
		return new String[]
			{
				 "JavaExe : Example5"								// Full name
				,"JavaExe : Example5 of Java Services & TrayIcon"	// Desc
				,""+Example5_Config.cnfgAutomatic		// IsAutomatic
				,""+Example5_Config.cnfgAcceptStop		// IsAcceptStop
				,Example5_Config.cnfgRunExe				// failure exe
				,Example5_Config.cnfgRunArgs			// args failure
				,""										// dependencies
				,Example5_Config.cnfgLstAction			// ACTION = NONE | REBOOT | RESTART | RUN
				,Example5_Config.cnfgLstDelay			// ActionDelay in seconds
				,Example5_Config.cnfgResetTime			// Reset time
				,Example5_Config.cnfgBootMsg			// Boot Message
				,""+Example5_Config.cnfgAutoDelayed		// IsAutomatic Delayed
			};
	}

	/*******************************************/
	public static void serviceDataFromUI(Serializable data)
	{
		if(app != null && "init".equals(data))
			app.reinit();
	}


	/*---------------------------------------------------*/

	static
	{
		Example5_Config.init();
	}
}
