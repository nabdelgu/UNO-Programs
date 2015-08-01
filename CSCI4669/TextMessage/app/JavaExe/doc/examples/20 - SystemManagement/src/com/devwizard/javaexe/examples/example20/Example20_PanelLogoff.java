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
public class Example20_PanelLogoff extends Example20_Panel
	implements ActionListener
{
	JButton butOpen   = new JButton("Open Dialog...");
	JButton butLogoff = new JButton("LogOff");
	JCheckBox chkForce;


	/*******************************************/
	public Example20_PanelLogoff()
	{
		super(" Logoff ");

		chkForce = Examples_UtilsGUI.putCheckbox(this, "Is Force");
		putComponent(butOpen, 1, 1, GridBagConstraints.NONE, 5, 0, 0, 0);
		putLastComponent(butLogoff, GridBagConstraints.NONE, 5, 10);

		Examples_UtilsGUI.initComponent(new AbstractButton[] { butOpen, butLogoff }, this);
		Examples_UtilsGUI.initComponent(new JComponent[] { chkForce });
	}

	/*******************************************/
	public void actionPerformed(ActionEvent evt)
	{
		Object o = evt.getSource();

		if(o==butOpen)
			openDialog();

		else if(o==butLogoff)
			logoff();
	}

	/*******************************************/
	void openDialog()
	{
		int err = Example20_SystemManagement.systemOpenDialogLogoff(chkForce.isSelected());

		displayError("systemOpenDialogLogoff", err);
	}

	/*******************************************/
	void logoff()
	{
		int err = Example20_SystemManagement.systemUserLogoff(chkForce.isSelected());

		displayError("systemUserLogoff", err);
	}
}
