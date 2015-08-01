/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.common;


import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;


/*****************************************************************************/
public class Examples_Table extends JTable
{
	protected DefaultTableModel model;
	String[] lstName;


	/*******************************************/
	public Examples_Table(String[] lstName, int[] lstWidth)
	{
		this.lstName = lstName;

		setNewModel();
		setShowGrid(false);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setAutoResizeMode(AUTO_RESIZE_LAST_COLUMN);
		setIntercellSpacing(new Dimension(0,0));

		TableColumnModel cm = getColumnModel();

		if(cm != null && lstWidth != null)
			try
			{
				for(int i=0;i < lstWidth.length;i++)
				{
					TableColumn c = cm.getColumn(i);

					if(c != null)
						c.setPreferredWidth(lstWidth[i]);
				}
			}
			catch(Exception ex) {}
	}

	/*******************************************/
	public void setNewModel()
	{
		model = new DefaultTableModel(lstName, 0);

		setModel(model);
	}

	/*******************************************/
	public void appendData(String[] data)
	{
		if(data != null && model != null)
			model.addRow(data);
	}

	/*******************************************/
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
}
