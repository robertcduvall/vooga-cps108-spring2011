package vooga.arcade.parser.gameObject;

import java.awt.Image;

public abstract class ArcadeObject
{
	private Image image;
	private String name;

	public ArcadeObject(Image i, String n)
	{
		image = i;
		name = n;
	}

	/**
	 * @return the drawableData
	 */
	public Image getImage()
	{
		return image;
	}

	public String getName()
	{
		return name;
	}
}
