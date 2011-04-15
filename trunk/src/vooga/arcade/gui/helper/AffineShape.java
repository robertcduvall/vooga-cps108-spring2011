package vooga.arcade.gui.helper;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

/**
 * Structure packaging a Shape with an affine transform. Yes, the member
 * variables are public, because this declaration should really be:
 * 
 * struct AffineShape { Shape img; AffineTransform transform; Color color; };
 * 
 * But, this isn't C/C++/C#, which are >>>>>>. Especially C#. Ugh.
 * 
 * @author Ethan Yong-Hui Goh
 * 
 */
public final class AffineShape
{
	private Shape shape;
	private AffineTransform transform;
	private Color color;

	public AffineShape(Shape s, AffineTransform at)
	{
		this(s, at, Color.black);
	}

	public AffineShape(Shape s, AffineTransform at, Color c)
	{
		shape = s;
		transform = at;
		color = c;
	}
	
	public Shape getShape()
	{
		return shape;
	}
	
	public AffineTransform getTransform()
	{
		return (AffineTransform) transform.clone();
	}
	
	public Color getColor()
	{
		return color;
	}
}
