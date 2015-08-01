/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.common.event;


import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Examples_TableEventLog extends Examples_Table
{
	boolean isFileLog = false;


	/*******************************************/
	public Examples_TableEventLog()
	{
		this(true);
	}

	/*******************************************/
	public Examples_TableEventLog(boolean isFileLog)
	{
		super(new String[] { "Time", "Event", "Parameters" }
			,new int[] { 80, 170, 550 });

		this.isFileLog = isFileLog;
	}

	/*******************************************/
	public void appendLog(String event, String param)
	{
		appendData(new String[] { Examples_Config.getCurrentTime(), event, param });
	}

	/*******************************************/
	public void appendData(String[] data)
	{
		super.appendData(data);

		if(isFileLog)
			Examples_UtilsEvent.logEvent(data);
	}

	/*******************************************/
	public void clearLog()
	{
		for(int nb=model.getRowCount();nb > 0;nb--)
			model.removeRow(0);
	}

	/*******************************************/
	public void setIsFileLog(boolean isLog)
	{
		isFileLog = isLog;
	}
}
