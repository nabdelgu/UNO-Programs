/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 9                                                           ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example9;


import java.lang.reflect.*;
import java.util.*;
import javax.swing.tree.*;


/*****************************************************************************/
public class Example9_TreeNodeKey extends Example9_TreeNode
{
	/*******************************************/
	public Example9_TreeNodeKey(Integer hkeyRoot, String pathKey, String name)
	{
		super(hkeyRoot, pathKey, name);

		addNodeEmpty();
	}

	/*******************************************/
	public String getPathKey()
	{
		return getPathKey(0);
	}

	/*******************************************/
	public void addNodesFromReg()
	{
		removeAllChildren();

		String pathKey = getPathKey();

		addNodesRegKeys(pathKey);
		addNodesRegVals(pathKey);

		if(isLeaf())
			addNodeEmpty();
	}

	/*******************************************/
	void addNodesRegKeys(String pathKey)
	{
		addNodesReg(Example9_RegistryManagement.regEnumKeys(hkeyRoot,pathKey)
			,pathKey, Example9_TreeNodeKey.class);
	}

	/*******************************************/
	void addNodesRegVals(String pathKey)
	{
		addNodesReg(Example9_RegistryManagement.regEnumValues(hkeyRoot,pathKey)
			,pathKey, Example9_TreeNodeVal.class);
	}

	/*******************************************/
	void addNodesReg(String[] lstName, String pathKey, Class<? extends Example9_TreeNode> clssNode)
	{
		if(lstName != null && clssNode != null)
			try
			{
				Arrays.sort(lstName, String.CASE_INSENSITIVE_ORDER);
				Constructor<? extends Example9_TreeNode> cons = clssNode.getConstructor(Integer.class, String.class, String.class);

				if(cons != null)
					for(int i=0;i < lstName.length;i++)
						addNodeChild(cons, pathKey, lstName[i]);
			}
			catch(Exception ex) {}
	}

	/*******************************************/
	void addNodeChild(Constructor<? extends Example9_TreeNode> cons, String pathKey, String name)
	{
		try
		{
			Example9_TreeNode nodeChild = cons.newInstance(hkeyRoot, pathKey, name);

			if(nodeChild != null)
				add(nodeChild);
		}
		catch(Exception ex) {}
	}

	/*******************************************/
	void addNodeEmpty()
	{
		add(new DefaultMutableTreeNode("<empty>"));
	}
}
