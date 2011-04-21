package vooga.arcade.parser;

import java.awt.Image;
import java.util.List;

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

	// private VoogaUser
	// private List<Friends>();

	public ArcadeUserObject(Image i, String n, List<ArcadeGameObject> fav)
	{
		super(i, n);
		favorites = fav;
	}
}
