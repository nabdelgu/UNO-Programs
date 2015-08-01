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
public class Example20_PanelLock extends Example20_Panel
	implements ActionListener
{
	JButton butLock = new JButton("Lock Workstation");


	/*******************************************/
	public Example20_PanelLock()
	{
		super(null);

		putLastComponent(butLock, GridBagConstraints.NONE);

		Examples_UtilsGUI.initComponent(new AbstractButton[] { butLock }, this);
	}

	/*******************************************/
	public void actionPerformed(ActionEvent evt)
	{
		Object o = evt.getSource();

		if(o==butLock)
			lockWindows();
	}

	/*******************************************/
	void lockWindows()
	{
		int err = Example20_SystemManagement.systemLock();

		displayError("systemLock", err);
	}
}
