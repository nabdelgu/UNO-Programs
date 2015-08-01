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

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example23_SectionManagement
{
	static final int NUMID_STOPSVC  = 0;
	static final int NUMID_STARTSVC = 1;


	/*******************************************/
	public static void runStartService()
	{
		sectionStartAdminThread(NUMID_STARTSVC, null, false);
	}

	/*******************************************/
	public static void runStopService()
	{
		sectionStartAdminThread(NUMID_STOPSVC, null, false);
	}

	/*******************************************/
	static void displayError(int err)
	{
		if(err != 0)
		{
			String msg = Example23_ServiceControlManagement.scmGetErrorMessage(err);

			Examples_UtilsGUI.showMessageDialog("ServiceControlManagement", msg);
		}
	}


	/*---------------------------------------------------*/
	// JavaExe_I_SectionManagement

	/*******************************************/
	public static native boolean sectionIsAdmin();
	public static native boolean sectionStartAdminThread(int numID, Serializable data, boolean isWait);
	public static native void sectionRestartAsAdmin(Serializable data, String[] mainArgs);


	/*---------------------------------------------------*/
	// JavaExe_I_SectionManagement : Admin Section

	/*******************************************/
	public static void sectionMainAdmin(int numID, Serializable data)
	{
		switch(numID)
		{
			case NUMID_STARTSVC :
				displayError(Example23_ServiceControlManagement.scmStartService(null));
				break;

			case NUMID_STOPSVC :
				displayError(Example23_ServiceControlManagement.scmStopService(null));
				break;
		}
	}
}
