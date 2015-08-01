/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 10                                                          ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example10;


import java.awt.*;
import javax.swing.*;

import com.devwizard.javaexe.examples.common.*;


/*****************************************************************************/
public class Example10_TextArea_Unicode extends JTextArea
{
	/*******************************************/
	public Example10_TextArea_Unicode(String txt, int rows, int cols)
	{
		super(txt, rows, cols);

		setEditable(false);

		Examples_UtilsGUI.initComponent(new JComponent[] { this }
			,new Font("Arial Unicode MS", 0, 16));
	}
}
