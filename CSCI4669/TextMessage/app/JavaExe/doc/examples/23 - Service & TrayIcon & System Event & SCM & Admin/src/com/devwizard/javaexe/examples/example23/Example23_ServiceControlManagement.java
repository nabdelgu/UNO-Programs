/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 23                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example23;


import com.devwizard.javaexe.interfaces.*;


/*****************************************************************************/
public class Example23_ServiceControlManagement
	implements JavaExe_I_ServiceControlManagement
{
	/*******************************************/
	public static native int scmStartService(String nameSvc);
	public static native int scmStopService(String nameSvc);

	public static native String[] scmQueryStatus(String nameSvc);
	public static native String scmGetErrorMessage(int numErr);
}
