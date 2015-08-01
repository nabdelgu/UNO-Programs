/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.common;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


/*****************************************************************************/
public class Examples_Panel extends JPanel
{
	GridBagLayout gridBag = new GridBagLayout();
	GridBagConstraints gridConst = new GridBagConstraints();

	int defaultFill;


	/*******************************************/
	public Examples_Panel()
	{
		this(null, null);
	}

	/*******************************************/
	public Examples_Panel(String title)
	{
		this(null, title);
	}

	/*******************************************/
	public Examples_Panel(LayoutManager layout, String title)
	{
		this(layout, title, 0, 0, 0, 0);
	}

	/*******************************************/
	public Examples_Panel(String title, int top, int left, int bottom, int right)
	{
		this(null, title, top, left, bottom, right);
	}

	/*******************************************/
	public Examples_Panel(LayoutManager layout, String title, int top, int left, int bottom, int right)
	{
		setLayout(layout==null ? gridBag : layout);

		AbstractBorder b1 = null;
		AbstractBorder b2 = null;

		if(top != 0 || left != 0 || bottom != 0 || right != 0)
			b1 = new EmptyBorder(top,left,bottom,right);

		if(title != null)
			b2 = new TitledBorder(title);

		if(b1 != null && b2 != null)
			setBorder(new CompoundBorder(b1,b2));
		else if(b1 != null)
			setBorder(b1);
		else if(b2 != null)
			setBorder(b2);

		init(GridBagConstraints.BOTH);
	}

	/*******************************************/
	public void init(int fill)
	{
		defaultFill = fill;

		gridConst.fill = fill;
		gridConst.weightx = 0.0;
		gridConst.weighty = 0.0;
		gridConst.insets = new Insets(1,1,1,1);
		gridConst.ipadx = 0;
		gridConst.ipady = 0;
		gridConst.anchor = GridBagConstraints.NORTHWEST;
	}

	/*******************************************/
	public void putLastComponent(JComponent obj)
	{
		putLastComponent(obj, GridBagConstraints.BOTH);
	}

	/*******************************************/
	public void putLastComponent(JComponent obj, int fill)
	{
		putLastComponent(obj, fill, 0, 0);
	}

	/*******************************************/
	public void putLastComponent(JComponent obj, int insetTop, int insetLeft)
	{
		putLastComponent(obj, GridBagConstraints.BOTH, insetTop, insetLeft);
	}

	/*******************************************/
	public void putLastComponent(JComponent obj, int fill, int insetTop, int insetLeft)
	{
		putLastComponent(obj, fill, insetTop, insetLeft, 0, 0);
	}

	/*******************************************/
	public void putLastComponent(JComponent obj, int insetTop, int insetLeft
		,int insetBottom, int insetRight)
	{
		putLastComponent(obj, GridBagConstraints.BOTH, insetTop, insetLeft, insetBottom, insetRight);
	}

	/*******************************************/
	public void putLastComponent(JComponent obj, int fill, int insetTop, int insetLeft
		,int insetBottom, int insetRight)
	{
		putComponent(obj, GridBagConstraints.REMAINDER, 1, fill, insetTop, insetLeft
			,insetBottom, insetRight);
	}

	/*******************************************/
	public void putComponent(Component comp)
	{
		putComponent(comp, 1, 1);
	}

	/*******************************************/
	public void putComponent(Component comp, int gridwidth)
	{
		putComponent(comp, gridwidth, 1);
	}

	/*******************************************/
	public void putComponent(Component comp, int gridwidth, int gridheight
		,int fill, int insetLeft, int insetRight)
	{
		putComponent(comp, gridwidth, gridheight, fill, 1, insetLeft, 1, insetRight);
	}

	/*******************************************/
	public void putComponent(Component comp, int gridwidth, int gridheight
		,int fill, int insetTop, int insetLeft, int insetBottom, int insetRight)
	{
		gridConst.insets = new Insets(insetTop, insetLeft, insetBottom, insetRight);

		putComponent(comp, gridwidth, gridheight, fill);

		gridConst.insets = new Insets(1,1,1,1);
	}

	/*******************************************/
	public void putComponent(Component comp, int gridwidth, int gridheight, int fill)
	{
		gridConst.fill = fill;
		putComponent(comp, gridwidth, gridheight);

		gridConst.fill = defaultFill;
	}

	/*******************************************/
	public void putComponent(Component comp, int gridwidth, int gridheight)
	{
		gridConst.gridwidth = gridwidth;
		gridConst.gridheight = gridheight;
		gridConst.weightx = 0.0;
		gridConst.weighty = 0.0;

		addComponent(comp);
	}

	/*******************************************/
	public void addComponent(Component comp)
	{
		gridBag.setConstraints(comp,gridConst);
		add(comp);

		gridConst.gridx = GridBagConstraints.RELATIVE;
	}
}
