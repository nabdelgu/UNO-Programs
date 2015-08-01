/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 9                                                           ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example9;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example9_Tree extends JTree
	implements TreeExpansionListener
{
	int hkeyRoot = 0;
	Example9_ActionMenu actionMenu = null;


	/*******************************************/
	public Example9_Tree()
	{
		this(Example9_RegistryManagement.HKEY_CURRENT_USER, "HKEY_CURRENT_USER");
	}

	/*******************************************/
	public Example9_Tree(int hkeyRoot, String title)
	{
		super(new Example9_TreeNodeKey(hkeyRoot,"",title));

		this.hkeyRoot   = hkeyRoot;
		this.actionMenu = new Example9_ActionMenu(hkeyRoot, this);

		setBorder(new EmptyBorder(1,1,0,0));
		setComponentPopupMenu(new Example9_PopupMenu(actionMenu));

		Examples_UtilsGUI.initComponent(new JComponent[] { this }
			,new Font("Arial Unicode MS", 0, 12));

		addTreeExpansionListener(this);
		addMouseListener(new MouseAdapter()
			{
				/*******************************************/
				public void mousePressed(MouseEvent evt)
				{
					setSelectionRow(getRowForLocation(evt.getX(), evt.getY()));
				}

				/*******************************************/
				public void mouseClicked(MouseEvent evt)
				{
					if(evt.getClickCount() >= 2 && actionMenu != null)
						actionMenu.menuEditValue();
				}
			});

		collapseRow(0);
		expandRow(0);
	}

	/*******************************************/
	public void treeExpanded(TreeExpansionEvent evt)
	{
		refreshNodeKey(getNodeEvent(evt));
	}

	/*******************************************/
	public void treeCollapsed(TreeExpansionEvent evt)
	{
	}

	/*******************************************/
	public boolean isSelectedTreeNodeUser()
	{
		Object o = getLastSelectedPathComponent();
		String name = (o==null ? null : o.toString());

		return(name != null && name.startsWith("JavaExe - "));
	}

	/*******************************************/
	public void refreshNode(TreeNode node)
	{
		if(node instanceof Example9_TreeNodeVal)
			refreshNodeVal((Example9_TreeNodeVal) node);

		else if(node instanceof Example9_TreeNodeKey)
			refreshNodeKey((Example9_TreeNodeKey) node);
	}

	/*******************************************/
	public void refreshNodeKey(Example9_TreeNodeKey nodeKey)
	{
		if(nodeKey != null)
		{
			nodeKey.addNodesFromReg();
			nodeChanged(nodeKey);
		}
	}

	/*******************************************/
	public void refreshNodeVal(Example9_TreeNodeVal nodeVal)
	{
		if(nodeVal != null)
		{
			nodeVal.refreshValue();
			nodeChanged(nodeVal);
		}
	}

	/*******************************************/
	public Example9_TreeNodeKey getSelectedTreeNodeKey()
	{
		return getTreeNodeKey(getLastSelectedPathComponent());
	}

	/*******************************************/
	public Example9_TreeNodeVal getSelectedTreeNodeVal()
	{
		return getTreeNodeVal(getLastSelectedPathComponent());
	}

	/*******************************************/
	Example9_TreeNodeKey getNodeEvent(TreeExpansionEvent evt)
	{
		TreePath tp = evt.getPath();
		Object obj  = (tp==null ? null : tp.getLastPathComponent());

		return getTreeNodeKey(obj);
	}

	/*******************************************/
	Example9_TreeNodeKey getTreeNodeKey(Object obj)
	{
		return((obj instanceof Example9_TreeNodeKey)
			? (Example9_TreeNodeKey) obj
			: null);
	}

	/*******************************************/
	Example9_TreeNodeVal getTreeNodeVal(Object obj)
	{
		return((obj instanceof Example9_TreeNodeVal)
			? (Example9_TreeNodeVal) obj
			: null);
	}

	/*******************************************/
	void nodeChanged(TreeNode node)
	{
		TreeModel mdl = getModel();

		if(mdl instanceof DefaultTreeModel)
			((DefaultTreeModel) mdl).nodeStructureChanged(node);
	}
}
