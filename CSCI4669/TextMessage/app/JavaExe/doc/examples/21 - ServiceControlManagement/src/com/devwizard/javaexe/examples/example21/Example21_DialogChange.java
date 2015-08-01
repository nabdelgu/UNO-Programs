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
public class Example21_DialogChange extends Examples_Dialog
	implements ActionListener
{
	JButton butStart, butStop;
	JButton butPause, butCont;
	JComboBox<String> cbxStart;
	JCheckBox chkDelay;

	final DefaultComboBoxModel<String> cbxModelStartDRV = new DefaultComboBoxModel<String>(new String[]
		{
			 "BOOT"
			,"SYSTEM"
			,"AUTO"
			,"DEMAND"
			,"DISABLED"
		});

	final DefaultComboBoxModel<String> cbxModelStartWIN = new DefaultComboBoxModel<String>(new String[]
		{
			 "AUTO"
			,"DEMAND"
			,"DISABLED"
		});

	Example21_I_Callback callback = null;
	String nameSvc = null;
	int deltaStart = 0;
	boolean isAction = false;


	/*******************************************/
	public Example21_DialogChange(String nameSvc, Example21_I_Callback callback)
	{
		super(null, "Change Config...");

		this.nameSvc  = nameSvc;
		this.callback = callback;

		setInfo(nameSvc);
		setVisible(true);
	}

	/*******************************************/
	public Point getXY()
	{
		return new Point(500, 250);
	}

	/*******************************************/
	public void putButtons()
	{
		putButton(butOK);
	}

	/*******************************************/
	public void putPanels()
	{
		butStart = new JButton("Start");
		butStop  = new JButton("Stop");
		butPause = new JButton("Pause");
		butCont  = new JButton("Continue");
		cbxStart = new JComboBox<String>();
		chkDelay = new JCheckBox("Is Delayed ?");

		initComponent(new JComponent[] { cbxStart }
			,new AbstractButton[]
				{
					 butStart, butStop
					,butPause, butCont
					,chkDelay
				});

		cbxStart.addActionListener(this);

		Examples_Panel p1 = new Examples_Panel();
		Examples_UtilsGUI.putComponent(p1, "Start Type ", cbxStart, false);
		p1.putComponent(chkDelay, 1, 1, GridBagConstraints.NONE, 20, 0);
		p1.putLastComponent(Box.createHorizontalBox(), GridBagConstraints.NONE);

		Examples_Panel p2 = new Examples_Panel(new FlowLayout(FlowLayout.LEFT,10,0), null);
		p2.add(butStart);
		p2.add(butStop);
		p2.add(butPause);
		p2.add(butCont);

		Examples_Panel p = new Examples_Panel();
		p.putLastComponent(p1, GridBagConstraints.NONE);
		p.putLastComponent(p2, GridBagConstraints.NONE, 10, 0);

		setComponent(p, "Center");
	}

	/*******************************************/
	public void actionPerformed(ActionEvent evt)
	{
		Object obj = evt.getSource();
		int err = -1;

		if(isAction)
		{
			if(obj==butStart)
				err = Example21_UtilsSVC.scmStartService(nameSvc);

			else if(obj==butStop)
				err = Example21_UtilsSVC.scmStopService(nameSvc);

			else if(obj==butPause)
				err = Example21_UtilsSVC.scmPauseService(nameSvc);

			else if(obj==butCont)
				err = Example21_UtilsSVC.scmContinueService(nameSvc);

			else if(obj==chkDelay)
				err = Example21_UtilsSVC.scmSetDelayedAutoStart(nameSvc, chkDelay.isSelected());

			else if(obj==cbxStart)
				err = changeStartType(nameSvc, cbxStart.getSelectedIndex());
		}

		if(err < 0)
			super.actionPerformed(evt);
		else
		{
			if(err != 0)
				Examples_UtilsGUI.showMessageDialog("Service Control Management", Example21_UtilsSVC.scmGetErrorMessage(err));
			else
				Example21_UtilsSVC.waitPending(nameSvc);

			setInfo(nameSvc);

			if(err==0 && callback != null)
				callback.updateList();
		}
	}

	/*******************************************/
	public int changeStartType(String nameSvc, int start)
	{
		if(start < 0)
			return 0;

		start += deltaStart;

		chkDelay.setVisible(start==Example21_UtilsSVC.SERVICE_AUTO_START);

		return Example21_UtilsSVC.scmChangeConfig(nameSvc, null, Example21_UtilsSVC.SERVICE_NO_CHANGE
			,start
			,null, null, null, null);
	}

	/*******************************************/
	public void setInfo(String nameSvc)
	{
		isAction = false;
		String[] arrConfig = Example21_UtilsSVC.scmQueryConfig(nameSvc);

		int type   = (arrConfig==null ? 0 : Example21_UtilsSVC.toIntServiceType(arrConfig[Example21_UtilsSVC.NDX_CONFIG_TYPE]));
		deltaStart = 0;

		if((type & Example21_UtilsSVC.SERVICE_DRIVER) != 0)
			cbxStart.setModel(cbxModelStartDRV);
		else
		{
			cbxStart.setModel(cbxModelStartWIN);
			deltaStart = 2;
		}

		int start = (arrConfig==null ? 0 : Example21_UtilsSVC.toInt(arrConfig[Example21_UtilsSVC.NDX_CONFIG_START]));
		cbxStart.setSelectedIndex(start-deltaStart);

		chkDelay.setSelected(Example21_UtilsSVC.scmIsDelayedAutoStart(nameSvc));
		chkDelay.setVisible(start==Example21_UtilsSVC.SERVICE_AUTO_START);

		setInfoButton(nameSvc, start);
		isAction = true;
	}

	/*******************************************/
	void setInfoButton(String nameSvc, int startType)
	{
		String[] arrStatus = Example21_UtilsSVC.scmQueryStatus(nameSvc);

		int valStatus = Example21_UtilsSVC.toInt(arrStatus==null ? "" : arrStatus[Example21_UtilsSVC.NDX_STATUS_CURRENT]);
		int cntrl = Example21_UtilsSVC.toInt(arrStatus==null ? "" : arrStatus[Example21_UtilsSVC.NDX_STATUS_CNTRL]);

		boolean isAccpStop  = ((cntrl & Example21_UtilsSVC.SERVICE_ACCEPT_STOP) != 0);
		boolean isAccpPause = ((cntrl & Example21_UtilsSVC.SERVICE_ACCEPT_PAUSE_CONTINUE) != 0);

		boolean isToStart = (valStatus==Example21_UtilsSVC.SERVICE_STOPPED && startType != Example21_UtilsSVC.SERVICE_DISABLED);
		boolean isToStop  = (valStatus != Example21_UtilsSVC.SERVICE_STOPPED && valStatus != Example21_UtilsSVC.SERVICE_STOP_PENDING && isAccpStop);
		boolean isToPause = ((valStatus==Example21_UtilsSVC.SERVICE_RUNNING) && isAccpPause);
		boolean isToCont  = ((valStatus==Example21_UtilsSVC.SERVICE_PAUSED) && isAccpPause);

		butStart.setEnabled(isToStart);
		butStop.setEnabled(isToStop);
		butPause.setEnabled(isToPause);
		butCont.setEnabled(isToCont);
	}
}
