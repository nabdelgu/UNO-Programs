/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 22                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example22;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example22_DialogChange extends Examples_Dialog
	implements ActionListener
{
	JLabel lblNameShort;
	JButton butStart, butStop;
	JButton butPause, butCont;
	JComboBox<String> cbxStart;
	JCheckBox chkDelay;

	Example22_I_Callback callback = null;
	String nameSvc = null;
	int deltaStart = 0;
	boolean isAction = false;


	/*******************************************/
	public Example22_DialogChange(String nameSvc, Example22_I_Callback callback)
	{
		super(null, "Change Config...");

		this.callback = callback;
		dialog = this;

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
		lblNameShort = new JLabel("");

		butStart = new JButton("Start");
		butStop  = new JButton("Stop");
		butPause = new JButton("Pause");
		butCont  = new JButton("Continue");
		cbxStart = new JComboBox<String>();
		chkDelay = new JCheckBox("Is Delayed ?");

		Examples_UtilsGUI.initComponent(new JComponent[] { lblNameShort }
			,Examples_UtilsGUI.font_TNR_12bold);

		initComponent(new JComponent[] { cbxStart }
			,new AbstractButton[]
				{
					 butStart, butStop
					,butPause, butCont
					,chkDelay
				});

		cbxStart.addActionListener(this);

		Examples_Panel p0 = new Examples_Panel();
		Examples_UtilsGUI.putComponent(p0, "Short Name : ", lblNameShort);

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
		p.putLastComponent(p0, GridBagConstraints.NONE, 0, 0, 5, 0);
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
				err = Example22_UtilsSVC.scmStartService(nameSvc);

			else if(obj==butStop)
				err = Example22_UtilsSVC.scmStopService(nameSvc);

			else if(obj==butPause)
				err = Example22_UtilsSVC.scmPauseService(nameSvc);

			else if(obj==butCont)
				err = Example22_UtilsSVC.scmContinueService(nameSvc);

			else if(obj==chkDelay)
				err = Example22_UtilsSVC.scmSetDelayedAutoStart(nameSvc, chkDelay.isSelected());

			else if(obj==cbxStart)
				err = changeStartType(nameSvc, cbxStart.getSelectedIndex());
		}

		if(err < 0)
			super.actionPerformed(evt);
		else
		{
			if(err != 0)
				Examples_UtilsGUI.showMessageDialog("Service Control Management", Example22_UtilsSVC.scmGetErrorMessage(err));
			else
				Example22_UtilsSVC.waitPending(nameSvc);

			setInfo();

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

		chkDelay.setVisible(start==Example22_UtilsSVC.SERVICE_AUTO_START);

		return Example22_UtilsSVC.scmChangeConfig(nameSvc, null, Example22_UtilsSVC.SERVICE_NO_CHANGE
			,start
			,null, null, null, null);
	}

	/*******************************************/
	public void setInfo(String nameSvc)
	{
		this.nameSvc = nameSvc;

		setInfo();
	}

	/*******************************************/
	public void setInfo()
	{
		isAction = false;
		lblNameShort.setText(nameSvc);

		String[] arrConfig = Example22_UtilsSVC.scmQueryConfig(nameSvc);

		int type   = (arrConfig==null ? 0 : Example22_UtilsSVC.toIntServiceType(arrConfig[Example22_UtilsSVC.NDX_CONFIG_TYPE]));
		deltaStart = 0;

		if((type & Example22_UtilsSVC.SERVICE_DRIVER) != 0)
			cbxStart.setModel(cbxModelStartDRV);
		else
		{
			cbxStart.setModel(cbxModelStartWIN);
			deltaStart = 2;
		}

		int start = (arrConfig==null ? 0 : Example22_UtilsSVC.toInt(arrConfig[Example22_UtilsSVC.NDX_CONFIG_START]));
		cbxStart.setSelectedIndex(start-deltaStart);

		chkDelay.setSelected(Example22_UtilsSVC.scmIsDelayedAutoStart(nameSvc));
		chkDelay.setVisible(start==Example22_UtilsSVC.SERVICE_AUTO_START);

		setInfoButton(nameSvc, start);
		isAction = true;
	}

	/*******************************************/
	void setInfoButton(String nameSvc, int startType)
	{
		String[] arrStatus = Example22_UtilsSVC.scmQueryStatus(nameSvc);

		int valStatus = Example22_UtilsSVC.toInt(arrStatus==null ? "" : arrStatus[Example22_UtilsSVC.NDX_STATUS_CURRENT]);
		int cntrl = Example22_UtilsSVC.toInt(arrStatus==null ? "" : arrStatus[Example22_UtilsSVC.NDX_STATUS_CNTRL]);

		boolean isAccpStop  = ((cntrl & Example22_UtilsSVC.SERVICE_ACCEPT_STOP) != 0);
		boolean isAccpPause = ((cntrl & Example22_UtilsSVC.SERVICE_ACCEPT_PAUSE_CONTINUE) != 0);

		boolean isToStart = (valStatus==Example22_UtilsSVC.SERVICE_STOPPED && startType != Example22_UtilsSVC.SERVICE_DISABLED);
		boolean isToStop  = (valStatus != Example22_UtilsSVC.SERVICE_STOPPED && valStatus != Example22_UtilsSVC.SERVICE_STOP_PENDING && isAccpStop);
		boolean isToPause = ((valStatus==Example22_UtilsSVC.SERVICE_RUNNING) && isAccpPause);
		boolean isToCont  = ((valStatus==Example22_UtilsSVC.SERVICE_PAUSED) && isAccpPause);

		butStart.setEnabled(isToStart);
		butStop.setEnabled(isToStop);
		butPause.setEnabled(isToPause);
		butCont.setEnabled(isToCont);
	}


	/*---------------------------------------------------*/

	public static Example22_DialogChange dialog = null;

	static final DefaultComboBoxModel<String> cbxModelStartDRV = new DefaultComboBoxModel<String>(new String[]
		{
			 "BOOT"
			,"SYSTEM"
			,"AUTO"
			,"DEMAND"
			,"DISABLED"
		});

	static final DefaultComboBoxModel<String> cbxModelStartWIN = new DefaultComboBoxModel<String>(new String[]
		{
			 "AUTO"
			,"DEMAND"
			,"DISABLED"
		});
}
