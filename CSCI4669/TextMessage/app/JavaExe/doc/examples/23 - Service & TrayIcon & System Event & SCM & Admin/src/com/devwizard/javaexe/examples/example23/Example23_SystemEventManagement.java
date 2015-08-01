/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 23                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example23;


import com.devwizard.javaexe.examples.common.event.*;


/*****************************************************************************/
public class Example23_SystemEventManagement extends Examples_SystemEventManagement
{
	/*******************************************/
	public static int notifyEvent(int msg, int val1, int val2, String val3, int[] arr1, byte[] arr2)
	{
		Examples_I_LogEvent objLog = new Examples_I_LogEvent()
			{
				/*******************************************/
				public void logEvent(String event, String param)
				{
					String[] data = new String[] { event, param };

					Examples_UtilsEvent.logEvent(data);
					Example23_ServiceManagement.setDataUI(data);
				}
			};

		return notifyEvent(objLog, false, msg, val1, val2, val3, arr1, arr2);
	}
}
