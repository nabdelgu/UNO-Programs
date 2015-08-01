/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 12                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example12;


import java.awt.event.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example12 extends Example12_Frame
{
	/*******************************************/
	public Example12(String[] args)
	{
		super(args);
	}

	/*******************************************/
	public void actionPerformed(ActionEvent evt)
	{
		Object o = evt.getSource();

		if(o==butCMD)
		{
			String pathWindow = System.getenv("windir");

			if(pathWindow != null)
				sectionExecAsAdmin(pathWindow+"\\system32\\cmd.exe"
					,new String[] { "/K", "dir *.*" });
		}
	}


	/*---------------------------------------------------*/

	/*******************************************/
	public static void main(String[] args)
	{
		Examples_UtilsGUI.setLookSystem();

		new Example12(args);
	}


	/*---------------------------------------------------*/
	// JavaExe_I_SectionManagement

	/*******************************************/
	public static native boolean sectionExecAsAdmin(String pathname, String[] mainArgs);
}
