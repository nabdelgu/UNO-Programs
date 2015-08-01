/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 9                                                           ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example9;


import com.devwizard.javaexe.interfaces.*;


/*****************************************************************************/
public class Example9_RegistryManagement
	implements JavaExe_I_RegistryManagement
{
	/*******************************************/
	public static native String regGetValueSTR(int hkey, String pathKey, String nameValue, boolean isExpandVal);
	public static native byte[] regGetValueBIN(int hkey, String pathKey, String nameValue);
	public static native int regGetValueDWORD(int hkey, String pathKey, String nameValue);
	public static native long regGetValueQWORD(int hkey, String pathKey, String nameValue);
	public static native String[] regGetValueMULTI(int hkey, String pathKey, String nameValue);

	/*******************************************/
	public static native boolean regSetValueSTR(int hkey, String pathKey, String nameValue, String val, boolean isTypeExpand);
	public static native boolean regSetValueBIN(int hkey, String pathKey, String nameValue, byte[] val);
	public static native boolean regSetValueDWORD(int hkey, String pathKey, String nameValue, int val, boolean isTypeBigEndian);
	public static native boolean regSetValueQWORD(int hkey, String pathKey, String nameValue, long val);
	public static native boolean regSetValueMULTI(int hkey, String pathKey, String nameValue, String[] val);

	/*******************************************/
	public static native int regGetTypeValue(int hkey, String pathKey, String nameValue);

	/*******************************************/
	public static native boolean regCreateKey(int hkey, String pathKey);
	public static native boolean regDeleteKey(int hkey, String pathKey);
	public static native boolean regDeleteValue(int hkey, String pathKey, String nameValue);

	/*******************************************/
	public static native String[] regEnumKeys(int hkey, String pathKey);
	public static native String[] regEnumValues(int hkey, String pathKey);
}
