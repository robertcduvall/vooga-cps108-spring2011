package vooga.arcade.parser.gameObject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jdom.Element;

/**
 * 
 * General object used to contain information that can be displayed in the
 * arcade.
 * 
 * @author Yong-Hui Goh
 * @author KevinWang
 * 
 */
public abstract class ArcadeObject
{
	protected BufferedImage image;
	protected Element root;
	protected String path;

	public ArcadeObject(Element root, String path)
	{
		this.root = root;
		this.path = path;
	}

	/**
	 * return the contained image
	 * 
	 * @return the image
	 */
	public BufferedImage getImage()
	{
		if (image != null)
			return image;
		// System.out.println(root.getChildText("image"));
		try
		{
			image = ImageIO.read(new File(root.getChildText("image")));
		}
		catch (IOException e)
		{
			e.printStackTrace();
			try
			{
				image = ImageIO.read(new File("src/games/tetris/kea.jpg"));
			}
			catch (IOException e1)
			{

			}
		}
		return image;
	}

	/**
	 * return name of the object
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return root.getChildText("name");
	}
}
