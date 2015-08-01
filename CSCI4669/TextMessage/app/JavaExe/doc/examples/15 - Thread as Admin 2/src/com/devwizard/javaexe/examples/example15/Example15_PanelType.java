/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 15                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example15;


import java.awt.*;
import javax.swing.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example15_PanelType extends Examples_Panel
{
	/*******************************************/
	public Example15_PanelType(boolean isAdmin)
	{
		super(null);

		JLabel lblAdmin = new JLabel(isAdmin ? "Admin" : "User");

		Examples_UtilsGUI.initComponent(lblAdmin, Examples_UtilsGUI.font_TNR_12bold);
		lblAdmin.setForeground(isAdmin ? Color.RED : Color.BLACK);

		Examples_UtilsGUI.putComponent(this, "Account Type : ", lblAdmin);
	}
}
