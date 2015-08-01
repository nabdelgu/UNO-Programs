/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 20                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example20;


import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example20_Panel extends Examples_Panel
{
	/*******************************************/
	public Example20_Panel(String title)
	{
		super(title);
	}

	/*******************************************/
	protected void displayError(String title, int err)
	{
		if(err > 0)
			Examples_UtilsGUI.showMessageDialog(title
				,Example20_SystemManagement.systemGetErrorMessage(err));
	}
}
