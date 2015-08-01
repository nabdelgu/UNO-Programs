/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 15                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example15;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public abstract class Example15_Frame extends Examples_Frame
	implements ActionListener
{
	protected JButton butSend    = null;
	protected JTextField txtSend = null;
	protected JTextArea txtInfo  = null;


	/*******************************************/
	public Example15_Frame(String title, boolean isAdmin)
	{
		super(title);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		add(createPanelType(isAdmin), "North");
		add(createPanelInfo(isAdmin), "Center");
		add(createPanelSend(), "South");
		afterCreatePanels();

		pack();
		setVisible(true);
	}

	/*******************************************/
	protected void afterCreatePanels()
	{
	}

	/*******************************************/
	protected JComponent createPanelType(boolean isAdmin)
	{
		Examples_Panel p = new Examples_Panel(new BorderLayout(), null, 5, 5, 5, 5);

		p.add(new Example15_PanelType(isAdmin), "West");

		return p;
	}

	/*******************************************/
	protected JComponent createPanelInfo(boolean isAdmin)
	{
		txtInfo = new JTextArea((isAdmin ? 5 : 20), 50);

		Examples_UtilsGUI.initComponent(txtInfo);
		txtInfo.setEditable(false);

		return new JScrollPane(txtInfo);
	}

	/*******************************************/
	protected JComponent createPanelSend()
	{
		Examples_Panel p = new Examples_Panel(null, 5, 5, 5, 5);

		txtSend = Examples_UtilsGUI.putTextfield(p, "Send Text", 20, false);
		putOtherSend(p);

		butSend = new JButton("Send");
		p.putLastComponent(butSend, GridBagConstraints.BOTH);

		Examples_UtilsGUI.initComponent(new JComponent[] { txtSend });
		Examples_UtilsGUI.initComponent(new AbstractButton[] { butSend }, this);

		return p;
	}

	/*******************************************/
	protected void putOtherSend(Examples_Panel p)
	{
	}

	/*******************************************/
	public void actionPerformed(ActionEvent evt)
	{
		Object o = evt.getSource();

		if(o==butSend)
		{
			sendText(txtSend.getText());
			txtSend.setText("");
		}
	}

	/*******************************************/
	public synchronized void addInfo(String txt)
	{
		if(txtInfo != null && txt != null)
			txtInfo.append(txt+"\n");
	}

	/*******************************************/
	protected abstract void sendText(String txt);
}
