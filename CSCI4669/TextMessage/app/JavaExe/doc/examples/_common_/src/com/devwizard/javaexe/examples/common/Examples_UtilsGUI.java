/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.common;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.text.*;


/*****************************************************************************/
public class Examples_UtilsGUI
{
	public static Font font_TNR_12 = new Font("Times New Roman", 0, 12);
	public static Font font_TNR_12bold = new Font("Times New Roman", Font.BOLD, 12);


	/*******************************************/
	public static void initComponent(JComponent comp)
	{
		initComponent(comp, font_TNR_12);
	}

	/*******************************************/
	public static void initComponent(JComponent comp, Font fnt)
	{
		if(comp != null)
			initComponent(new JComponent[] { comp }, fnt);
	}

	/*******************************************/
	public static void initComponent(JComponent[] lstLbl)
	{
		initComponent(lstLbl, null, null);
	}

	/*******************************************/
	public static void initComponent(JComponent[] lstLbl, Font fnt)
	{
		initComponent(lstLbl, null, null, fnt);
	}

	/*******************************************/
	public static void initComponent(AbstractButton[] lstBut)
	{
		initComponent(null, lstBut, null);
	}

	/*******************************************/
	public static void initComponent(AbstractButton[] lstBut, Font fnt)
	{
		initComponent(null, lstBut, null, fnt);
	}

	/*******************************************/
	public static void initComponent(AbstractButton[] lstBut, ActionListener act)
	{
		initComponent(null, lstBut, act);
	}

	/*******************************************/
	public static void initComponent(JComponent[] lstLbl, AbstractButton[] lstBut)
	{
		initComponent(lstLbl, lstBut, null);
	}

	/*******************************************/
	public static void initComponent(JComponent[] lstLbl, AbstractButton[] lstBut
		,ActionListener act)
	{
		initComponent(lstLbl, lstBut, act, font_TNR_12);
	}

	/*******************************************/
	public static void initComponent(JComponent[] lstLbl, AbstractButton[] lstBut
		,ActionListener act, Font fnt)
	{
		if(lstLbl != null)
			for(int i=0;i < lstLbl.length;i++)
				if(lstLbl[i] != null)
					lstLbl[i].setFont(fnt);

		if(lstBut != null)
			for(int i=0;i < lstBut.length;i++)
				if(lstBut[i] != null)
				{
					lstBut[i].setFont(fnt);
					if(act != null) lstBut[i].addActionListener(act);
				}
	}

	/*******************************************/
	public static JTextField putTextfield(Examples_Panel p, String lbl)
	{
		return putTextfield(p, lbl, 10);
	}

	/*******************************************/
	public static JTextField putTextfield(Examples_Panel p, String lbl, int len)
	{
		return putTextfield(p, lbl, len, true);
	}

	/*******************************************/
	public static JTextField putTextfield(Examples_Panel p, String lbl, int len
		,boolean isLast)
	{
		JTextField txtField = new JTextField(len);

		putComponent(p, lbl, txtField, isLast);

		return txtField;
	}

	/*******************************************/
	public static JLabel putComponent(Examples_Panel p, String lbl, JComponent comp)
	{
		return putComponent(p, lbl, comp, true);
	}

	/*******************************************/
	public static JLabel putComponent(Examples_Panel p, String lbl, JComponent comp
		,boolean isLast)
	{
		JLabel objLbl = new JLabel(lbl, JLabel.RIGHT);

		p.putComponent(objLbl);

		if(isLast)
			p.putLastComponent(comp, GridBagConstraints.BOTH);
		else
			p.putComponent(comp);

		return objLbl;
	}

	/*******************************************/
	public static JCheckBox putCheckbox(Examples_Panel p, String txt)
	{
		return putCheckbox(p, txt, true);
	}

	/*******************************************/
	public static JCheckBox putCheckbox(Examples_Panel p, String txt, boolean isLast)
	{
		JCheckBox chk = new JCheckBox(txt);

		if(isLast)
			p.putLastComponent(chk);
		else
			p.putComponent(chk);

		return chk;
	}

	/*******************************************/
	public static Component getComponentLeftPaned(Component comp)
	{
		if(comp != null
		&& !(comp instanceof JScrollPane)
		&& !(comp instanceof JTabbedPane)
		&& !(comp instanceof JPanel))
		{
			JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));

			p.add(comp);
			comp = p;
		}

		return comp;
	}

	/*******************************************/
	public static Image getImage(String nameFile)
	{
		ImageIcon icn = getIcon(nameFile);

		return(icn==null ? null : icn.getImage());
	}

	/*******************************************/
	public static ImageIcon getIcon(String nameFile)
	{
		URL u = Examples_Config.getRsrcURL("/Images/"+nameFile);

		return(u==null ? null : new ImageIcon(u));
	}

	/*******************************************/
	public static boolean showConfirmDialog(String title, Object msg)
	{
		int ret = JOptionPane.showConfirmDialog(null, msg, title
			,JOptionPane.YES_NO_OPTION);

		return(ret==JOptionPane.YES_OPTION);
	}

	/*******************************************/
	public static void showInfoDialog(String title, Object msg)
	{
		showInfoDialog(null, title, msg);
	}

	/*******************************************/
	public static void showInfoDialog(Component parent, String title, Object msg)
	{
		showMessageDialog(parent, title, msg, JOptionPane.INFORMATION_MESSAGE);
	}

	/*******************************************/
	public static void showMessageDialog(String title, Object msg)
	{
		showMessageDialog(title, msg, JOptionPane.ERROR_MESSAGE);
	}

	/*******************************************/
	public static void showMessageDialog(String title, Object msg, int type)
	{
		showMessageDialog(null, title, msg, type);
	}

	/*******************************************/
	public static void showMessageDialog(Component parent, String title, Object msg, int type)
	{
		JOptionPane.showMessageDialog(parent, msg, title, type);
	}

	/*******************************************/
	public static void setLookSystem()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception ex) {}
	}

	/*******************************************/
	public static void setCursorWait(Component comp)
	{
		Window wind = SwingUtilities.getWindowAncestor(comp);

		if(wind != null)
			wind.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	}

	/*******************************************/
	public static void setCursorDefault(Component comp)
	{
		Window wind = SwingUtilities.getWindowAncestor(comp);

		if(wind != null)
			wind.setCursor(Cursor.getDefaultCursor());
	}

	/*******************************************/
	public static void windowPack(Component comp)
	{
		comp.revalidate();
		final Window wind = SwingUtilities.getWindowAncestor(comp);

		if(wind != null)
			SwingUtilities.invokeLater(new Runnable()
				{
					/*******************************************/
					public void run()
					{
						wind.pack();
					}
				});
	}

	/*******************************************/
	public static void setRenderingQuality(Graphics2D g)
	{
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	}


	/*---------------------------------------------------*/

	static
	{
		setLookSystem();
	}
}
