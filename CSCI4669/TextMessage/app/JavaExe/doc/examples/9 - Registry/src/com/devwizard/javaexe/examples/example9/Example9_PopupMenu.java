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


/*****************************************************************************/
public class Example9_PopupMenu extends JPopupMenu
	implements ActionListener
{
	Example9_I_CallbackMenu callback = null;
	JMenuItem[] lstMenu = new JMenuItem[NB_LBL];


	/*******************************************/
	public Example9_PopupMenu(Example9_I_CallbackMenu callback)
	{
		this.callback = callback;

		addItem(NDX_NEWKEY);
		addItem(NDX_NEWVAL);

		addSeparator();
		addItem(NDX_EDITVAL);

		addSeparator();
		addItem(NDX_DELETE);
	}

	/*******************************************/
	public void show(Component invoker, int x, int y)
	{
		boolean isK = (callback != null && callback.isSelectedKey());
		boolean isV = (callback != null && callback.isSelectedValue());
		boolean isU = (callback != null && callback.isSelectedUser());

		if(lstMenu[NDX_NEWKEY]  != null) lstMenu[NDX_NEWKEY].setEnabled(isK);
		if(lstMenu[NDX_NEWVAL]  != null) lstMenu[NDX_NEWVAL].setEnabled(isK);
		if(lstMenu[NDX_EDITVAL] != null) lstMenu[NDX_EDITVAL].setEnabled(isV && isU);
		if(lstMenu[NDX_DELETE]  != null) lstMenu[NDX_DELETE].setEnabled(isU);

		super.show(invoker, x, y);
	}

	/*******************************************/
	public void actionPerformed(ActionEvent evt)
	{
		Object o = evt.getSource();

		if(o==lstMenu[NDX_NEWKEY]) callback.menuNewKey();
		else if(o==lstMenu[NDX_NEWVAL]) callback.menuNewValue();
		else if(o==lstMenu[NDX_EDITVAL]) callback.menuEditValue();
		else if(o==lstMenu[NDX_DELETE]) callback.menuDelete();
	}

	/*******************************************/
	void addItem(int ndx)
	{
		if(lstMenu==null || ndx < 0 || ndx >= lstMenu.length)
			return;

		JMenuItem mi = new JMenuItem(LBL_MENU[ndx]);
		lstMenu[ndx] = mi;

		mi.addActionListener(this);
		add(mi);
	}


	/*---------------------------------------------------*/

	static final String[] LBL_MENU =
		{
			"New Key",
			"New Value [REG_SZ]",
			"Edit Value",
			"Delete"
		};

	static final int NB_LBL = LBL_MENU.length;

	static final int NDX_NEWKEY  = 0;
	static final int NDX_NEWVAL  = 1;
	static final int NDX_EDITVAL = 2;
	static final int NDX_DELETE  = 3;
}
