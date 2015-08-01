/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 11                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example11;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example11_Frame extends Examples_Frame
{
	JTextArea txtInfo = new JTextArea("", 5, 40);
	JTextArea txtEdit = new JTextArea("", 10, 40);
	JLabel lbl = new JLabel("Typing something and reboot Windows without exiting this application.");


	/*******************************************/
	public Example11_Frame(String args, String infoFrame, String txtRestored)
	{
		super("Save/Restore Session");

		Container c = getContentPane();
		c.setLayout(new BorderLayout());

		if(args != null && !args.isEmpty())
		{
			c.add("North", new JScrollPane(txtInfo));

			Examples_UtilsGUI.initComponent(new JComponent[] { txtInfo });

			txtInfo.setBackground(null);
			txtInfo.setEditable(false);

			appendStr(txtInfo, args);
		}

		JPanel p = new JPanel(new BorderLayout());
		p.add("North", lbl);
		p.add("Center", new JScrollPane(txtEdit));

		c.add("Center", p);

		Examples_UtilsGUI.initComponent(new JComponent[] { txtEdit });
		Examples_UtilsGUI.initComponent(new JComponent[] { lbl }, Examples_UtilsGUI.font_TNR_12bold);

		lbl.setBorder(new EmptyBorder(10,2,2,2));

		appendStr(txtEdit, txtRestored);

		pack();
		setBounds(infoFrame);
		setVisible(true);
	}

	/*******************************************/
	public String getTxtEdit()
	{
		return txtEdit.getText();
	}

	/*******************************************/
	void appendStr(JTextArea comp, String s)
	{
		if(s != null && !s.isEmpty())
		{
			comp.append(s);
			comp.setCaretPosition(comp.getText().length());
		}
	}
}
