package vooga.arcade.gui.helper;

import java.awt.Image;
import java.awt.geom.AffineTransform;

/**
 * Structure packaging an image with an affine transform. Yes, the member variables are public, because this declaration should really be: 
 * 
 * struct AffineImage
 * {
 * 		Image img;
 * 		AffineTransform transform;
 * };
 * 
 * But, this isn't C/C++/C#, which are >>>>>>. Especially C#. Ugh.
 * 
 * @author Ethan Yong-Hui Goh
 *
 */
public final class AffineImage
{
	private Image img;
	private AffineTransform transform;
	
	public AffineImage(Image i, AffineTransform at)
	{
		img = i;
		transform = at;
	}
	
	public Image getImage()
	{
		return img;
	}
	
	public AffineTransform getTransform()
	{
		return transform;
	}
}
