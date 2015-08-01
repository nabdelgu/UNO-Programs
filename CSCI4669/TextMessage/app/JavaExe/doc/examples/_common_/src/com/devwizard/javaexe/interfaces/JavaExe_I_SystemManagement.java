/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/*****************************************************************************/

package com.devwizard.javaexe.interfaces;


/*****************************************************************************/
public interface JavaExe_I_SystemManagement
{
	//--- Error Code
	public static final int ERROR_SUCCESS						= 0;
	public static final int ERROR_ACCESS_DENIED					= 5;
	public static final int ERROR_NOT_SUPPORTED					= 50;
	public static final int ERROR_FAIL_SHUTDOWN					= 351;
	public static final int ERROR_SYSTEM_SHUTDOWN				= 641;
	public static final int ERROR_SHUTDOWN_IN_PROGRESS			= 1115;
	public static final int ERROR_NO_SHUTDOWN_IN_PROGRESS		= 1116;
	public static final int ERROR_SHUTDOWN_IS_SCHEDULED			= 1190;
	public static final int ERROR_SHUTDOWN_USERS_LOGGED_ON		= 1191;
	public static final int ERROR_SERVER_SHUTDOWN_IN_PROGRESS	= 1255;


	/*************************************************/
	// public static native int systemShutdown(String msg, int timeOut, boolean isReboot, boolean isForce, boolean isPlanned);
	// public static native int systemAbortShutdown();
	// public static native int systemOpenDialogShutdown(boolean isReboot, boolean isForce);

	// public static native int systemStandby(boolean isHibernate, boolean isDisableWake);

	// public static native int systemLock();
	// public static native int systemUserLogoff(boolean isForce);
	// public static native int systemOpenDialogLogoff(boolean isForce);

	// public static native int systemSetRequiredState(boolean isNoScreenSaver, boolean isNoDisplayOff, boolean isNoStandby);
	// public static native int systemBlockShutdown(String reason);
	// public static native int systemUnblockShutdown();

	// public static native boolean systemIsLocked();
	// public static native boolean systemIsShutdownAllowed();
	// public static native boolean systemIsHibernateAllowed();
	// public static native boolean systemIsStandbyAllowed();
	// public static native String systemGetErrorMessage(int numErr);
}
