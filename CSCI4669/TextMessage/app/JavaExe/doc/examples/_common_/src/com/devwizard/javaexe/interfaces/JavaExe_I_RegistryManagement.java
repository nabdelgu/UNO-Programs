/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/*****************************************************************************/

package com.devwizard.javaexe.interfaces;


/*****************************************************************************/
public interface JavaExe_I_RegistryManagement
{
	static final int HKEY_CLASSES_ROOT		= 0x80000000;
	static final int HKEY_CURRENT_USER		= 0x80000001;
	static final int HKEY_LOCAL_MACHINE		= 0x80000002;
	static final int HKEY_USERS				= 0x80000003;
	static final int HKEY_PERFORMANCE_DATA	= 0x80000004;
	static final int HKEY_CURRENT_CONFIG	= 0x80000005;
	static final int HKEY_DYN_DATA			= 0x80000006;

	static final int REG_NONE				= 0;
	static final int REG_SZ					= 1;
	static final int REG_EXPAND_SZ			= 2;
	static final int REG_BINARY				= 3;
	static final int REG_DWORD				= 4;
	static final int REG_DWORD_BIG_ENDIAN	= 5;
	static final int REG_LINK				= 6;
	static final int REG_MULTI_SZ			= 7;
	static final int REG_QWORD				= 11;


	/*******************************************/
	// public static native String regGetValueSTR(int hkey, String pathKey, String nameValue, boolean isExpandVal);
	// public static native byte[] regGetValueBIN(int hkey, String pathKey, String nameValue);
	// public static native int regGetValueDWORD(int hkey, String pathKey, String nameValue);
	// public static native long regGetValueQWORD(int hkey, String pathKey, String nameValue);
	// public static native String[] regGetValueMULTI(int hkey, String pathKey, String nameValue);

	// public static native boolean regSetValueSTR(int hkey, String pathKey, String nameValue, String val, boolean isTypeExpand);
	// public static native boolean regSetValueBIN(int hkey, String pathKey, String nameValue, byte[] val);
	// public static native boolean regSetValueDWORD(int hkey, String pathKey, String nameValue, int val, boolean isTypeBigEndian);
	// public static native boolean regSetValueQWORD(int hkey, String pathKey, String nameValue, long val);
	// public static native boolean regSetValueMULTI(int hkey, String pathKey, String nameValue, String[] val);

	// public static native int regGetTypeValue(int hkey, String pathKey, String nameValue);

	// public static native boolean regCreateKey(int hkey, String pathKey);
	// public static native boolean regDeleteKey(int hkey, String pathKey);
	// public static native boolean regDeleteValue(int hkey, String pathKey, String nameValue);

	// public static native String[] regEnumKeys(int hkey, String pathKey);
	// public static native String[] regEnumValues(int hkey, String pathKey);
}
