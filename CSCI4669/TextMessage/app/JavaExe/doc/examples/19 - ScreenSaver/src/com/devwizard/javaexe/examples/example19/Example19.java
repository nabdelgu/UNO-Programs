/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 19                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example19;


import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example19
{
	static final String textJavaExe = "JavaExe";
	static final String textQuit = "Press 'ESC' to Quit";
	static final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
	static final Image img = Examples_UtilsGUI.getImage("JavaExe.jpg");
	static final Font fontSmall = new Font("Times New Roman", Font.BOLD, 16);
	static Font fontBig = null;
	static int IMG_W = 0;
	static int IMG_H = 0;
	static Properties props = null;

	static
	{
		Examples_UtilsGUI.setLookSystem();
	}


 	/*******************************************/
	static void paintTextQuit(Graphics2D g, int wScr, int hScr)
	{
		g.setFont(fontSmall);
		g.setColor(Color.GRAY);

		FontMetrics fm = g.getFontMetrics();
		int w = 0;

		if(fm != null)
		{
			w = fm.stringWidth(textQuit);
			hScr -= fm.getDescent();
		}

		g.drawString(textQuit, wScr-w, hScr);
	}

 	/*******************************************/
	static void paintTextDatetime(Graphics2D g)
	{
		if(dateFormat != null)
		{
			g.setFont(fontSmall);
			g.setColor(Color.GRAY);

			FontMetrics fm = g.getFontMetrics();
			int y = (fm==null ? 20 : fm.getAscent());

			String s = dateFormat.format(new Date());
			g.drawString(s, 0, y);
		}
	}

 	/*******************************************/
	static void paintTextJavaExe(Graphics2D g, int x, int y)
	{
		g.setFont(fontBig);
		g.setColor(Color.RED);
		g.drawString(textJavaExe, x, y);
	}

 	/*******************************************/
	static void paintLogo(Graphics2D g, int x, int y)
	{
		if(img != null)
		{
			FontMetrics fm = g.getFontMetrics();

			y -= IMG_H - (fm==null ? 0 : fm.getDescent());
			g.drawImage(img, x-IMG_W-20, y, IMG_W, IMG_H, null);

			int w = (fm==null ? 0 : fm.stringWidth(textJavaExe));

			if(w > 0)
				g.drawImage(img, x+w+20, y, IMG_W, IMG_H, null);
		}
	}


	/*---------------------------------------------------*/
	// JavaExe_I_ScreenSaverManagement

	/*******************************************/
	public static void scrsvOpenConfig()
	{
		new Example19_DialogConfig();
	}

	/*******************************************/
	public static void scrsvInit()
	{
		Example19_Config.init();

		int fac = Math.max(1, Math.min(Example19_Config.cnfgSize,3));

		fontBig = new Font("Times New Roman", 0, 72*fac);
		IMG_W = 64*fac;
		IMG_H = 64*fac;
	}

	/*******************************************/
	public static void scrsvPaint(Graphics2D g, int wScr, int hScr)
	{
		Examples_UtilsGUI.setRenderingQuality(g);

		int x = (int) (Math.random()*wScr);
		int y = (int) (Math.random()*hScr);

		paintTextDatetime(g);
		paintTextQuit(g, wScr, hScr);
		paintTextJavaExe(g, x, y);
		paintLogo(g, x, y);
	}

	/*******************************************/
	public static boolean scrsvIsExitByMouse(int x, int y, int nbClicks, int button, boolean isUp)
	{
		return(nbClicks >= 2 && button==2);
	}

	/*******************************************/
	public static boolean scrsvIsExitByKey(int keycode, boolean isUp)
	{
		return(keycode==KeyEvent.VK_ESCAPE);
	}

	/*******************************************/
	public static String[] scrsvGetInfo()
	{
		return new String[]
			{
				 "JavaExe : ScreenSaver"			// Description
				,""+Example19_Config.cnfgTrans		// Transparent
				,"FALSE"							// Show Mouse
				,""+Example19_Config.cnfgTickCount	// TickCount
			};
	}
}
