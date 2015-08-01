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
public class Example20_PanelStandby extends Example20_Panel
	implements ActionListener
{
	JButton butStandby = new JButton();
	JCheckBox chkHibernate, chkDisableAwake;


	/*******************************************/
	public Example20_PanelStandby()
	{
		super(" Standby ");

		chkHibernate    = Examples_UtilsGUI.putCheckbox(this, "Is Hibernate ", false);
		chkDisableAwake = Examples_UtilsGUI.putCheckbox(this, "No Awake");
		putLastComponent(butStandby, GridBagConstraints.NONE, 5, 0);

		Examples_UtilsGUI.initComponent(new AbstractButton[] { butStandby }, this);
		Examples_UtilsGUI.initComponent(new JComponent[]
			{
				chkHibernate, chkDisableAwake
			});

		boolean isHiber = Example20_SystemManagement.systemIsHibernateAllowed();
		boolean isSleep = Example20_SystemManagement.systemIsStandbyAllowed();

		butStandby.setText(isSleep ? "Standby" : "Hibernate");
		butStandby.setEnabled(isHiber || isSleep);

 		chkHibernate.setEnabled(isHiber && isSleep);
		chkHibernate.setSelected(isHiber && !isSleep);
	}

	/*******************************************/
	public void actionPerformed(ActionEvent evt)
	{
		Object o = evt.getSource();

		if(o==butStandby)
			standby();
	}

	/*******************************************/
	void standby()
	{
		int err = Example20_SystemManagement.systemStandby(chkHibernate.isSelected()
			,chkDisableAwake.isSelected());

		displayError("systemStandby", err);
	}


	/*---------------------------------------------------*/

	/*******************************************/
	public static boolean isAllowed()
	{
		return(Example20_SystemManagement.systemIsHibernateAllowed()
			|| Example20_SystemManagement.systemIsStandbyAllowed());
	}
}
