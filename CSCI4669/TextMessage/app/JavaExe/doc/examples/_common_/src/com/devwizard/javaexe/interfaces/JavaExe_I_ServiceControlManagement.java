/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/*****************************************************************************/

package com.devwizard.javaexe.interfaces;


/*****************************************************************************/
public interface JavaExe_I_ServiceControlManagement
{
	//--- Service Type
	public static final int SERVICE_KERNEL_DRIVER		= 0x00000001;
	public static final int SERVICE_FILE_SYSTEM_DRIVER	= 0x00000002;
	public static final int SERVICE_ADAPTER				= 0x00000004;
	public static final int SERVICE_RECOGNIZER_DRIVER	= 0x00000008;
	public static final int SERVICE_WIN32_OWN_PROCESS	= 0x00000010;
	public static final int SERVICE_WIN32_SHARE_PROCESS	= 0x00000020;
	public static final int SERVICE_INTERACTIVE_PROCESS	= 0x00000100;
	public static final int SERVICE_WIN32		= (SERVICE_WIN32_OWN_PROCESS | SERVICE_WIN32_SHARE_PROCESS);
	public static final int SERVICE_DRIVER		= (SERVICE_KERNEL_DRIVER | SERVICE_FILE_SYSTEM_DRIVER | SERVICE_RECOGNIZER_DRIVER);
	public static final int SERVICE_TYPE_ALL	= (SERVICE_WIN32 | SERVICE_ADAPTER | SERVICE_DRIVER | SERVICE_INTERACTIVE_PROCESS);

	//--- Start Type
	public static final int SERVICE_BOOT_START		= 0x00000000;
	public static final int SERVICE_SYSTEM_START	= 0x00000001;
	public static final int SERVICE_AUTO_START		= 0x00000002;
	public static final int SERVICE_DEMAND_START	= 0x00000003;
	public static final int SERVICE_DISABLED		= 0x00000004;

	//--- Current Status
	public static final int SERVICE_STOPPED				= 0x00000001;
	public static final int SERVICE_START_PENDING		= 0x00000002;
	public static final int SERVICE_STOP_PENDING		= 0x00000003;
	public static final int SERVICE_RUNNING				= 0x00000004;
	public static final int SERVICE_CONTINUE_PENDING	= 0x00000005;
	public static final int SERVICE_PAUSE_PENDING		= 0x00000006;
	public static final int SERVICE_PAUSED				= 0x00000007;

	//--- Service State
	public static final int SERVICE_ACTIVE		= 0x00000001;
	public static final int SERVICE_INACTIVE	= 0x00000002;
	public static final int SERVICE_STATE_ALL	= (SERVICE_ACTIVE | SERVICE_INACTIVE);

	//--- Controls Code
	public static final int SERVICE_ACCEPT_STOP						= 0x00000001;
	public static final int SERVICE_ACCEPT_PAUSE_CONTINUE			= 0x00000002;
	public static final int SERVICE_ACCEPT_SHUTDOWN					= 0x00000004;
	public static final int SERVICE_ACCEPT_PARAMCHANGE				= 0x00000008;
	public static final int SERVICE_ACCEPT_NETBINDCHANGE			= 0x00000010;
	public static final int SERVICE_ACCEPT_HARDWAREPROFILECHANGE	= 0x00000020;
	public static final int SERVICE_ACCEPT_POWEREVENT				= 0x00000040;
	public static final int SERVICE_ACCEPT_SESSIONCHANGE			= 0x00000080;
	public static final int SERVICE_ACCEPT_PRESHUTDOWN				= 0x00000100;

