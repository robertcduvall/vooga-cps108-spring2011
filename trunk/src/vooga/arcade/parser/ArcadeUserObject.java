package vooga.arcade.parser;

import java.awt.Image;
import java.util.List;

import org.jdom.Element;

import vooga.arcade.parser.gameObject.ArcadeObject;


/**
 * Object containing an instance of a user and information associated with it.
 * This is used to transport game and its data around
 * 
 * @author Ethan Goh
 * 
 */
public class ArcadeUserObject extends ArcadeObject
{
	private List<ArcadeGameObject> favorites;

	public ArcadeUserObject(Element root, String path)
	{
		super(root, path);
	}
}
