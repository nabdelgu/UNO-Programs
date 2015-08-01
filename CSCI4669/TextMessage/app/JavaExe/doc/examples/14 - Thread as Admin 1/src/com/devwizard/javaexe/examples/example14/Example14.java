/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 14                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example14;


import java.awt.*;
import java.awt.event.*;
import java.io.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example14 extends Example14_Frame
{
	int numThread = NUMID_THREAD;


	/*******************************************/
	public Example14(boolean isAdmin)
	{
		super(isAdmin);
	}

	/*******************************************/
	protected void afterCreatePanels()
	{
		sectionSetIconAdmin(butAdmin);

		setJMenuBar(new Example14_Menu());
		setLocation(300, 300);
	}

	/*******************************************/
	public void actionPerformed(ActionEvent evt)
	{
		Object o = evt.getSource();

		if(o==butAdmin)
			runNewThread(numThread++, chkWait.isSelected());
	}

	/*******************************************/
	void runNewThread(final int numID, final boolean isWait)
	{
		(new Thread(new Runnable()
			{
				/*******************************************/
				public void run()
				{
					String info = "";

					if(sectionStartAdminThread(numID, "Thread ", isWait))
					{
						String s = (isWait ? " : END" : " is running...");
						info = ("Thread " + numID + s);
					}
					else
						info = "*** UAC Elevation Canceled ***";

					addInfo(info);
				}
			})).start();
	}


	/*---------------------------------------------------*/

	static final int NUMID_ACTADM = 0;
	static final int NUMID_THREAD = 1;

	static
	{
		Examples_UtilsGUI.setLookSystem();
	}


	/*******************************************/
	public static void actionAsUser()
	{
		Examples_UtilsGUI.showInfoDialog("Example14", "Action as Current User");
	}

	/*******************************************/
	public static void actionAsAdmin()
	{
		if(!sectionStartAdminThread(NUMID_ACTADM, null, false))
			Examples_UtilsGUI.showMessageDialog("Example14", "Can't Run this Action");
	}

	/*******************************************/
	public static void main(String[] args)
	{
		new Example14(sectionIsAdmin());
	}


	/*---------------------------------------------------*/
	// JavaExe_I_SectionManagement

	/*******************************************/
	public static void sectionMainAdmin(int numID, Serializable data)
	{
		if(numID==NUMID_ACTADM)
			Examples_UtilsGUI.showInfoDialog("Example14", "Action as Admin");

		else if(numID >= NUMID_THREAD)
		{
			String info = (data==null ? "" : data.toString()) + numID;

			Examples_UtilsGUI.showInfoDialog(info
				,new Example14_PanelType(sectionIsAdmin()));
		}
	}

	/*******************************************/
	public static native boolean sectionIsAdmin();
	public static native void sectionSetIconAdmin(Component comp);
	public static native boolean sectionStartAdminThread(int numID, Serializable data, boolean isWait);
}
