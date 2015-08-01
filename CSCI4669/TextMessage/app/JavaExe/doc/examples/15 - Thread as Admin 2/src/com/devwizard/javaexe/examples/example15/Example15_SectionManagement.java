/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 15                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example15;


import java.awt.*;
import java.io.*;
import java.util.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example15_SectionManagement
{
	/*******************************************/
	public static void sendDataForAdmin(int numID, String txt)
	{
		Vector<String> vect = hashDataForAdmin.get(numID);

		if(vect==null)
		{
			vect = new Vector<String>();
			hashDataForAdmin.put(numID, vect);
		}

		synchronized(vect)
		{
			vect.add(txt);
		}
	}

	/*******************************************/
	public static void sendDataForUser(String txt)
	{
		synchronized(vectDataForUser)
		{
			vectDataForUser.add(txt);
		}
	}


	/*---------------------------------------------------*/
	// JavaExe_I_SectionManagement

	/*******************************************/
	public static native boolean sectionIsAdmin();
	public static native void sectionSetIconAdmin(Component comp);
	public static native boolean sectionStartAdminThread(int numID, Serializable data, boolean isWait);


	/*---------------------------------------------------*/
	// JavaExe_I_SectionManagement : User Section

	static Hashtable<Integer,Vector<String>> hashDataForAdmin = new Hashtable<Integer,Vector<String>>();


	/*******************************************/
	public static boolean sectionIsDataForAdmin(int numID)
	{
		Vector<String> vect = hashDataForAdmin.get(numID);

		return(vect != null && !vect.isEmpty());
	}

	/*******************************************/
	public static Serializable sectionDataForAdmin(int numID)
	{
		Vector<String> vect = hashDataForAdmin.get(numID);

		if(vect != null)
			synchronized(vect)
			{
				return vect.remove(0);
			}

		return null;
	}

	/*******************************************/
	public static void sectionDataFromAdmin(int numID, Serializable data)
	{
		if(data != null)
			Example15.appendLog("Data from AdminThread "+numID+" : "+data.toString());
	}

	/*******************************************/
	public static void sectionClosedAdminThread(int numID)
	{
		Example15.closedThread(numID);
	}


	/*---------------------------------------------------*/
	// JavaExe_I_SectionManagement : Admin Section

	static Example15_Frame frameAdmin = null;
	static Vector<String> vectDataForUser = new Vector<String>();


	/*******************************************/
	public static boolean sectionIsDataForUser()
	{
		return !vectDataForUser.isEmpty();
	}

	/*******************************************/
	public static Serializable sectionDataForUser()
	{
		synchronized(vectDataForUser)
		{
			return vectDataForUser.remove(0);
		}
	}

	/*******************************************/
	public static void sectionDataFromUser(Serializable data)
	{
		if(frameAdmin != null && data != null)
			frameAdmin.addInfo("Data from User : "+data.toString());
	}

	/*******************************************/
	public static void sectionMainAdmin(int numID, Serializable data)
	{
		String title = (data==null ? "" : data.toString()) + numID;

		frameAdmin = new Example15_Frame(title, sectionIsAdmin())
			{
				/*******************************************/
				protected void sendText(String txt)
				{
					Example15_SectionManagement.sendDataForUser(txt);
				}
			};
	}
}
