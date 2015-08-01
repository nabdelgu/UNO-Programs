/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 15                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example15;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public abstract class Example15_FrameMain extends Example15_Frame
{
	JButton butAdmin;
	JComboBox<Integer> cbxNumID;
	JComponent panelSend;


	/*******************************************/
	public Example15_FrameMain(boolean isAdmin)
	{
		super("RunThread as Admin", isAdmin);
	}

	/*******************************************/
	protected JComponent createPanelType(boolean isAdmin)
	{
		JComponent p = super.createPanelType(isAdmin);

		if(p instanceof Examples_Panel)
		{
			butAdmin = new JButton("New Thread as Admin");
			((Examples_Panel) p).add(butAdmin, "East");

			Examples_UtilsGUI.initComponent(new AbstractButton[] { butAdmin }, this);
		}

		return p;
	}

	/*******************************************/
	protected JComponent createPanelSend()
	{
		panelSend = super.createPanelSend();
		enablePanelSend(false);

		return panelSend;
	}

	/*******************************************/
	protected void putOtherSend(Examples_Panel p)
	{
		cbxNumID = new JComboBox<Integer>(createModelNumID());

		Examples_UtilsGUI.putComponent(p, "  to AdminThread", cbxNumID, false);
		Examples_UtilsGUI.initComponent(new JComponent[] { cbxNumID });
	}

	/*******************************************/
	int getSelectedNumID()
	{
		Integer objNumID = (Integer) cbxNumID.getSelectedItem();

		return(objNumID==null ? 0 : objNumID.intValue());
	}

	/*******************************************/
	ComboBoxModel<Integer> createModelNumID()
	{
		return new DefaultComboBoxModel<Integer>(getListID());
	}

	/*******************************************/
	void updateListID()
	{
		if(cbxNumID != null)
		{
			cbxNumID.setModel(createModelNumID());
			enablePanelSend(cbxNumID.getItemCount() > 0);
		}
	}

	/*******************************************/
	Integer[] getListID()
	{
		return new Integer[] {};
	}

	/*******************************************/
	void enablePanelSend(boolean b)
	{
		if(panelSend != null)
		{
			Component[] arr = panelSend.getComponents();

			if(arr != null)
				for(int i=0;i < arr.length;i++)
					arr[i].setEnabled(b);
		}
	}

	/*******************************************/
	public void actionPerformed(ActionEvent evt)
	{
		Object o = evt.getSource();

		if(o==butAdmin)
			runNewThread();
		else
			super.actionPerformed(evt);
	}

	/*******************************************/
	protected abstract void runNewThread();
}
