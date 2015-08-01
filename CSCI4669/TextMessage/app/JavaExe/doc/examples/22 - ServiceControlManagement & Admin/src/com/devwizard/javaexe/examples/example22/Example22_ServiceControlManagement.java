/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 22                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example22;


import com.devwizard.javaexe.interfaces.*;


/*****************************************************************************/
public class Example22_ServiceControlManagement
	implements JavaExe_I_ServiceControlManagement
{
	/*******************************************/
	public static native int scmStartService(String nameSvc);
	public static native int scmStopService(String nameSvc);
	public static native int scmPauseService(String nameSvc);
	public static native int scmContinueService(String nameSvc);

	public static native int scmChangeConfig(String nameSvc, String nameLong, int serviceType, int startType, String pathnameExe, String dependencies, String login, String passwd);
	public static native int scmSetDelayedAutoStart(String nameSvc, boolean isDelayed);

	public static native String[][] scmEnumServices(int serviceType, int serviceState, boolean isFullInfo);
	public static native String[][] scmEnumDependentServices(String nameSvc, int serviceState, boolean isFullInfo);

	public static native String[] scmQueryConfig(String nameSvc);
	public static native String[] scmQueryStatus(String nameSvc);
	public static native String scmGetDescription(String nameSvc);
	public static native String scmGetNameLong(String nameSvc);
	public static native boolean scmIsDelayedAutoStart(String nameSvc);

	public static native String scmGetErrorMessage(int numErr);
}
