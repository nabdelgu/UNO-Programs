/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.common.service;


import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Examples_FormConfigService extends Examples_Panel
	implements ActionListener
{
	JTextField txtPort		= new JTextField(""+Examples_ConfigService.cnfgPortNumber, 5);
	JTextField txtRun		= new JTextField(Examples_ConfigService.cnfgRunExe, 20);
	JTextField txtArgs		= new JTextField(Examples_ConfigService.cnfgRunArgs, 10);
	JTextField txtBoot		= new JTextField(Examples_ConfigService.cnfgBootMsg, 20);
	JTextField txtReset		= new JTextField(Examples_ConfigService.cnfgResetTime, 5);
	JCheckBox chkRun		= new JCheckBox("Run server", Examples_ConfigService.cnfgIsLaunch);
	JCheckBox chkStop		= new JCheckBox("Accept Stop", Examples_ConfigService.cnfgAcceptStop);
	JCheckBox chkAuto		= new JCheckBox("Automatic", Examples_ConfigService.cnfgAutomatic);
	JCheckBox chkDelayed	= new JCheckBox("Delayed", Examples_ConfigService.cnfgAutoDelayed);
	Examples_Panel panelRun = new Examples_Panel();
	Examples_Panel panelBoot= new Examples_Panel();

	Vector<JComboBox> vectAction = new Vector<JComboBox>();
	Vector<JTextField> vectDelay = new Vector<JTextField>();


	/*******************************************/
	public Examples_FormConfigService(String msg, boolean isAll)
	{
		Examples_Panel p2 = new Examples_Panel(" Running Parameters ", 10, 0, 0, 0);

		putLastComponent(new JLabel(msg));
		putLastComponent(p2, GridBagConstraints.NONE);

		Examples_UtilsGUI.putComponent(p2, "Port number", txtPort);

		if(isAll)
		{
			Examples_Panel p3 = new Examples_Panel(null, 5, 0, 0, 0);
			p3.putComponent(chkRun);
			p3.putLastComponent(chkAuto, 0, 20);
			p3.putComponent(chkStop);
			p3.putLastComponent(chkDelayed, 0, 20);

			p2.putLastComponent(p3);
			putLastComponent(createPanelFailure());

			txtRun.setText(Examples_ConfigService.cnfgRunExe);
			txtArgs.setText(Examples_ConfigService.cnfgRunArgs);
			txtBoot.setText(Examples_ConfigService.cnfgBootMsg);

			txtRun.setEnabled(false);
			txtArgs.setEnabled(false);

			initComponents();
			chkAuto.addActionListener(this);
		}
	}

	/*******************************************/
	Examples_Panel createPanelAction(int ndx, String[] lstAct, String[] lstDelay)
	{
		Examples_Panel p = new Examples_Panel();
		JComboBox<String> combo  = new JComboBox<String>(new String[] { "NONE", "RESTART", "RUN", "REBOOT" });

		Examples_UtilsGUI.putComponent(p, "Action "+(ndx+1)+" ", combo, false);
		JTextField txt = Examples_UtilsGUI.putTextfield(p, "  delay (in secs)", 5);

		vectAction.add(combo);
		vectDelay.add(txt);

		combo.setSelectedItem(ndx < lstAct.length ? lstAct[ndx] : "NONE");
		txt.setText(ndx < lstDelay.length ? lstDelay[ndx] : "");

		combo.addActionListener(this);

		return p;
	}

	/*******************************************/
	Examples_Panel createPanelFailure()
	{
		String s = Examples_ConfigService.cnfgLstAction;
		if(s==null) s = "";
		String[] lstAct = s.split("/");

		s = Examples_ConfigService.cnfgLstDelay;
		if(s==null) s = "";
		String[] lstDelay = s.split("/");

		Examples_Panel p = new Examples_Panel(" Failure Actions ", 5, 0, 0, 0);

		p.putLastComponent(createPanelAction(0,lstAct,lstDelay), GridBagConstraints.NONE);
		p.putLastComponent(createPanelAction(1,lstAct,lstDelay), GridBagConstraints.NONE);
		p.putLastComponent(createPanelAction(2,lstAct,lstDelay), GridBagConstraints.NONE, 0, 0, 10, 0);

		Examples_UtilsGUI.putComponent(p, "Reset time (in secs)", txtReset);

		p.putLastComponent(panelRun, GridBagConstraints.NONE);
		p.putLastComponent(panelBoot, GridBagConstraints.NONE);

		Examples_UtilsGUI.putComponent(panelRun, "File to run", txtRun, false);
		Examples_UtilsGUI.putComponent(panelRun, "  args", txtArgs);
		Examples_UtilsGUI.putComponent(panelBoot, "Boot Msg", txtBoot);

		return p;
	}

	/*******************************************/
	void initComponents()
	{
		boolean b1 = false;
		boolean b2 = false;

		for(Iterator<JComboBox> itr=vectAction.iterator();itr.hasNext();)
		{
			JComboBox combo = itr.next();
			String s = ""+combo.getSelectedItem();

			if(s.equals("RUN")) b1 = true;
			else if(s.equals("REBOOT")) b2 = true;
		}

		panelRun.setVisible(b1);
		panelBoot.setVisible(b2);

		initParent();
	}

	/*******************************************/
	void initParent()
	{
		Examples_UtilsGUI.windowPack(this);
	}

	/*******************************************/
	public void actionPerformed(ActionEvent evt)
	{
		Object o = evt.getSource();

		if(o==chkAuto)
			chkDelayed.setEnabled(chkAuto.isSelected());
		else
			initComponents();
	}

	/*******************************************/
	public void saveParameters()
	{
		Examples_ConfigService.saveParameters(getText(txtPort)
			,chkRun.isSelected(), chkStop.isSelected()
			,chkAuto.isSelected(), chkDelayed.isSelected()
			,parseVector(vectAction), parseVector(vectDelay)
			,(panelRun.isVisible() ? getText(txtRun) : "")
			,(panelRun.isVisible() ? getText(txtArgs) : "")
			,(panelBoot.isVisible() ? getText(txtBoot) : "")
			,""+getDelay(txtReset,-1));
	}

	/*******************************************/
	String parseVector(Vector<? extends JComponent> vect)
	{
		StringBuffer str = new StringBuffer();

		for(Iterator<? extends JComponent> itr=vect.iterator();itr.hasNext();)
		{
			JComponent comp = itr.next();
			String s = null;

			if(comp instanceof JComboBox)
				s = ""+((JComboBox) comp).getSelectedItem();
			else if(comp instanceof JTextField)
				s = ""+getDelay((JTextField) comp);

			if(s != null)
				appendStr(str, s);
		}

		return str.toString();
	}

	/*******************************************/
	void appendStr(StringBuffer str, String s)
	{
		if(str.length() > 0)
			str.append("/");

		str.append(s);
	}

	/*******************************************/
	int getDelay(JTextField txt)
	{
		return getDelay(txt, 0);
	}

	/*******************************************/
	int getDelay(JTextField txt, int def)
	{
		try
		{
			return Integer.parseInt(getText(txt));
		}
		catch(Exception ex) {}

		return def;
	}

	/*******************************************/
	String getText(JTextField txt)
	{
		return txt.getText().trim();
	}
}
