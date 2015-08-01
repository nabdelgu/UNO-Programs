/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 20                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example20;


import java.awt.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example20 extends Examples_Dialog
{
	/*******************************************/
	public Example20()
	{
		super(null, "SystemManagement", null, false, false, true);

		Examples_Panel p2 = createPanelP2();
		Examples_Panel p1 = createPanelP1();
		Examples_Panel p0 = createPanelP0(p1, p2);

		add(p0, "Center");

		pack();
		setVisible(true);
	}

	/*******************************************/
	Examples_Panel createPanelP2()
	{
		Examples_Panel p2 = new Examples_Panel();

		if(Example20_PanelBlockShutdown.isAllowed())
			p2.putComponent(new Example20_PanelBlockShutdown(), 1, 1, GridBagConstraints.BOTH, 0, 0, 0, 10);

		p2.putLastComponent(new Example20_PanelState(), GridBagConstraints.BOTH);

		return p2;
	}

	/*******************************************/
	Examples_Panel createPanelP1()
	{
		Examples_Panel p1 = new Examples_Panel();

		if(Example20_PanelStandby.isAllowed())
			p1.putComponent(new Example20_PanelStandby(), 1, 1, GridBagConstraints.BOTH);

		p1.putLastComponent(new Example20_PanelLogoff(), GridBagConstraints.VERTICAL);

		return p1;
	}

	/*******************************************/
	Examples_Panel createPanelP0(Examples_Panel p1, Examples_Panel p2)
	{
		Examples_Panel p0 = new Examples_Panel();

		if(Example20_PanelShutdown.isAllowed())
			p0.putLastComponent(new Example20_PanelShutdown(), GridBagConstraints.BOTH, 2, 0);

		p0.putLastComponent(p1, GridBagConstraints.NONE, 5, 0);
		p0.putLastComponent(p2, GridBagConstraints.NONE, 5, 0);

		return p0;
	}

	/*******************************************/
	public void putButtons()
	{
		putButton(new Example20_PanelLock());
	}


	/*---------------------------------------------------*/

	/*******************************************/
	public static void main(String[] args)
	{
		Examples_UtilsGUI.setLookSystem();
		new Example20();
	}
}
