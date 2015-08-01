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
import javax.swing.event.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example22_TableSVC extends Examples_Table
{
	Example22_I_Callback callback = null;
	int typeShow = 0;


	/*******************************************/
	public Example22_TableSVC(Example22_I_Callback callback)
	{
		super(new String[] { "Short Name", "Long Name", "Description", "Status", "Start Type", "Service Type" }
			,new int[] { 100, 200, 400, 100, 100, 100 });

		this.callback = callback;

		setAutoCreateRowSorter(true);

		ListSelectionModel mdl = getSelectionModel();

		if(mdl != null)
			mdl.addListSelectionListener(new ListSelectionListener()
				{
					/*******************************************/
					public void valueChanged(ListSelectionEvent evt)
					{
						if(!evt.getValueIsAdjusting())
							showPanelInfo();
					}
				});


		addKeyListener(new KeyAdapter()
			{
				/*******************************************/
				public void keyPressed(KeyEvent evt)
				{
					if(evt.getKeyCode()==KeyEvent.VK_F5)
						updateList();
				}
			});

	}

	/*******************************************/
	public int getTypeShow()
	{
		return typeShow;
	}

	/*******************************************/
	public void updateList(int type)
	{
		typeShow = type;

		updateList();
	}

	/*******************************************/
	public void updateList()
	{
		String nameSvc = getNameSvc(getSelectedRow());

		Example22_UtilsSVC.updateList(this);
		repaint();

		ListSelectionModel mdl = getSelectionModel();

		if(mdl != null)
		{
			int row = getRow(nameSvc);

			mdl.setSelectionInterval(row,row);
		}
	}

	/*******************************************/
	void showPanelInfo()
	{
		if(callback != null)
		{
			int row = getSelectedRow();

			boolean isShow = (row >= 0);
			String nameSvc = (isShow ? getNameSvc(row) : null);

			callback.showPanelInfo(nameSvc, isShow);

			if(isShow)
				scrollToVisible(row);
		}
	}

	/*******************************************/
	void scrollToVisible(final int row)
	{
		SwingUtilities.invokeLater(new Runnable()
			{
				/*******************************************/
				public void run()
				{
					Rectangle rect = getCellRect(row, 0, true);

					if(rect != null)
						scrollRectToVisible(rect);
				}
			});
	}

	/*******************************************/
	int getRow(String nameSvc)
	{
		if(nameSvc==null || nameSvc.isEmpty())
			return -1;

		int col0 = convertColumnIndexToView(0);
		int nb   = getRowCount();

		for(int row=0;row < nb;row++)
		{
			Object val = getValueAt(row, col0);

			if(nameSvc.equals(val))
				return row;
		}

		return -1;
	}

	/*******************************************/
	String getNameSvc(int row)
	{
		Object val = (row >= 0
			? getValueAt(row, convertColumnIndexToView(0))
			: null);

		if(val instanceof String)
			return((String) val);

		return null;
	}
}
