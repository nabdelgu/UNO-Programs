/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 23                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example23;


import java.awt.*;
import java.io.*;
import java.util.*;

import com.devwizard.javaexe.interfaces.*;
import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example23_TaskbarManagement
	implements JavaExe_I_TaskbarManagement
{
	static final int ID_MENU_LOG	= 1;
	static final int ID_MENU_SHOW	= 2;
	static final int ID_MENU_START	= 3;
	static final int ID_MENU_STOP	= 4;
	static final int ID_MENU_RERUN	= 5;
	static final int ID_MENU_EXIT	= 6;

	static boolean isShowDetails	= false;
	static boolean isShowBalloon	= true;
	static boolean isDataBalloon	= true;
	static boolean isBalloonSupported = true;
	static boolean isFirstBalloon	= true;

	static Object lockBalloonData = new Object();
	static String lastBalloonData = null;
	static String balloonIcon = ""+NIIF_USER;
	static String balloonData = "Example23 : SystemEvent with JavaExe\n\n"
									+"for more info :\n"
									+"     http://devwizard.free.fr/";


	/*******************************************/
	public static String[][] taskGetMenu(boolean isRightClick, int menuID)
	{
		if(!isRightClick || menuID > 0)
			return null;

		String[] arrStatus = Example23_ServiceControlManagement.scmQueryStatus(null);
		int valStatus = -1;

		if(arrStatus != null && arrStatus.length > 0 && arrStatus[0] != null)
			try { valStatus = Integer.parseInt(arrStatus[0]); }
			catch(Exception ex) {}

		boolean isStarted = (valStatus==Example23_ServiceControlManagement.SERVICE_RUNNING || valStatus==Example23_ServiceControlManagement.SERVICE_PAUSED);
		boolean isStopped = (valStatus==Example23_ServiceControlManagement.SERVICE_STOPPED);

		Vector<String[]> vect = new Vector<String[]>();

		vect.add(new String[] { ""+ID_MENU_LOG,  "Open Log",     "", (Example23.isShow() ? ""+MFS_DISABLED : "") });
		vect.add(new String[] { ""+ID_MENU_SHOW, "Show Balloon", "", (!isBalloonSupported ? ""+MFS_DISABLED : (isShowBalloon ? ""+MFS_CHECKED : "")) });
		vect.add(new String[] { "", "", ""+MFT_SEPARATOR, "" });
		vect.add(new String[] { ""+ID_MENU_START, "Start Service", "", ""+(MFS_ADMIN | (!isStopped ? MFS_DISABLED : 0)) });
		vect.add(new String[] { ""+ID_MENU_STOP,  "Stop Service",  "", ""+(MFS_ADMIN | (!isStarted ? MFS_DISABLED : 0)) });

		if(!Example23_SectionManagement.sectionIsAdmin())
			vect.add(new String[] { ""+ID_MENU_RERUN, "Restart ServiceUI as Admin",  "", ""+MFS_ADMIN });

		vect.add(new String[] { "", "", ""+MFT_SEPARATOR, "" });
		vect.add(new String[] { ""+ID_MENU_EXIT, "Exit ServiceUI", "", "" });

		return vect.toArray(new String[0][0]);
	}

	/*******************************************/
	public static int taskGetDefaultMenuID(boolean isRightClick)
	{
		return ID_MENU_LOG;
	}

	/*******************************************/
	public static void taskDoAction(boolean isRightClick, int menuID)
	{
		int err;

		switch(menuID)
		{
			case ID_MENU_LOG :
				if(Example23.app != null) Example23.app.setVisible(true);
				break;

			case ID_MENU_SHOW :
				isShowBalloon ^= true;
				break;

			case ID_MENU_START :
				Example23_SectionManagement.runStartService();
				break;

			case ID_MENU_STOP :
				Example23_SectionManagement.runStopService();
				break;

			case ID_MENU_RERUN :
				Example23_SectionManagement.sectionRestartAsAdmin(null, null);
				break;

			case ID_MENU_EXIT : System.exit(0);
		}
	}

	/*******************************************/
	public static void taskDoBalloonAction()
	{
		if(lastBalloonData != null && !isShowDetails)
		{
			isShowDetails = true;
			Examples_UtilsGUI.showInfoDialog("Details", lastBalloonData);
			isShowDetails = false;
		}
	}

	/*******************************************/
	public static void taskInit(boolean isServiceUI)
	{
		Example23.createApp();

		if(Example23.app != null)
			Example23.app.setVisible(true);
	}

	/*******************************************/
	public static String[] taskGetInfo()
	{
		return new String[]
			{
				 "JavaExe : Example23 of SystemEvent with Java"	// Description
				,""+ACT_CLICK_MENU								// action "1 click-Right"
				,""+ACT_CLICK_NOP								// action "2 click-Right"
				,""+ACT_CLICK_NOP								// action "1 click-Left"
				,""+ACT_CLICK_OPEN								// action "2 click-Left"
			};
	}

	/*******************************************/
	public static void taskDataFromService(Serializable data)
	{
		while(Example23.app==null)
			try { Thread.sleep(500); }
			catch(Exception ex) {}

		if(data != null)
		{
			String[] arr = (String[]) data;

			Example23.app.logEvent(arr[0], arr[1]);

			if(!isFirstBalloon && isBalloonSupported)
				setBalloon(arr);
		}
	}

	/*******************************************/
	public static void taskErrorNoService()
	{
		Examples_UtilsGUI.showMessageDialog("No Service", "The service isn't created !");
	}

	/*******************************************/
	public static void taskSetBalloonSupported(boolean isSupported)
	{
		isBalloonSupported = isDataBalloon = isSupported;
	}

	/*******************************************/
	public static boolean taskIsBalloonShow()
	{
		return(isDataBalloon && isShowBalloon);
	}

	/*******************************************/
	public static String[] taskGetBalloonInfo()
	{
		String[] ret = null;

		synchronized(lockBalloonData)
		{
			isDataBalloon   = false;
			isFirstBalloon  = false;
			lastBalloonData = balloonData;

			ret = new String[]
				{
					 "JavaExe"				// title
					,balloonData			// text
					,balloonIcon			// icon type
					,"20"					// delay in seconds
				};
		}

		return ret;
	}

	/*******************************************/
	static void setBalloon(String[] arr)
	{
		synchronized(lockBalloonData)
		{
			balloonData   = "New event : "+arr[0];
			balloonIcon   = ""+NIIF_INFO;
			isDataBalloon = true;
		}
	}


	/*---------------------------------------------------*/

	static
	{
		Example23_Config.init();
	}
}
