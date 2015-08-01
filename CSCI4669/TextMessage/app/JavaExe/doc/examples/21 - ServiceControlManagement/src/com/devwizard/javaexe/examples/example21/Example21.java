/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 21                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example21;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example21 extends Examples_Dialog
	implements Example21_I_Callback, ActionListener
{
	Example21_TableSvc tableSvc;
	Example21_PanelInfo panelSvc;
	JScrollPane spInfo;
	JCheckBox chkTypeWIN32, chkTypeDRIVER;


	/*******************************************/
	public Example21()
	{
		super(null, "Service Control Management", null, true, false, false, false);

		setVisible(true);
	}

	/*******************************************/
	public void putButtons()
	{
	}

	/*******************************************/
	public void putPanels()
	{
		tableSvc = new Example21_TableSvc(this);
		panelSvc = new Example21_PanelInfo(this);

		chkTypeWIN32  = new JCheckBox("Show SERVICE_WIN32", true);
		chkTypeDRIVER = new JCheckBox("Show SERVICE_DRIVER");

		initComponent(new AbstractButton[] { chkTypeWIN32, chkTypeDRIVER });

		Examples_Panel p = new Examples_Panel(new FlowLayout(FlowLayout.LEFT,20,0), null);
		p.add(chkTypeWIN32);
		p.add(chkTypeDRIVER);

		setComponent(p, "North", false);
		setComponent(new JScrollPane(tableSvc), "Center", false);
		setComponent(spInfo=new JScrollPane(panelSvc), "South", false);

		spInfo.setVisible(false);

		SwingUtilities.invokeLater(new Runnable()
			{
				/*******************************************/
				public void run()
				{
					updateList();
				}
			});
	}

	/*******************************************/
	public void actionPerformed(ActionEvent evt)
	{
		updateList();
	}

	/*******************************************/
	public void updateList()
	{
		if(tableSvc != null)
			tableSvc.updateList(getTypeShow());
	}

	/*******************************************/
	public void showPanelInfo(String nameSvc, boolean isShow)
	{
		if(isShow)
			panelSvc.setInfo(nameSvc);

		spInfo.setVisible(isShow);
		spInfo.invalidate();

		validate();
	}

	/*******************************************/
	public Dimension getWH()
	{
		return new Dimension(APP_WIDTH, APP_HEIGHT);
	}

	/*******************************************/
	int getTypeShow()
	{
		int type = 0;

		if(chkTypeWIN32.isSelected())  type |= Example21_UtilsSVC.SERVICE_WIN32;
		if(chkTypeDRIVER.isSelected()) type |= Example21_UtilsSVC.SERVICE_DRIVER;

		return type;
	}


	/*---------------------------------------------------*/

	static final int APP_WIDTH  = 1000;
	static final int APP_HEIGHT = 600;


	/*******************************************/
	public static void main(String[] args)
	{
		new Example21();
	}
}
