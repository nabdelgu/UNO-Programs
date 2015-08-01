/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 19                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example19;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example19_DialogConfig extends Examples_Dialog
{
	protected JTextField txtTick = null;
	protected JCheckBox chkTrans = null;
	protected JComboBox<Integer> cbxSize = new JComboBox<Integer>(new Integer[] { 1,2,3 });


	/*******************************************/
	public Example19_DialogConfig()
	{
		super(null, "ScreenSaver Config...");

		Examples_Panel p = new Examples_Panel(null, 5, 5, 5, 5);
		JLabel lbl = new JLabel(" (in milliseconds)");

		txtTick  = Examples_UtilsGUI.putTextfield(p, "Tick Count", 5, false);
		p.putLastComponent(lbl, GridBagConstraints.BOTH);
		Examples_UtilsGUI.putComponent(p, "Text Size", cbxSize, false);
		p.putLastComponent(Box.createHorizontalBox(), GridBagConstraints.BOTH);
		chkTrans = Examples_UtilsGUI.putCheckbox(p, "Is Transparent Background ?");

		Example19_Config.init();
		txtTick.setText(""+Example19_Config.cnfgTickCount);
		chkTrans.setSelected(Example19_Config.cnfgTrans);
		cbxSize.setSelectedItem(Example19_Config.cnfgSize);

		Examples_UtilsGUI.initComponent(new JComponent[]
			{
				txtTick, chkTrans, cbxSize, lbl
			});

		setComponent(p, "Center");

		pack();
		setLocation(300, 300);
		setVisible(true);
	}

	/*******************************************/
	public void actionPerformed(ActionEvent evt)
	{
		Object obj = evt.getSource();

		if(obj==butOK)
			Example19_Config.save(txtTick.getText(), chkTrans.isSelected()
				,(Integer) cbxSize.getSelectedItem());

		super.actionPerformed(evt);
	}
}
