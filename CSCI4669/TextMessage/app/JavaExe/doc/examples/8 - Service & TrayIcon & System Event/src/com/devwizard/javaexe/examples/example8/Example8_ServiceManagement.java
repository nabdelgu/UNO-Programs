/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 8                                                           ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example8;


import java.io.*;
import java.util.*;

import com.devwizard.javaexe.interfaces.*;
import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example8_ServiceManagement
	implements JavaExe_I_ServiceManagement
{
	static Vector<Serializable> vectData = new Vector<Serializable>();


	/*******************************************/
	public static boolean serviceIsCreate()
	{
		return Examples_UtilsGUI.showConfirmDialog("Create Service"
			,"Do you want to install the service for SystemEvent ?");
	}

	/*******************************************/
	public static boolean serviceIsLaunch()
	{
		return true;
	}

	/*******************************************/
	public static boolean serviceIsDelete()
	{
		return Examples_UtilsGUI.showConfirmDialog("Delete Service"
			,"This service is already installed.\nDo you want to delete it ?");
	}

	/*******************************************/
	public static boolean serviceInit()
	{
		return true;
	}

	/*******************************************/
	public static void serviceFinish()
	{
		System.exit(0);
	}

	/*******************************************/
	public static String[] serviceGetInfo()
	{
		return new String[]
			{
				 "JavaExe : Example8"						// Full name
				,"JavaExe : Example8 of Java Service & TrayIcon & System Event"	// Desc
				,"1"										// IsAutomatic
				,"1"										// IsAcceptStop
			};
	}

	/*******************************************/
	public static boolean serviceIsDataForUI()
	{
		return !vectData.isEmpty();
	}

	/*******************************************/
	public static Serializable serviceDataForUI()
	{
		synchronized(vectData)
		{
			return vectData.remove(0);
		}
	}

	/*******************************************/
	public static void setDataUI(Serializable data)
	{
		synchronized(vectData)
		{
			vectData.add(data);
		}
	}


	/*---------------------------------------------------*/

	static
	{
		Example8_Config.init();
	}
}
