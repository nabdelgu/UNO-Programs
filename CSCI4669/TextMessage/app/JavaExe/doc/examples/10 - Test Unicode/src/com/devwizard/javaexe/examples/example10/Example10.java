/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 10                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example10;


import java.awt.*;
import java.util.*;
import javax.swing.*;

import com.devwizard.javaexe.interfaces.*;
import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example10 extends Examples_Frame
	implements JavaExe_I_RegistryManagement
{
	JTextArea txtLog = new Example10_TextArea_Unicode("", 15, 50);


	/*******************************************/
	public Example10()
	{
		this("", "");
	}

	/*******************************************/
	public Example10(String mainArgs, String regVal)
	{
		super("\u2042 \u21A3 Test Unicode \u21A2 \u2042");

		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add("Center", new JScrollPane(txtLog));

		appendStr("********** Main args :", mainArgs);
		appendStr("********** Registry :", regVal);
		appendProperties();

		pack();
		setVisible(true);
	}

	/*******************************************/
	void appendProperties()
	{
		try
		{
			Properties prop = System.getProperties();
			Enumeration enm = (prop==null ? null : prop.propertyNames());

			if(enm != null)
			{
				StringBuffer str = new StringBuffer();

				while(enm.hasMoreElements())
				{
					String name = (String) enm.nextElement();

					if(name.startsWith("javaExe."))
						appendProperty(name, str);
				}

				appendStr("********** System Properties :", str.toString());
				str.setLength(0);
			}
		}
		catch(Exception ex) {}
	}

	/*******************************************/
	void appendProperty(String name, StringBuffer str)
	{
		str.append("\t"+name+" = "+System.getProperty(name)+"\n");
	}

	/*******************************************/
	void appendStr(String title, String txt)
	{
		if(txt != null && !txt.isEmpty())
		{
			appendStr(title);
			appendStr(txt);
		}
	}

	/*******************************************/
	void appendStr(String txt)
	{
		txtLog.append(txt+"\n");
		txtLog.setCaretPosition(txtLog.getText().length());
	}


	/*---------------------------------------------------*/

	static Example10 frame = null;


	/*******************************************/
	static String getStrReg()
	{
		String pathKey = "Software\\JavaExe\\Example10 : Test\u09CCUnicode";
		String nameVal = "name-\u0A8A";
		String regVal  = regGetValueSTR(HKEY_CURRENT_USER, pathKey, nameVal, false);

		if(regVal==null)
		{
			regVal = "--\u0D68\u0D66\u0D67\u0D68--\u203B--";

			if(!regSetValueSTR(HKEY_CURRENT_USER, pathKey, nameVal, regVal, false))
				regVal = null;
		}

		if(regVal != null)
			regVal = "\t"+pathKey+"\\"+nameVal+" = "+regVal+"\n";

		return regVal;
	}

	/*******************************************/
	static String getStrArgs(String[] args)
	{
		StringBuffer str = new StringBuffer();

		if(args != null)
			for(int i=0;i < args.length;i++)
				str.append("\t["+i+"] = "+args[i]+"\n");

		return str.toString();
	}

	/*******************************************/
	public static void main(String[] args)
	{
		Example10_Config.init();
		frame = new Example10(getStrArgs(args), getStrReg());
	}


	/*---------------------------------------------------*/
	// JavaExe_I_RegistryManagement

	/*******************************************/
	public static final native String  regGetValueSTR(int hkey, String pathKey, String nameValue, boolean isExpandVal);
	public static final native boolean regSetValueSTR(int hkey, String pathKey, String nameValue, String val, boolean isTypeExpand);
}
