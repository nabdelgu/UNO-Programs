/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 9                                                           ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example9;


import java.util.*;


/*****************************************************************************/
public class Example9_TreeNodeVal extends Example9_TreeNode
{
	protected Integer hkeyRoot = 0;
	protected String pathKey = "";
	protected String name = "";


	/*******************************************/
	public Example9_TreeNodeVal(Integer hkeyRoot, String pathKey, String name)
	{
		super(hkeyRoot, pathKey, "");

		this.hkeyRoot = hkeyRoot;
		this.pathKey = pathKey;
		this.name = name;

		refreshValue();
	}

	/*******************************************/
	public void refreshValue()
	{
		setUserObject(getLibelle());
	}

	/*******************************************/
	public String getName()
	{
		return name;
	}

	/*******************************************/
	public String getPathKey()
	{
		return getPathKey(1);
	}

	/*******************************************/
	String getLibelle()
	{
		int type = Example9_RegistryManagement.regGetTypeValue(hkeyRoot, pathKey, name);
		StringBuffer str = new StringBuffer();

		str.append(name);
		str.append("  [" + getStringType(type) + "] = ");
		str.append(getStringData(type));

		return str.toString();
	}

	/*******************************************/
	String getStringType(int type)
	{
		switch(type)
		{
			case Example9_RegistryManagement.REG_SZ					: return "REG_SZ";
			case Example9_RegistryManagement.REG_EXPAND_SZ			: return "REG_EXPAND_SZ";
			case Example9_RegistryManagement.REG_BINARY				: return "REG_BINARY";
			case Example9_RegistryManagement.REG_DWORD				: return "REG_DWORD";
			case Example9_RegistryManagement.REG_DWORD_BIG_ENDIAN	: return "REG_DWORD_BIG_ENDIAN";
			case Example9_RegistryManagement.REG_LINK				: return "REG_LINK";
			case Example9_RegistryManagement.REG_MULTI_SZ			: return "REG_MULTI_SZ";
			case Example9_RegistryManagement.REG_QWORD				: return "REG_QWORD";
		}

		return "???";
	}

	/*******************************************/
	String getStringData(int type)
	{
		switch(type)
		{
			case Example9_RegistryManagement.REG_SZ			:
			case Example9_RegistryManagement.REG_EXPAND_SZ	:
			case Example9_RegistryManagement.REG_LINK		:
				{
					String s = Example9_RegistryManagement.regGetValueSTR(hkeyRoot, pathKey, name, false);
					return(s==null ? null : "\""+s+"\"");
				}

			case Example9_RegistryManagement.REG_MULTI_SZ : return Arrays.toString(Example9_RegistryManagement.regGetValueMULTI(hkeyRoot, pathKey, name));

			case Example9_RegistryManagement.REG_BINARY : return getHexData(Example9_RegistryManagement.regGetValueBIN(hkeyRoot, pathKey, name));

			case Example9_RegistryManagement.REG_DWORD				:
			case Example9_RegistryManagement.REG_DWORD_BIG_ENDIAN	: return ""+Example9_RegistryManagement.regGetValueDWORD(hkeyRoot, pathKey, name);

			case Example9_RegistryManagement.REG_QWORD : return ""+Example9_RegistryManagement.regGetValueQWORD(hkeyRoot, pathKey, name);
		}

		return "";
	}

	/*******************************************/
	String getHexData(byte[] data)
	{
		if(data==null)
			return null;

		StringBuffer str = new StringBuffer();
		str.append("[");

		for(int i=0;i < data.length;i++)
		{
			String s = Integer.toHexString((int) data[i] & 0xFF).toUpperCase();
			if(s.length() < 2) s = "0"+s;

			if(i > 0) str.append(", ");
			str.append("0x"+s);
		}

		str.append("]");

		return str.toString();
	}
}
