/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 14                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example14;


import java.awt.*;
import javax.swing.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example14_PanelType extends Examples_Panel
{
	/*******************************************/
	public Example14_PanelType(boolean isAdmin)
	{
		this(isAdmin, 0);
	}

	/*******************************************/
	public Example14_PanelType(boolean isAdmin, int top)
	{
		super(null, top, 0, 0, 0);

		JLabel lblAdmin = new JLabel(isAdmin ? "Admin" : "User");

		Examples_UtilsGUI.initComponent(lblAdmin, Examples_UtilsGUI.font_TNR_12bold);
		lblAdmin.setForeground(isAdmin ? Color.RED : Color.BLACK);

		Examples_UtilsGUI.putComponent(this, "Account Type : ", lblAdmin);
	}
}
