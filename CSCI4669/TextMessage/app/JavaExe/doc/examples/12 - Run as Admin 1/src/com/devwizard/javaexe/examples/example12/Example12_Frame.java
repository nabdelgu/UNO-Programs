/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 12                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example12;


import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public abstract class Example12_Frame extends Examples_Frame
	implements ActionListener
{
	protected JButton butCMD = null;


	/*******************************************/
	public Example12_Frame(String[] args)
	{
		super("Run as Admin 1");

		Examples_Panel panelSouth = new Examples_Panel(new BorderLayout(), null);

		panelSouth.add(createPanelType(), "North");
		panelSouth.add(createPanelButtons(), "South");

		setLayout(new BorderLayout());
		add(createPanelArgs(args), "Center");
		add(panelSouth, "South");

		pack();
		setVisible(true);
	}

	/*******************************************/
	protected JComponent createPanelArgs(String[] args)
	{
		JTextArea txtArgs = new JTextArea(5, 40);
		String s = "";

		if(args != null)
		{
			s = Arrays.toString(args);

			if(s.startsWith("[")) s = s.substring(1);
			if(s.endsWith("]")) s = s.substring(0, s.length()-1);

			s = s.replaceAll(", ", "\n");
		}

		Examples_UtilsGUI.initComponent(txtArgs);
		txtArgs.setEditable(false);
		txtArgs.setText(s);

		return new JScrollPane(txtArgs);
	}

	/*******************************************/
	protected JComponent createPanelType()
	{
		JLabel lblAdmin = new JLabel("Admin");

		Examples_UtilsGUI.initComponent(lblAdmin, Examples_UtilsGUI.font_TNR_12bold);
		lblAdmin.setForeground(Color.RED);

		Examples_Panel panel = new Examples_Panel(null, 10, 0, 0, 0);
		Examples_UtilsGUI.putComponent(panel, "Account Type : ", lblAdmin);

		return panel;
	}

	/*******************************************/
	protected JComponent createPanelButtons()
	{
		Examples_Panel panelBut = new Examples_Panel(null, 10, 10, 10, 10);

		butCMD = new JButton("Execute CMD.EXE as Admin");
		panelBut.putLastComponent(butCMD);

		Examples_UtilsGUI.initComponent(new AbstractButton[] { butCMD }
			,this);

		return panelBut;
	}
}
