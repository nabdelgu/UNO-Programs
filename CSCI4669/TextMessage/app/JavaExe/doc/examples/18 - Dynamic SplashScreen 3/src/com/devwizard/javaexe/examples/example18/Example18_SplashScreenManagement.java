/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 18                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example18;


import java.awt.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example18_SplashScreenManagement
{
	static final Font font = new Font("Times New Roman", Font.BOLD, 16);
	static
	{
		Examples_UtilsGUI.setLookSystem();
	}


	/*******************************************/
	public static int sphGetTickCount()
	{
		return 500;
	}

	/*******************************************/
	public static void sphInit()
	{
		(new Thread(Example18_Config.getThreadInitApp())).start();
	}

	/*******************************************/
	public static boolean sphIsClose()
	{
		return Example18_Config.isFinishThreadInit;
	}

	/*******************************************/
	public static void sphPaint(Graphics2D g, int wWnd, int hWnd)
	{
		Examples_UtilsGUI.setRenderingQuality(g);

		g.setFont(font);
		g.setColor(Color.GRAY);

		String s = Example18_Config.getCurrentLbl();
		int x = 0;
		int y = 0;

		FontMetrics fm = g.getFontMetrics();

		if(fm != null)
		{
			x = (wWnd - fm.stringWidth(s)) / 2;
			y = fm.getAscent();
		}

		g.drawString(s, x, y);
	}

	/*******************************************/
	public static String[] sphGetProgressBarInfo()
	{
		return new String[]
			{
				 "10"	// X
				,"-5"	// Y
				,"-10"	// W
				,"20"	// H
				,""+Example18_Config.progressMaxValue
			};
	}

	/*******************************************/
	public static int sphGetProgressBarValue()
	{
		return Example18_Config.progressValue;
	}
}
