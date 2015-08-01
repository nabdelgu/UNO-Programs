/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 9                                                           ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example9;


/*****************************************************************************/
public class Example9_ActionMenu
	implements Example9_I_CallbackMenu
{
	int hkeyRoot = 0;
	Example9_Tree tree = null;


	/*******************************************/
	public Example9_ActionMenu(int hkeyRoot, Example9_Tree tree)
	{
		this.hkeyRoot = hkeyRoot;
		this.tree = tree;
	}

	/*******************************************/
	public boolean isSelectedKey()
	{
		return(tree != null && tree.getSelectedTreeNodeKey() != null);
	}

	/*******************************************/
	public boolean isSelectedValue()
	{
		return(tree != null && tree.getSelectedTreeNodeVal() != null);
	}

	/*******************************************/
	public boolean isSelectedUser()
	{
		return(tree != null && tree.isSelectedTreeNodeUser());
	}

	/*******************************************/
	public void menuNewKey()
	{
		Example9_TreeNodeKey nodeKey = (tree != null ? tree.getSelectedTreeNodeKey() : null);

		if(Example9_ActionForm.newKey(hkeyRoot,nodeKey))
			tree.refreshNodeKey(nodeKey);
	}

	/*******************************************/
	public void menuNewValue()
	{
		Example9_TreeNodeKey nodeKey = (tree != null ? tree.getSelectedTreeNodeKey() : null);

		if(Example9_ActionForm.newValue(hkeyRoot,nodeKey))
			tree.refreshNodeKey(nodeKey);
	}

	/*******************************************/
	public void menuEditValue()
	{
		Example9_TreeNodeVal nodeVal = (tree != null ? tree.getSelectedTreeNodeVal() : null);

		if(isSelectedUser() && Example9_ActionForm.editValue(hkeyRoot,nodeVal))
			tree.refreshNodeVal(nodeVal);
	}

	/*******************************************/
	public void menuDelete()
	{
		Object obj = (tree != null ? tree.getLastSelectedPathComponent() : null);
		Example9_TreeNode node = ((obj instanceof Example9_TreeNode) ? (Example9_TreeNode) obj : null);

		if(Example9_ActionForm.delete(hkeyRoot,node))
			tree.refreshNode(node.getParent());
	}
}
