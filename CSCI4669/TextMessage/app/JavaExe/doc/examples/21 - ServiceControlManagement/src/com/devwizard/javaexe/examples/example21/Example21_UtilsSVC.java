/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 21                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example21;


import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example21_UtilsSVC extends Example21_ServiceControlManagement
{
	/*******************************************/
	public static void updateList(Example21_TableSvc tableSvc)
	{
		if(tableSvc==null)
			return;

		Examples_UtilsGUI.setCursorWait(tableSvc);
		tableSvc.setNewModel();

		String[][] lstSvc = scmEnumServices(tableSvc.getTypeShow(), SERVICE_STATE_ALL, true);

		if(lstSvc != null)
			for(int i=0;i < lstSvc.length;i++)
			{
				updateList(tableSvc, lstSvc[i]);

				if((i % 5)==0)
					Thread.yield();
			}

		Examples_UtilsGUI.setCursorDefault(tableSvc);
	}

	/*******************************************/
	public static void updateList(Example21_TableSvc tableSvc, String[] svcInfo)
	{
		if(svcInfo != null && svcInfo.length >= 8)
			tableSvc.appendData(new String[]
				{
					 svcInfo[NDX_ENUM_NAMESVC]
					,svcInfo[NDX_ENUM_NAMELONG]
					,svcInfo[NDX_ENUM_DESCR]
					,toStringCurrentStatus(svcInfo[NDX_ENUM_STATUS])
					,toStringStartType(svcInfo[NDX_ENUM_START],svcInfo[NDX_ENUM_DELAYED])
					,toStringServiceType(svcInfo[NDX_ENUM_TYPE])
				});
	}

	/*******************************************/
	public static void waitPending(String nameSvc)
	{
		for(int i=0;i < 60;i++)
			try
			{
				if(!isPending(scmQueryStatus(nameSvc)))
					break;

				Thread.sleep(500);
			}
			catch(Exception ex) {}
	}

	/*******************************************/
	public static boolean isPending(String[] arrStatus)
	{
		int val = toInt(arrStatus==null || arrStatus.length <= NDX_STATUS_CURRENT ? "" : arrStatus[NDX_STATUS_CURRENT]);

		return(val==SERVICE_START_PENDING || val==SERVICE_STOP_PENDING
			|| val==SERVICE_CONTINUE_PENDING || val==SERVICE_PAUSE_PENDING);
	}

	/*******************************************/
	public static String toStringServiceType(String type)
	{
		String s = "";
		int val  = toInt(type);

		switch(val & ~SERVICE_INTERACTIVE_PROCESS)
		{
			case SERVICE_KERNEL_DRIVER			: s = "KERNEL"; break;
			case SERVICE_FILE_SYSTEM_DRIVER		: s = "FILE_SYSTEM"; break;
			case SERVICE_ADAPTER				: s = "ADAPTER"; break;
			case SERVICE_RECOGNIZER_DRIVER		: s = "RECOGNIZER"; break;
			case SERVICE_WIN32_OWN_PROCESS		: s = "WIN32_OWN"; break;
			case SERVICE_WIN32_SHARE_PROCESS	: s = "WIN32_SHARE"; break;
		}

		if((val & SERVICE_INTERACTIVE_PROCESS) != 0)
			s += " (INTERACT)";

		return s;
	}

	/*******************************************/
	public static String toStringStartType(String start, String delayed)
	{
		return toStringStartType(start, (toInt(delayed) != 0));
	}

	/*******************************************/
	public static String toStringStartType(String start, boolean isDelayed)
	{
		switch(toInt(start,-1))
		{
			case SERVICE_BOOT_START		: return "BOOT";
			case SERVICE_SYSTEM_START	: return "SYSTEM";
			case SERVICE_AUTO_START		: return "AUTO"+(isDelayed ? " (DELAYED)" : "");
			case SERVICE_DEMAND_START	: return "DEMAND";
			case SERVICE_DISABLED		: return "DISABLED";
		}

		return "";
	}

	/*******************************************/
	public static String toStringCurrentStatus(String status)
	{
		return toStringCurrentStatus(toInt(status));
	}

	/*******************************************/
	public static String toStringCurrentStatus(int status)
	{
		switch(status)
		{
			case SERVICE_STOPPED			: return "STOPPED";
			case SERVICE_START_PENDING		: return "START_PENDING";
			case SERVICE_STOP_PENDING		: return "STOP_PENDING";
			case SERVICE_RUNNING			: return "RUNNING";
			case SERVICE_CONTINUE_PENDING	: return "CONTINUE_PENDING";
			case SERVICE_PAUSE_PENDING		: return "PAUSE_PENDING";
			case SERVICE_PAUSED				: return "PAUSED";
		}

		return "";
	}

	/*******************************************/
	public static int toIntServiceType(String type)
	{
		return(toInt(type) & ~SERVICE_INTERACTIVE_PROCESS);
	}

	/*******************************************/
	public static int toInt(String s)
	{
		return toInt(s, 0);
	}

	/*******************************************/
	public static int toInt(String s, int def)
	{
		try
		{
			return Integer.parseInt(s);
		}
		catch(Exception ex) {}

		return def;
	}
}
