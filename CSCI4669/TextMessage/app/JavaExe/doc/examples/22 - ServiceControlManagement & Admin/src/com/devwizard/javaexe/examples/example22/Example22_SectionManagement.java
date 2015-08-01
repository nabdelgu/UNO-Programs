/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 22                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example22;


import java.awt.*;
import java.io.*;
import java.util.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example22_SectionManagement
{
	/*******************************************/
	public static void refreshAdmin(String nameSvc)
	{
		synchronized(lockName)
		{
			nextNameSvc = nameSvc;
		}
	}

	/*******************************************/
	public static void openDialogChange(String nameSvc)
	{
		sectionStartAdminThread(0, nameSvc, false);
	}


	/*---------------------------------------------------*/
	// JavaExe_I_SectionManagement

	/*******************************************/
	public static native void sectionSetIconAdmin(Component comp);
	public static native boolean sectionStartAdminThread(int numID, Serializable data, boolean isWait);


	/*---------------------------------------------------*/
	// JavaExe_I_SectionManagement : User Section

	static String nextNameSvc = null;
	static Object lockName = new Object();


	/*******************************************/
	public static boolean sectionIsDataForAdmin(int numID)
	{
		return(nextNameSvc != null);
	}

	/*******************************************/
	public static Serializable sectionDataForAdmin(int numID)
	{
		synchronized(lockName)
		{
			String data = nextNameSvc;
			nextNameSvc = null;

			return data;
		}
	}

	/*******************************************/
	public static void sectionDataFromAdmin(int numID, Serializable data)
	{
		if("refreshList".equals(data) && Example22.app != null)
			Example22.app.updateList();
	}


	/*---------------------------------------------------*/
	// JavaExe_I_SectionManagement : Admin Section

	static boolean isDataForUser = false;
	static Object lockData = new Object();


	/*******************************************/
	public static boolean sectionIsDataForUser()
	{
		return isDataForUser;
	}

	/*******************************************/
	public static Serializable sectionDataForUser()
	{
		synchronized(lockData)
		{
			isDataForUser = false;

			return "refreshList";
		}
	}

	/*******************************************/
	public static void sectionDataFromUser(Serializable data)
	{
		if((data instanceof String) && Example22_DialogChange.dialog != null)
			Example22_DialogChange.dialog.setInfo((String) data);
	}

	/*******************************************/
	public static void sectionMainAdmin(int numID, Serializable data)
	{
		if(data instanceof String)
			new Example22_DialogChange((String) data, new Example22_I_Callback()
				{
					/*******************************************/
					public void updateList()
					{
						synchronized(lockData)
						{
							isDataForUser = true;
						}
					}

					/*******************************************/
					public void showPanelInfo(String nameSvc, boolean isShow) {}
				});
	}
}
