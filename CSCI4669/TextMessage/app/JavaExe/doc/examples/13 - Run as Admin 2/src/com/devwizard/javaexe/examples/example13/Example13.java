/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 13                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example13;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example13 extends Example13_Frame
{
	/*******************************************/
	public Example13(boolean isAdmin, String infoFrame, String[] args)
	{
		super(isAdmin, infoFrame, args);
	}

	/*******************************************/
	protected JComponent createPanelButtons(boolean isAdmin)
	{
		JComponent panel = super.createPanelButtons(isAdmin);

		sectionSetIconAdmin(butAdmin);
		sectionSetIconAdmin(butCMD);

		return panel;
	}

	/*******************************************/
	public void actionPerformed(ActionEvent evt)
	{
		Object o = evt.getSource();

		if(o==butAdmin)
			sectionRestartAsAdmin(getStringBounds(), null);

		else if(o==butCMD)
		{
			String pathWindow = System.getenv("windir");

			if(pathWindow != null)
				sectionExecAsAdmin(pathWindow+"\\system32\\cmd.exe"
					,new String[] { "/K", "dir *.exe" });
		}
	}


	/*---------------------------------------------------*/

	static String dataRestart = null;


	/*******************************************/
	public static void main(String[] args)
	{
		Examples_UtilsGUI.setLookSystem();

		new Example13(sectionIsAdmin(), dataRestart, args);
	}


	/*---------------------------------------------------*/
	// JavaExe_I_SectionManagement

	/*******************************************/
	public static void sectionSetDataRestart(Serializable data)
	{
		if(data instanceof String)
			dataRestart = (String) data;
	}

	/*******************************************/
	public static native boolean sectionIsAdmin();
	public static native boolean sectionExecAsAdmin(String pathname, String[] mainArgs);
	public static native void sectionRestartAsAdmin(Serializable data, String[] args);
	public static native void sectionSetIconAdmin(Component comp);
}
