/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 19                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example19;


import java.util.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example19_Config extends Examples_Config
{
	public static boolean cnfgTrans = false;
	public static int cnfgTickCount = 1000;
	public static int cnfgSize = 1;


	/*******************************************/
	public static void init()
	{
		init("ScreenSaver");

		Properties prop = loadProperties();

		if(prop != null)
			try
			{
				cnfgTrans = Boolean.valueOf(prop.getProperty("cnfgTrans",""+cnfgTrans)).booleanValue();
				cnfgTickCount = Integer.valueOf(prop.getProperty("cnfgTickCount",""+cnfgTickCount)).intValue();
				cnfgSize = Integer.valueOf(prop.getProperty("cnfgSize",""+cnfgSize)).intValue();
			}
			catch(Exception ex) {}
	}

	/*******************************************/
	public static void save(String txtTick, boolean isTrans, Integer valSize)
	{
		cnfgTrans = isTrans;

		try
		{
			cnfgTickCount = Integer.valueOf(txtTick).intValue();
			cnfgSize = (valSize==null ? 1 : valSize.intValue());
		}
		catch(Exception ex) {}

		save();
	}

	/*******************************************/
	public static void save()
	{
		Properties prop = loadProperties();

		if(prop != null)
		{
			prop.setProperty("cnfgTrans", ""+cnfgTrans);
			prop.setProperty("cnfgTickCount", ""+cnfgTickCount);
			prop.setProperty("cnfgSize", ""+cnfgSize);

			saveProperties(prop);
		}
	}
}
