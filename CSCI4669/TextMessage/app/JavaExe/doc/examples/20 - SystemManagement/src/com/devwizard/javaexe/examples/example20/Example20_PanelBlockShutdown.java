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
public class Example20_PanelBlockShutdown extends Example20_Panel
	implements ActionListener
{
	JButton butBlock  = new JButton("Block Shutdown");
	JButton butUnblock= new JButton("Unblock");
	JTextField txtMsg;


	/*******************************************/
	public Example20_PanelBlockShutdown()
	{
		super(" Block Shutdown ");

		Examples_Panel p1 = new Examples_Panel(new BorderLayout(), null);
		p1.add(butBlock, "West");
		p1.add(butUnblock, "East");

		txtMsg = Examples_UtilsGUI.putTextfield(this, "Reason", 20);
		putLastComponent(p1, GridBagConstraints.BOTH, 5, 0);

		Examples_UtilsGUI.initComponent(new AbstractButton[] { butBlock, butUnblock }, this);
		Examples_UtilsGUI.initComponent(new JComponent[] { txtMsg });

		butUnblock.setEnabled(false);
	}

	/*******************************************/
	public void actionPerformed(ActionEvent evt)
	{
		Object o = evt.getSource();

		if(o==butBlock)
			blockShutdown();

		else if(o==butUnblock)
			unblockShutdown();
	}

	/*******************************************/
	void blockShutdown()
	{
		int err = Example20_SystemManagement.systemBlockShutdown(txtMsg.getText());

		butBlock.setEnabled(err != 0);
		butUnblock.setEnabled(err==0);

		displayError("systemBlockShutdown", err);
	}

	/*******************************************/
	void unblockShutdown()
	{
		int err = Example20_SystemManagement.systemUnblockShutdown();

		butBlock.setEnabled(err==0);
		butUnblock.setEnabled(err != 0);

		displayError("systemUnblockShutdown", err);
	}


	/*---------------------------------------------------*/

	/*******************************************/
	public static boolean isAllowed()
	{
		return(Example20_SystemManagement.systemUnblockShutdown() != Example20_SystemManagement.ERROR_NOT_SUPPORTED);
	}
}
