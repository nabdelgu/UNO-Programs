/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.common;


import java.awt.*;
import javax.swing.*;


/*****************************************************************************/
public class Examples_Frame extends JFrame
{
	/*******************************************/
	public Examples_Frame(String title)
	{
		super(title);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	/*******************************************/
	public String getStringBounds()
	{
		Rectangle rect = getBounds();

		if(rect==null)
			return "";

		return(""+rect.x
			+","+rect.y
			+","+rect.width
			+","+rect.height);
	}

	/*******************************************/
	public void setBounds(String infoFrame)
	{
		if(infoFrame != null)
		{
			String[] arr = infoFrame.split(",");

			if(arr.length >= 4)
				try
				{
					setBounds(Integer.parseInt(arr[0])
						,Integer.parseInt(arr[1])
						,Integer.parseInt(arr[2])
						,Integer.parseInt(arr[3]));
				}
				catch(Exception ex) {}
		}
	}
}
