/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 8                                                           ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example8;


import java.awt.*;
import java.io.*;

import com.devwizard.javaexe.interfaces.*;
import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example8_TaskbarManagement
	implements JavaExe_I_TaskbarManagement
{
	static final int ID_MENU_LOG	= 1;
	static final int ID_MENU_SHOW	= 2;

	static boolean isShowDetails	= false;
	static boolean isShowBalloon	= true;
	static boolean isDataBalloon	= true;
	static boolean isBalloonSupported = true;
	static boolean isFirstBalloon	= true;

	static Object lockBalloonData = new Object();
	static String lastBalloonData = null;
	static String balloonIcon = ""+NIIF_USER;
	static String balloonData = "Example8 : SystemEvent with JavaExe\n\n"
									+"for more info :\n"
									+"     http://devwizard.free.fr/";


	/*******************************************/
	public static String[][] taskGetMenu(boolean isRightClick, int menuID)
	{
		if(!isRightClick || menuID > 0)
			return null;

		return new String[][]
			{
				{ ""+ID_MENU_LOG,  "Open Log",     "", (Example8.isShow() ? ""+MFS_DISABLED : "") },
				{ ""+ID_MENU_SHOW, "Show Balloon", "", (!isBalloonSupported ? ""+MFS_DISABLED : (isShowBalloon ? ""+MFS_CHECKED : "")) },
			};
	}

	/*******************************************/
	public static int taskGetDefaultMenuID(boolean isRightClick)
	{
		return ID_MENU_LOG;
	}

	/*******************************************/
	public static void taskDoAction(boolean isRightClick, int menuID)
	{
		switch(menuID)
		{
			case ID_MENU_LOG :
				if(Example8.app != null) Example8.app.setVisible(true);
				break;

			case ID_MENU_SHOW :
				isShowBalloon ^= true;
				break;
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
		Example8.createApp();

		if(Example8.app != null)
			Example8.app.setVisible(true);
	}

	/*******************************************/
	public static String[] taskGetInfo()
	{
		return new String[]
			{
				 "JavaExe : Example8 of SystemEvent with Java"	// Description
				,""+ACT_CLICK_MENU								// action "1 click-Right"
				,""+ACT_CLICK_NOP								// action "2 click-Right"
				,""+ACT_CLICK_NOP								// action "1 click-Left"
				,""+ACT_CLICK_OPEN								// action "2 click-Left"
			};
	}

	/*******************************************/
	public static void taskDataFromService(Serializable data)
	{
		while(Example8.app==null)
			try { Thread.sleep(500); }
			catch(Exception ex) {}

		if(data != null)
		{
			String[] arr = (String[]) data;

			Example8.app.logEvent(arr[0], arr[1]);

			if(!isFirstBalloon && isBalloonSupported)
				setBalloon(arr);
		}
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
			isDataBalloon = false;
			isFirstBalloon = false;
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
			balloonData = "New event : "+arr[0];
			balloonIcon = ""+NIIF_INFO;
			isDataBalloon = true;
		}
	}


	/*---------------------------------------------------*/

	static
	{
		Example8_Config.init();
	}
}
