package vooga.arcade.controller;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;

// import vooga.arcade.gui.helper.DrawableData;


/**
 * Interface for the Controller in the MVC pattern.
 * 
 * @author Ethan Yong-Hui Goh, Andrea Scripa
 */
public interface IController
{
	public void displayError(String s);
	
	public void queryModel(String s);
	

    public void sortInModel (String s)
        throws SecurityException,
            NoSuchMethodException,
            ClassNotFoundException,
            IllegalArgumentException, IllegalAccessException, InvocationTargetException;

    public void login ();
}
