/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 9                                                           ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example9;


import javax.swing.tree.*;


/*****************************************************************************/
public abstract class Example9_TreeNode extends DefaultMutableTreeNode
{
	protected int hkeyRoot = 0;
	protected String pathKey = "";


	/*******************************************/
	public Example9_TreeNode(Integer hkeyRoot, String pathKey, String name)
	{
		super(name);

		this.hkeyRoot = (hkeyRoot==null ? 0 : hkeyRoot.intValue());
		this.pathKey  = pathKey;
	}

	/*******************************************/
	public String getName()
	{
		return null;
	}

	/*******************************************/
	public abstract String getPathKey();

	/*******************************************/
	String getPathKey(int delta)
	{
		TreeNode[] lst = getPath();

		if(lst==null)
			return null;

		StringBuffer str = new StringBuffer();
		int nb = lst.length - delta;

		for(int i=1;i < nb;i++)
		{
			if(i > 1) str.append("\\");
			str.append(lst[i].toString());
		}

		return str.toString();
	}
}