	//--- Error Code
	public static final int ERROR_SUCCESS					= 0;
	public static final int ERROR_PATH_NOT_FOUND			= 3;
	public static final int ERROR_ACCESS_DENIED				= 5;
	public static final int ERROR_INVALID_NAME				= 123;
	public static final int ERROR_DEPENDENT_SERVICES_RUNNING= 1051;
	public static final int ERROR_INVALID_SERVICE_CONTROL	= 1052;
	public static final int ERROR_SERVICE_REQUEST_TIMEOUT	= 1053;
	public static final int ERROR_SERVICE_NO_THREAD			= 1054;
	public static final int ERROR_SERVICE_DATABASE_LOCKED	= 1055;
	public static final int ERROR_SERVICE_ALREADY_RUNNING	= 1056;
	public static final int ERROR_INVALID_SERVICE_ACCOUNT	= 1057;
	public static final int ERROR_SERVICE_DISABLED			= 1058;
	public static final int ERROR_CIRCULAR_DEPENDENCY		= 1059;
	public static final int ERROR_SERVICE_DOES_NOT_EXIST	= 1060;
	public static final int ERROR_SERVICE_CANNOT_ACCEPT_CTRL= 1061;
	public static final int ERROR_SERVICE_NOT_ACTIVE		= 1062;
	public static final int ERROR_SERVICE_DEPENDENCY_FAIL	= 1068;
	public static final int ERROR_SERVICE_LOGON_FAILED		= 1069;
	public static final int ERROR_SERVICE_MARKED_FOR_DELETE	= 1072;
	public static final int ERROR_SERVICE_EXISTS			= 1073;
	public static final int ERROR_SERVICE_DEPENDENCY_DELETED= 1075;
	public static final int ERROR_DUPLICATE_SERVICE_NAME	= 1078;
	public static final int ERROR_SHUTDOWN_IN_PROGRESS		= 1115;

	//--- Change Config
	public static final int SERVICE_NO_CHANGE = -1;

	//--- Result QueryConfig
	public static final int NDX_CONFIG_NAMELONG = 0;
	public static final int NDX_CONFIG_PATHNAME = 1;
	public static final int NDX_CONFIG_DEPENDS  = 2;
	public static final int NDX_CONFIG_LOGIN    = 3;
	public static final int NDX_CONFIG_TYPE     = 4;
	public static final int NDX_CONFIG_START    = 5;

	//--- Result QueryStatus
	public static final int NDX_STATUS_CURRENT  = 0;
	public static final int NDX_STATUS_TYPE     = 1;
	public static final int NDX_STATUS_CNTRL    = 2;
	public static final int NDX_STATUS_PRCSSID  = 3;

	//--- Result Enum Services
	public static final int NDX_ENUM_NAMESVC  = 0;
	public static final int NDX_ENUM_NAMELONG = 1;
	public static final int NDX_ENUM_STATUS   = 2;
	public static final int NDX_ENUM_TYPE     = 3;
	public static final int NDX_ENUM_CNTRL    = 4;
	public static final int NDX_ENUM_START    = 5;
	public static final int NDX_ENUM_DELAYED  = 6;
	public static final int NDX_ENUM_DESCR    = 7;


	/*******************************************/
	// public static native int scmCreateService(String nameSvc, String nameLong, String descr, String pathnameExe, boolean isAuto, boolean isDelayed);
	// public static native int scmDeleteService(String nameSvc);
	// public static native int scmStartService(String nameSvc);
	// public static native int scmStopService(String nameSvc);
	// public static native int scmPauseService(String nameSvc);
	// public static native int scmContinueService(String nameSvc);

	// public static native int scmChangeConfig(String nameSvc, String nameLong, int serviceType, int startType, String pathnameExe, String dependencies, String login, String passwd);
	// public static native int scmSetDescription(String nameSvc, String descr);
	// public static native int scmSetFailures(String nameSvc, String actions, String delays, String exeFailure, String msgBoot, int resetTime);
	// public static native int scmSetDelayedAutoStart(String nameSvc, boolean isDelayed);

	// public static native String[][] scmEnumServices(int serviceType, int serviceState, boolean isFullInfo);
	// public static native String[][] scmEnumDependentServices(String nameSvc, int serviceState, boolean isFullInfo);

	// public static native String[] scmQueryConfig(String nameSvc);
	// public static native String[] scmQueryStatus(String nameSvc);
	// public static native String[] scmGetFailures(String nameSvc);
	// public static native String scmGetDescription(String nameSvc);
	// public static native String scmGetNameLong(String nameSvc);
	// public static native String scmGetNameSvc(String nameLong);
	// public static native boolean scmIsDelayedAutoStart(String nameSvc);

	// public static native String scmGetErrorMessage(int numErr);
}
