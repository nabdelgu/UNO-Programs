/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 14                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example14;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public abstract class Example14_Frame extends Examples_Frame
	implements ActionListener
{
	protected JCheckBox chkWait = null;
	protected JButton butAdmin  = new JButton("Run Thread as Admin");
	protected JTextArea txtInfo = new JTextArea(5, 40);


	/*******************************************/
	public Example14_Frame(boolean isAdmin)
	{
		super("Thread as Admin");

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Examples_Panel panelSouth = new Examples_Panel(new BorderLayout(), null);
		panelSouth.add(createPanelType(isAdmin), "North");
		panelSouth.add(createPanelButtons(), "South");

		setLayout(new BorderLayout());
		add(createPanelInfo(), "Center");
		add(panelSouth, "South");

		afterCreatePanels();

		pack();
		setVisible(true);
	}

	/*******************************************/
	protected void afterCreatePanels()
	{
	}

	/*******************************************/
	protected JComponent createPanelInfo()
	{
		Examples_UtilsGUI.initComponent(txtInfo);
		txtInfo.setEditable(false);

		return new JScrollPane(txtInfo);
	}

	/*******************************************/
	protected JComponent createPanelType(boolean isAdmin)
	{
		return new Example14_PanelType(isAdmin, 10);
	}

	/*******************************************/
	protected JComponent createPanelButtons()
	{
		Examples_Panel panelBut = new Examples_Panel(null, 10, 10, 10, 10);

		panelBut.putComponent(butAdmin, 1, 1, GridBagConstraints.BOTH, 0, 0, 0, 10);
		chkWait = Examples_UtilsGUI.putCheckbox(panelBut, "Waiting end");

		Examples_UtilsGUI.initComponent(new AbstractButton[] { butAdmin }, this);
		Examples_UtilsGUI.initComponent(chkWait);

		return panelBut;
	}

	/*******************************************/
	public void addInfo(String txt)
	{
		if(txt != null)
			txtInfo.append(txt+"\n");
	}
}
