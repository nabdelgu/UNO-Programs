/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.common.service;


import java.util.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Examples_ConfigService extends Examples_Config
{
	public static int cnfgPortNumber	  = 12367;
	public static boolean cnfgIsLaunch	  = true;
	public static boolean cnfgAcceptStop  = true;
	public static boolean cnfgAutomatic   = true;
	public static boolean cnfgAutoDelayed = false;
	public static String cnfgResetTime = "";
	public static String cnfgLstAction = "";
	public static String cnfgLstDelay  = "";
	public static String cnfgRunExe    = "";
	public static String cnfgRunArgs   = "";
	public static String cnfgBootMsg   = "";


	/*******************************************/
	public static void init(String name)
	{
		Examples_Config.init(name);
		initParameters();
	}

	/*******************************************/
	public static boolean showDialogOption(String title, String msg)
	{
		return showDialogOption(title,msg,true);
	}

	/*******************************************/
	public static boolean showDialogOption(String title, String msg, boolean isAll)
	{
		Examples_FormConfigService form = new Examples_FormConfigService(msg, isAll);

		if(!Examples_UtilsGUI.showConfirmDialog(title,form))
			return false;

		form.saveParameters();

		return true;
	}

	/*******************************************/
	static void initParameters()
	{
		Properties prop = loadProperties();

		if(prop != null)
			setParameters(prop.getProperty("cnfgPortNumber",""+cnfgPortNumber)
				,Boolean.valueOf(prop.getProperty("cnfgIsLaunch",""+cnfgIsLaunch)).booleanValue()
				,Boolean.valueOf(prop.getProperty("cnfgAcceptStop",""+cnfgAcceptStop)).booleanValue()
				,Boolean.valueOf(prop.getProperty("cnfgAutomatic",""+cnfgAutomatic)).booleanValue()
				,Boolean.valueOf(prop.getProperty("cnfgAutoDelayed",""+cnfgAutoDelayed)).booleanValue()
				,prop.getProperty("cnfgLstAction","")
				,prop.getProperty("cnfgLstDelay","")
				,prop.getProperty("cnfgRunExe","")
				,prop.getProperty("cnfgRunArgs","")
				,prop.getProperty("cnfgBootMsg","")
				,prop.getProperty("cnfgResetTime",""));
	}

	/*******************************************/
	static synchronized void setParameters(String port, boolean isRun, boolean isStop, boolean isAuto, boolean isDelay
		,String lstAction, String lstDelay, String runExe, String runArgs, String bootMsg, String resetTime)
	{
		try
		{
			cnfgPortNumber = Integer.parseInt(port);
		}
		catch(Exception ex) {}

		cnfgIsLaunch    = isRun;
		cnfgAcceptStop  = isStop;
		cnfgAutomatic   = isAuto;
		cnfgAutoDelayed = isDelay;
		cnfgLstAction   = lstAction;
		cnfgLstDelay    = lstDelay;
		cnfgRunExe      = (runExe != null && !runExe.isEmpty() ? getRsrcPathname(runExe) : "");
		cnfgRunArgs     = runArgs;
		cnfgBootMsg     = bootMsg;
		cnfgResetTime   = resetTime;
	}

	/*******************************************/
	public static void saveParameters(String port, boolean isRun, boolean isStop, boolean isAuto, boolean isDelay
		,String lstAction, String lstDelay, String runExe, String runArgs, String bootMsg, String resetTime)
	{
		setParameters(port, isRun, isStop, isAuto, isDelay, lstAction, lstDelay, runExe, runArgs, bootMsg, resetTime);
		saveParameters();
	}

	/*******************************************/
	static void saveParameters()
	{
		Properties prop = loadProperties();

		if(prop != null)
		{
			prop.setProperty("cnfgPortNumber", ""+cnfgPortNumber);
			prop.setProperty("cnfgIsLaunch", ""+cnfgIsLaunch);
			prop.setProperty("cnfgAcceptStop", ""+cnfgAcceptStop);
			prop.setProperty("cnfgAutomatic", ""+cnfgAutomatic);
			prop.setProperty("cnfgAutoDelayed", ""+cnfgAutoDelayed);
			prop.setProperty("cnfgLstAction", ""+cnfgLstAction);
			prop.setProperty("cnfgLstDelay", ""+cnfgLstDelay);
			prop.setProperty("cnfgRunExe", ""+cnfgRunExe);
			prop.setProperty("cnfgRunArgs", ""+cnfgRunArgs);
			prop.setProperty("cnfgBootMsg", ""+cnfgBootMsg);
			prop.setProperty("cnfgResetTime", ""+cnfgResetTime);

			saveProperties(prop);
		}
	}
}
