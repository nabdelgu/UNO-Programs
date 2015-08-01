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
public class Example20_PanelShutdown extends Example20_Panel
	implements ActionListener
{
	JButton butOpen     = new JButton("Open Dialog...");
	JButton butShutdown = new JButton("Shutdown");
	JButton butAbort    = new JButton("Aborting Shutdown");
	JTextField txtMsg, txtDelay;
	JCheckBox chkReboot, chkForce, chkPlanned;


	/*******************************************/
	public Example20_PanelShutdown()
	{
		super(" Shutdown ");

		JLabel lblSec = new JLabel("(in seconds) ");

		txtMsg = Examples_UtilsGUI.putTextfield(this, "Message", 20, false);
		putComponent(Box.createHorizontalBox(), 1, 1, GridBagConstraints.NONE, 0, 10, 0, 0);
		txtDelay = Examples_UtilsGUI.putTextfield(this, "Delay", 3, false);
		putLastComponent(lblSec);

		Examples_Panel p1 = new Examples_Panel();
		chkReboot  = Examples_UtilsGUI.putCheckbox(p1, "Is Reboot ", false);
		chkForce   = Examples_UtilsGUI.putCheckbox(p1, "Is Force ", false);
		chkPlanned = Examples_UtilsGUI.putCheckbox(p1, "Is Planned");

		Examples_Panel p3 = new Examples_Panel();
		p3.putLastComponent(butShutdown, GridBagConstraints.NONE);

		Examples_Panel p2 = new Examples_Panel(new BorderLayout(), null);
		p2.add(butOpen, "West");
		p2.add(p3, "Center");
		p2.add(butAbort, "East");

		putLastComponent(p1);
		putLastComponent(p2, GridBagConstraints.BOTH, 5, 0);

		Examples_UtilsGUI.initComponent(new AbstractButton[]
			{
				butOpen, butShutdown, butAbort
			}, this);

		Examples_UtilsGUI.initComponent(new JComponent[]
			{
				 txtMsg, txtDelay
				,chkReboot, chkForce, chkPlanned
				,lblSec
			});
	}

	/*******************************************/
	public void actionPerformed(ActionEvent evt)
	{
		Object o = evt.getSource();

		if(o==butOpen)
			openDialog();

		else if(o==butShutdown)
			shutdown();

		else if(o==butAbort)
			abortShutdown();
	}

	/*******************************************/
	void openDialog()
	{
		int err = Example20_SystemManagement.systemOpenDialogShutdown(chkReboot.isSelected()
			,chkForce.isSelected());

		displayError("systemOpenDialogShutdown", err);
	}

	/*******************************************/
	void shutdown()
	{
		int delay = 0;

		try { delay = Integer.parseInt(txtDelay.getText()); }
		catch(Exception ex) {}

		String msg = txtMsg.getText();

		int err = Example20_SystemManagement.systemShutdown((msg==null ? null : msg.trim())
			,delay
			,chkReboot.isSelected()
			,chkForce.isSelected()
			,chkPlanned.isSelected());

		displayError("systemShutdown", err);
	}

	/*******************************************/
	void abortShutdown()
	{
		int err = Example20_SystemManagement.systemAbortShutdown();

		displayError("systemAbortShutdown", err);
	}


	/*---------------------------------------------------*/

	/*******************************************/
	public static boolean isAllowed()
	{
		return Example20_SystemManagement.systemIsShutdownAllowed();
	}
}
