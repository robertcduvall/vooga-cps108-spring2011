package vooga.arcade.gui.helper;

import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Structure representing that raw data that is passed from the Model to the
 * View. It should NOT have any behavior because this is raw data - giving it
 * innate drawing capabilities would be assuming something about the view, which
 * we don't want to do. The View should decide what it wants to do with this
 * data, however it decides to process this.
 * 
 * @author Ethan Yong-Hui Goh
 * 
 */
public final class DrawableData
{
	private List<AffineImage> images;
	private List<AffineShape> shapes;

	public DrawableData()
	{
		this(new ArrayList<AffineImage>(), new ArrayList<AffineShape>());
	}

	public DrawableData(List<AffineImage> i, List<AffineShape> s)
	{
		images = i;
		shapes = s;
	}

	public void addImage(AffineImage i)
	{
		images.add(i);
	}

	public void addShape(AffineShape s)
	{
		shapes.add(s);
	}
	
	public List<AffineImage> getImages()
	{
		return images;
	}
	
	public List<AffineShape> getShapes()
	{
		return shapes;
	}
}
