/*****************************************************************************/
/***  (c) 2002-2013, DevWizard (DevWizard@free.fr)                         ***/
/***                                                                       ***/
/***                                                                       ***/
/***   Example 9                                                           ***/
/***                                                                       ***/
/*****************************************************************************/

package com.devwizard.javaexe.examples.example9;


/*****************************************************************************/
public interface Example9_I_CallbackMenu
{
	public boolean isSelectedKey();
	public boolean isSelectedValue();
	public boolean isSelectedUser();

	public void menuNewKey();
	public void menuNewValue();
	public void menuEditValue();
	public void menuDelete();
}
