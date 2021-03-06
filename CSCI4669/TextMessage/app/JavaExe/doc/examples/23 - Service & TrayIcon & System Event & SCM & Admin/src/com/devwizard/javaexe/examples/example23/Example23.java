/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 23                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example23;


import java.awt.*;
import java.awt.event.*;

import com.devwizard.javaexe.examples.common.event.*;


/*****************************************************************************/
public class Example23 extends Examples_DialogEvent
{
	/*******************************************/
	public Example23()
	{
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setVisible(false);
	}

	/*******************************************/
	public void putPanels()
	{
		super.putPanels();

		tableLog.setIsFileLog(false);
	}

	/*******************************************/
	public void putButtons()
	{
		super.putButtons();

		if(butOK != null)
			butOK.setText(" Hide ");
	}

	/*******************************************/
	public Point getXY()
	{
		Point pt   = new Point(0,0);
		Toolkit tk = Toolkit.getDefaultToolkit();

		if(tk != null)
			try
			{
				Dimension dim = tk.getScreenSize();

				pt.x = (dim.width - APP_WIDTH - 5);
				pt.y = (dim.height - APP_HEIGHT - 50);
			}
			catch(Exception ex) {}

		return pt;
	}

	/*******************************************/
	public void actionPerformed(ActionEvent evt)
	{
		Object obj = evt.getSource();

		if(obj==butOK)
			setVisible(false);
		else
			super.actionPerformed(evt);
	}

	/*******************************************/
	public void setVisible(boolean b)
	{
		super.setVisible(b);
		toFront();
	}


	/*---------------------------------------------------*/

	public static Example23 app = null;


	/*******************************************/
	public static void createApp()
	{
		if(app==null)
			app = new Example23();
	}

	/*******************************************/
	public static boolean isShow()
	{
		return(app != null && app.isVisible());
	}
}
