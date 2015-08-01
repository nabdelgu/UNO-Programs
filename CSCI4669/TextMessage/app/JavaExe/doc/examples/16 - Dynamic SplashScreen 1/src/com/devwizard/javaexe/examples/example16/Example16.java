/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 16                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example16;


import java.awt.*;
import java.util.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example16
{
	static final Font font = new Font("Times New Roman", Font.BOLD, 16);
	static long time1 = 0;
	static int phase  = 0;


	/*******************************************/
	public static void main(String[] args)
	{
		Example16_Config.init();
		Example16_Config.showDialogOption();
	}


	/*---------------------------------------------------*/
	// JavaExe_I_SplashScreenManagement

	/*******************************************/
	public static void sphInit()
	{
		time1 = (new Date()).getTime();
	}

	/*******************************************/
	public static int sphGetTickCount()
	{
		return 1000;
	}

	/*******************************************/
	public static boolean sphIsClose()
	{
		long time2 = (new Date()).getTime();

		return(time2-time1 > 5000);
	}

	/*******************************************/
	public static void sphPaint(Graphics2D g, int wWnd, int hWnd)
	{
		Examples_UtilsGUI.setRenderingQuality(g);

		g.setFont(font);
		g.setColor(Color.GRAY);

		String s = "Initialization Phase "+(++phase)+"...";
		int x = 0;

		FontMetrics fm = g.getFontMetrics();

		if(fm != null)
		{
			hWnd -= fm.getDescent();
			x = (wWnd - fm.stringWidth(s)) / 2;
		}

		g.drawString(s, x, hWnd);
	}
}
