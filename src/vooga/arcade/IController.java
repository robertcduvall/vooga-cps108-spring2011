package vooga.arcade;

import java.awt.Component;

import vooga.arcade.gui.helper.DrawableData;


/**
 * Interface for the Controller in the MVC pattern.
 * 
 * @author Ethan Yong-Hui Goh
 */
public interface IController
{
	public void promptModel(String str);

	public void updateView(DrawableData p);

	public void displayError(String s);

	public void addNewAvatar(String imageUML);
}
