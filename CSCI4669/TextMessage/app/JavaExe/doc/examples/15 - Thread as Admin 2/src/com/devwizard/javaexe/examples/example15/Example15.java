/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 15                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example15;


import java.util.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example15 extends Example15_FrameMain
{
	TreeSet<Integer> tsetNumUse  = new TreeSet<Integer>();
	TreeSet<Integer> tsetNumFree = new TreeSet<Integer>();


	/*******************************************/
	public Example15(boolean isAdmin)
	{
		super(isAdmin);
	}

	/*******************************************/
	protected void afterCreatePanels()
	{
		Example15_SectionManagement.sectionSetIconAdmin(butAdmin);

		setLocation(300,300);
	}

	/*******************************************/
	protected void sendText(String txt)
	{
		int numID = getSelectedNumID();

		if(numID > 0)
			Example15_SectionManagement.sendDataForAdmin(numID, txt);
	}

	/*******************************************/
	protected void runNewThread()
	{
		int numID   = newNumID();
		String info = "";

		if(Example15_SectionManagement.sectionStartAdminThread(numID, "AdminThread ", false))
			info = ("AdminThread " + numID + " is running...");
		else
		{
			deleteNumID(numID);
			info = "*** UAC Elevation Canceled ***";
		}

		addInfo(info);
	}

	/*******************************************/
	int newNumID()
	{
		int numID = 0;

		synchronized(tsetNumFree)
		{
			if(!tsetNumFree.isEmpty())
				numID = tsetNumFree.pollFirst();
		}

		synchronized(tsetNumUse)
		{
			if(numID <= 0)
				numID = (tsetNumUse.isEmpty() ? 0 : tsetNumUse.last()) + 1;

			tsetNumUse.add(numID);
		}

		updateListID();

		return numID;
	}

	/*******************************************/
	void deleteNumID(int numID)
	{
		synchronized(tsetNumUse)
		{
			tsetNumUse.remove(numID);
		}

		synchronized(tsetNumFree)
		{
			tsetNumFree.add(numID);
		}

		updateListID();
	}

	/*******************************************/
	Integer[] getListID()
	{
		if(tsetNumUse != null)
			return tsetNumUse.toArray(new Integer[0]);

		return super.getListID();
	}


	/*---------------------------------------------------*/

	static Example15 app = null;


	/*******************************************/
	public static void closedThread(int numID)
	{
		appendLog("AdminThread " + numID + " is closed");

		if(app != null)
			app.deleteNumID(numID);
	}

	/*******************************************/
	public static void appendLog(String txt)
	{
		if(app != null)
			app.addInfo(txt);
	}

	/*******************************************/
	public static void main(String[] args)
	{
		Examples_UtilsGUI.setLookSystem();

		app = new Example15(Example15_SectionManagement.sectionIsAdmin());
	}
}
