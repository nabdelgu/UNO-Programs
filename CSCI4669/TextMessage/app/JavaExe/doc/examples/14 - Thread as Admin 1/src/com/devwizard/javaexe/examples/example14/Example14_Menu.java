/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 14                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example14;


import java.awt.event.*;
import javax.swing.*;


/*****************************************************************************/
public class Example14_Menu extends JMenuBar
	implements ActionListener
{
	JMenuItem miActUser  = null;
	JMenuItem miActAdmin = null;
	JMenuItem miActQuit  = null;


	/*******************************************/
	public Example14_Menu()
	{
		JMenu menu = new JMenu("File");

		miActUser  = createItem(menu, "Action as Current User");
		miActAdmin = createItem(menu, "Action as Admin");
		menu.addSeparator();
		miActQuit  = createItem(menu, "Quit");

		Example14.sectionSetIconAdmin(miActAdmin);

		add(menu);
	}

	/*******************************************/
	JMenuItem createItem(JMenu menu, String lbl)
	{
		JMenuItem mi = new JMenuItem(lbl);

		mi.addActionListener(this);
		menu.add(mi);

		return mi;
	}

	/*******************************************/
	public void actionPerformed(ActionEvent evt)
	{
		Object o = evt.getSource();

		if(o==miActUser)
			Example14.actionAsUser();

		else if(o==miActAdmin)
			Example14.actionAsAdmin();

		else if(o==miActQuit)
			System.exit(0);
	}
}
