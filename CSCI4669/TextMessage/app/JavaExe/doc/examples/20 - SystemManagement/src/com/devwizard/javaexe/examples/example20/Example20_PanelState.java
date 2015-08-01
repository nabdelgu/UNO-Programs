/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 20                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example20;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example20_PanelState extends Example20_Panel
	implements ActionListener
{
	JCheckBox chkIsNoSCR, chkIsDisplay, chkIsSystem;


	/*******************************************/
	public Example20_PanelState()
	{
		super(null);

		chkIsNoSCR   = Examples_UtilsGUI.putCheckbox(this, "No ScreenSaver");
		chkIsDisplay = Examples_UtilsGUI.putCheckbox(this, "No Monitor OFF");
		chkIsSystem  = Examples_UtilsGUI.putCheckbox(this, "No Standby");

		Examples_UtilsGUI.initComponent(new AbstractButton[]
			{
				chkIsNoSCR, chkIsDisplay, chkIsSystem
			}, this);
	}

	/*******************************************/
	public void actionPerformed(ActionEvent evt)
	{
		Object o = evt.getSource();

		if(o==chkIsNoSCR || o==chkIsDisplay || o==chkIsSystem)
			requiredState();
	}

	/*******************************************/
	void requiredState()
	{
		int err = Example20_SystemManagement.systemSetRequiredState(chkIsNoSCR.isSelected()
			,chkIsDisplay.isSelected()
			,chkIsSystem.isSelected());

		displayError("systemSetRequiredState", err);
	}
}
