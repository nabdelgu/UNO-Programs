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
import java.util.*;
import javax.swing.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public abstract class Example13_Frame extends Examples_Frame
	implements ActionListener
{
	protected JButton butAdmin = null;
	protected JButton butCMD   = null;


	/*******************************************/
	public Example13_Frame(boolean isAdmin, String infoFrame, String[] args)
	{
		super("Run as Admin 2");

		Examples_Panel panelSouth = new Examples_Panel(new BorderLayout(), null);

		panelSouth.add(createPanelType(isAdmin), "North");
		panelSouth.add(createPanelButtons(isAdmin), "South");

		setLayout(new BorderLayout());
		add(createPanelArgs(args), "Center");
		add(panelSouth, "South");

		pack();
		setBounds(infoFrame);
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
	protected JComponent createPanelType(boolean isAdmin)
	{
		JLabel lblAdmin = new JLabel(isAdmin ? "Admin" : "User");

		Examples_UtilsGUI.initComponent(lblAdmin, Examples_UtilsGUI.font_TNR_12bold);
		lblAdmin.setForeground(isAdmin ? Color.RED : Color.BLACK);

		Examples_Panel panel = new Examples_Panel(null, 10, 0, 0, 0);
		Examples_UtilsGUI.putComponent(panel, "Account Type : ", lblAdmin);

		return panel;
	}

	/*******************************************/
	protected JComponent createPanelButtons(boolean isAdmin)
	{
		Examples_Panel panelBut = new Examples_Panel(null, 10, 10, 10, 10);

		if(!isAdmin)
		{
			butAdmin = new JButton("Restart Appli as Admin");
			panelBut.putComponent(butAdmin, 1, 1, GridBagConstraints.BOTH, 0, 0, 0, 10);
		}

		butCMD = new JButton("Execute CMD.EXE as Admin");
		panelBut.putLastComponent(butCMD);

		Examples_UtilsGUI.initComponent(new AbstractButton[] { butAdmin, butCMD }
			,this);

		return panelBut;
	}
}
