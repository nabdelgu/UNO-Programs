/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 20                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example20;


import com.devwizard.javaexe.interfaces.*;


/*****************************************************************************/
public class Example20_SystemManagement
	implements JavaExe_I_SystemManagement
{
	/*******************************************/
	public static native int systemShutdown(String msg, int timeOut, boolean isReboot, boolean isForce, boolean isPlanned);
	public static native int systemAbortShutdown();
	public static native int systemOpenDialogShutdown(boolean isReboot, boolean isForce);

	public static native int systemStandby(boolean isHibernate, boolean isDisableWake);

	public static native int systemLock();
	public static native int systemUserLogoff(boolean isForce);
	public static native int systemOpenDialogLogoff(boolean isForce);

	public static native int systemSetRequiredState(boolean isNoScreenSaver, boolean isNoDisplayOff, boolean isNoStandby);
	public static native int systemBlockShutdown(String reason);
	public static native int systemUnblockShutdown();

	public static native boolean systemIsShutdownAllowed();
	public static native boolean systemIsHibernateAllowed();
	public static native boolean systemIsStandbyAllowed();
	public static native String systemGetErrorMessage(int numErr);
}
