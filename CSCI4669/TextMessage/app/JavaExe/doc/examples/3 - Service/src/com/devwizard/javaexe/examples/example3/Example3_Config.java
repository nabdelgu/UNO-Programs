/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 3                                                           ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example3;


import com.devwizard.javaexe.examples.common.service.*;


/*****************************************************************************/
public class Example3_Config extends Examples_ConfigService
{
	/*******************************************/
	public static void init()
	{
		init("Example3");

		cnfgRunExe  = "/resource/fail.bat";
		cnfgRunArgs = "123 456";
	}
}
