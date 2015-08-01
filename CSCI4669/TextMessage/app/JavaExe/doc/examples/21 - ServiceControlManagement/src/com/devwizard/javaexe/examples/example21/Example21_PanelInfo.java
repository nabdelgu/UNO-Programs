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
import java.util.*;
import javax.swing.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example21_PanelInfo extends Examples_Panel
	implements ActionListener
{
	JTextArea txtDescr    = new JTextArea("", 5, 40);
	JTextArea txtPathname = new JTextArea("", 0, 40);
	JLabel lblNameShort = new JLabel("");
	JLabel lblNameLong  = new JLabel("");
	JLabel lblLogin     = new JLabel("");
	JLabel lblStatus    = new JLabel("");
	JLabel lblPrcssID   = new JLabel("");
	JLabel lblType      = new JLabel("");
	JLabel lblStart     = new JLabel("");
	JLabel lblLabelLogin, lblLabelPrcssID;
	JButton butConfig = new JButton("Change...");
	JList<String> listDpnd = new JList<String>();
	JList<String> listRef  = new JList<String>();
	String currNameSvc = null;
	Example21_I_Callback callback = null;


	/*******************************************/
	public Example21_PanelInfo(Example21_I_Callback callback)
	{
		super(new FlowLayout(FlowLayout.LEFT), null);

		this.callback = callback;

		initTextArea(txtDescr, true);
		initTextArea(txtPathname, false);

		initList(listDpnd);
		initList(listRef);

		Examples_UtilsGUI.initComponent(new AbstractButton[] { butConfig }, this);
		Examples_UtilsGUI.initComponent(new JComponent[]
				{
					 txtDescr, txtPathname
					,lblNameShort, lblNameLong, lblLogin
					,lblStatus, lblPrcssID
					,lblType, lblStart
					,listDpnd, listRef
				}
			,Examples_UtilsGUI.font_TNR_12bold);

		Examples_Panel p2 = new Examples_Panel(new FlowLayout(FlowLayout.RIGHT,0,0), null);
		lblLabelLogin = Examples_UtilsGUI.putComponent(p2, "Login : ", lblLogin);

		Examples_Panel p1 = new Examples_Panel();
		Examples_UtilsGUI.putComponent(p1, "Short Name : ", lblNameShort, false);
		p1.putLastComponent(p2, GridBagConstraints.BOTH);
		Examples_UtilsGUI.putComponent(p1, "Long Name : ", lblNameLong);
		Examples_UtilsGUI.putComponent(p1, "Description ", new JScrollPane(txtDescr));
		Examples_UtilsGUI.putComponent(p1, "Pathname ", new JScrollPane(txtPathname));

		Examples_Panel p4 = new Examples_Panel();
		p4.putComponent(new JLabel("Dependencies :"));
		p4.putLastComponent(new JLabel("Services that depend on this one :"), GridBagConstraints.BOTH, 0, 10);
		p4.putComponent(new JScrollPane(listDpnd));
		p4.putLastComponent(new JScrollPane(listRef), GridBagConstraints.BOTH, 0, 10);

		Examples_Panel p3 = new Examples_Panel();
		Examples_UtilsGUI.putComponent(p3, "Start Type : ", lblStart, false);
		p3.putComponent(Box.createHorizontalBox(), 1, 1, GridBagConstraints.NONE, 20, 0);
		Examples_UtilsGUI.putComponent(p3, "Service Type : ", lblType);
		Examples_UtilsGUI.putComponent(p3, "Current Status : ", lblStatus, false);
		p3.putComponent(Box.createHorizontalBox(), 1, 1, GridBagConstraints.NONE, 20, 0);
		lblLabelPrcssID = Examples_UtilsGUI.putComponent(p3, "Process ID : ", lblPrcssID);
		p3.putLastComponent(Box.createHorizontalBox(), GridBagConstraints.NONE);
		p3.putLastComponent(p4, GridBagConstraints.BOTH, 10, 0);

		Examples_Panel p5 = new Examples_Panel(new FlowLayout(FlowLayout.RIGHT,0,0), null);
		p5.add(butConfig);

		Examples_Panel p6 = new Examples_Panel();
		p6.putLastComponent(p5, GridBagConstraints.BOTH);
		p6.putComponent(p1);
		p6.putLastComponent(p3, GridBagConstraints.BOTH, 0, 10);

		add(p6);
	}

	/*******************************************/
	void initTextArea(JTextArea objTxt, boolean word)
	{
		objTxt.setEditable(false);
		objTxt.setLineWrap(true);
		objTxt.setWrapStyleWord(word);
	}

	/*******************************************/
	void initList(JList objList)
	{
		objList.setVisibleRowCount(4);
		objList.setEnabled(false);
		objList.setFixedCellWidth(200);
	}

	/*******************************************/
	public void actionPerformed(ActionEvent evt)
	{
		Object obj = evt.getSource();

		if(obj==butConfig && currNameSvc != null)
			new Example21_DialogChange(currNameSvc, callback);
	}

	/*******************************************/
	public void setInfo(String nameSvc)
	{
		currNameSvc = nameSvc;

		lblNameShort.setText(nameSvc);
		setTextArea(txtDescr, Example21_UtilsSVC.scmGetDescription(nameSvc));

		setInfoConfig(nameSvc);
		setInfoStatus(nameSvc);
	}

	/*******************************************/
	void setInfoConfig(String nameSvc)
	{
		String[] arrConfig = Example21_UtilsSVC.scmQueryConfig(nameSvc);

		lblNameLong.setText(arrConfig==null ? "" : arrConfig[Example21_UtilsSVC.NDX_CONFIG_NAMELONG]);

		String login = (arrConfig==null ? "" : arrConfig[Example21_UtilsSVC.NDX_CONFIG_LOGIN]);
		setLabelVal(lblLabelLogin, lblLogin, login, (login != null && !login.isEmpty()));

		setTextArea(txtPathname, (arrConfig==null ? "" : arrConfig[Example21_UtilsSVC.NDX_CONFIG_PATHNAME]));

		String type = (arrConfig==null ? "" : arrConfig[Example21_UtilsSVC.NDX_CONFIG_TYPE]);
		lblType.setText(Example21_UtilsSVC.toStringServiceType(type));

		String start = (arrConfig==null ? "" : arrConfig[Example21_UtilsSVC.NDX_CONFIG_START]);
		lblStart.setText(Example21_UtilsSVC.toStringStartType(start,Example21_UtilsSVC.scmIsDelayedAutoStart(nameSvc)));

		setInfoDepend(arrConfig);
		setInfoRef(nameSvc);
	}

	/*******************************************/
	void setInfoStatus(String nameSvc)
	{
		String[] arrStatus = Example21_UtilsSVC.scmQueryStatus(nameSvc);

		int pid = Example21_UtilsSVC.toInt(arrStatus==null ? "" : arrStatus[Example21_UtilsSVC.NDX_STATUS_PRCSSID]);
		setLabelVal(lblLabelPrcssID, lblPrcssID, ""+pid, (pid > 0));

		String status = (arrStatus==null ? "" : arrStatus[Example21_UtilsSVC.NDX_STATUS_CURRENT]);
		lblStatus.setText(Example21_UtilsSVC.toStringCurrentStatus(status));
	}

	/*******************************************/
	void setInfoDepend(String[] arrConfig)
	{
		String depend = (arrConfig==null ? null : arrConfig[Example21_UtilsSVC.NDX_CONFIG_DEPENDS]);
		String[] arrDpnd = (depend==null || depend.isEmpty() ? null : depend.split("/"));
		Vector<String> vect = new Vector<String>();

		if(arrDpnd != null)
			for(int i=0;i < arrDpnd.length;i++)
			{
				String s = arrDpnd[i].trim();

				if(!s.isEmpty())
					addVectNameSvc(vect, s, Example21_UtilsSVC.scmGetNameLong(s));
			}

		setListData(listDpnd, vect);
	}

	/*******************************************/
	void setInfoRef(String nameSvc)
	{
		String[][] arrRef = Example21_UtilsSVC.scmEnumDependentServices(nameSvc, Example21_UtilsSVC.SERVICE_STATE_ALL, false);
		Vector<String> vect = new Vector<String>();

		if(arrRef != null)
			for(int i=0;i < arrRef.length;i++)
			{
				String[] arr = arrRef[i];

				if(arr != null && arr.length > Example21_UtilsSVC.NDX_ENUM_NAMESVC)
					addVectNameSvc(vect, arr[Example21_UtilsSVC.NDX_ENUM_NAMESVC]
						,(arr.length > Example21_UtilsSVC.NDX_ENUM_NAMELONG ? arr[Example21_UtilsSVC.NDX_ENUM_NAMELONG] : null));
			}

		setListData(listRef, vect);
	}

	/*******************************************/
	void setTextArea(JTextArea objTxt, String s)
	{
		objTxt.setText(s);
		objTxt.setCaretPosition(0);
	}

	/*******************************************/
	void setLabelVal(JLabel lblLabel, JLabel lblVal, String s, boolean isShow)
	{
		lblLabel.setVisible(isShow);
		lblVal.setVisible(isShow);
		lblVal.setText(s);
	}

	/*******************************************/
	void setListData(JList<String> objList, Vector<String> vect)
	{
		if(vect==null || vect.isEmpty())
			objList.setListData(new String[] { " " });
		else
			objList.setListData(vect.toArray(new String[0]));
	}

	/*******************************************/
	void addVectNameSvc(Vector<String> vect, String nameSvc, String nameLong)
	{
		if(nameLong != null && !nameLong.isEmpty())
			nameSvc += " (" + nameLong + ")";

		vect.add(nameSvc);
	}
}
