/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 3                                                           ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example3;


import java.util.*;

import com.devwizard.javaexe.examples.common.service.*;


/*****************************************************************************/
public class Example3_ServiceManagement extends Examples_ServiceManagement
{
	/*******************************************/
	public static boolean serviceInit()
	{
		app = new Example3();

		return true;
	}

	/*******************************************/
	public static String[] serviceGetInfo()
	{
		return new String[]
			{
				 "JavaExe : Example3"					// Full name
				,"JavaExe : Example3 of Java Services"	// Desc
				,""+Example3_Config.cnfgAutomatic		// IsAutomatic
				,""+Example3_Config.cnfgAcceptStop		// IsAcceptStop
				,Example3_Config.cnfgRunExe				// failure exe
				,Example3_Config.cnfgRunArgs			// args failure
				,"eventlog"								// dependencies
				,Example3_Config.cnfgLstAction			// ACTION = NONE | REBOOT | RESTART | RUN
				,Example3_Config.cnfgLstDelay			// ActionDelay in seconds
				,Example3_Config.cnfgResetTime			// Reset time in seconds
				,Example3_Config.cnfgBootMsg			// Boot Message
				,""+Example3_Config.cnfgAutoDelayed		// IsAutomatic Delayed
			};
	}


	/*---------------------------------------------------*/

	static
	{
		Example3_Config.init();
	}
}
