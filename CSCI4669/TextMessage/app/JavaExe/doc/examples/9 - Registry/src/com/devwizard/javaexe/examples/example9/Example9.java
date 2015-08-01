/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 9                                                           ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example9;


import java.awt.*;
import javax.swing.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example9 extends Examples_Frame
{
	/*******************************************/
	public Example9()
	{
		super("Example9 : RegistryManagement");

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600,450));

		add(new JScrollPane(new Example9_Tree()), "Center");

		pack();
		setVisible(true);
	}

	/*******************************************/
	public static void main(String[] args)
	{
		Examples_UtilsGUI.setLookSystem();
		new Example9();
	}
}
