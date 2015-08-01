/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 9                                                           ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example9;


import java.util.*;
import javax.swing.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example9_ActionForm
{
	/*******************************************/
	public static boolean newKey(int hkeyRoot, Example9_TreeNodeKey nodeKey)
	{
		if(nodeKey==null)
			return false;

		String[] arr = displayForm("New Key", "Name of the New Key :"
			,"JavaExe - ", null, null);

		if(arr==null || arr.length < 1)
			return false;

		return Example9_RegistryManagement.regCreateKey(hkeyRoot
			,nodeKey.getPathKey() + "\\" + arr[0]);
	}

	/*******************************************/
	public static boolean newValue(int hkeyRoot, Example9_TreeNodeKey nodeKey)
	{
		if(nodeKey==null)
			return false;

		String[] arr = displayForm("New Value", null, "JavaExe - ", "Value ", "");

		if(arr==null || arr.length < 2)
			return false;

		return Example9_RegistryManagement.regSetValueSTR(hkeyRoot
			,nodeKey.getPathKey(), arr[0], arr[1], false);
	}

	/*******************************************/
	public static boolean editValue(int hkeyRoot, Example9_TreeNodeVal nodeVal)
	{
		if(nodeVal==null)
			return false;

		String pathKey = nodeVal.getPathKey();
		String name = nodeVal.getName();

		String[] arr = displayForm("Edit Value", null, null, name+" = "
			,Example9_RegistryManagement.regGetValueSTR(hkeyRoot,pathKey,name,false));

		if(arr==null || arr.length < 1)
			return false;

		return Example9_RegistryManagement.regSetValueSTR(hkeyRoot
			,pathKey, name, arr[0], false);
	}

	/*******************************************/
	public static boolean delete(int hkeyRoot, Example9_TreeNode node)
	{
		if(node==null)
			return false;

		String nameValue = node.getName();
		String pathKey = node.getPathKey();
		boolean isKey  = (nameValue==null);

		String[] arr = displayForm("Delete "+(isKey ? "Key" : "Value")
			,"Do you want to delete \""+(isKey ? pathKey : nameValue)+"\" ?"
			,null, null, null);

		if(arr==null)
			return false;

		if(isKey)
			return Example9_RegistryManagement.regDeleteKey(hkeyRoot, pathKey);

		return Example9_RegistryManagement.regDeleteValue(hkeyRoot, pathKey, nameValue);
	}

	/*******************************************/
	static String[] displayForm(String title, String desc, String lblName, String lblVal
		,String val)
	{
		Examples_Panel p = new Examples_Panel();

		if(desc != null)
			p.putLastComponent(new JLabel(desc), 0, 0, 10, 0);

		JTextField txtN = (lblName != null ? Examples_UtilsGUI.putTextfield(p, lblName, 20) : null);
		JTextField txtV = (lblVal  != null ? Examples_UtilsGUI.putTextfield(p, lblVal, 30) : null);

		if(val != null && txtV != null)
			txtV.setText(val);

		if(!Examples_UtilsGUI.showConfirmDialog(title, p))
			return null;

		Vector<String> vect = new Vector<String>();

		if(txtN != null) vect.add("JavaExe - "+txtN.getText().trim());
		if(txtV != null) vect.add(txtV.getText());

		return vect.toArray(new String[0]);
	}
}
