package vooga.collisions.shapes;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.util.ArrayList;
import java.util.List;

import vooga.collisions.shapes.regularShapes.Circle;
import vooga.collisions.shapes.regularShapes.Polygon;
import vooga.collisions.shapes.regularShapes.RegularPolygon;

public class ShapeFactory 
{
	public static Polygon makePolygon(Vertex[] vertices)
	{
		return new Polygon(vertices);
	}
	
	public static RegularPolygon makeRegularPolygon(double x, double y, int sideNum, double sideLength){
        return new RegularPolygon(x,y, sideNum, sideLength);
	    
	}
	
	public static Circle makeCircle(double x, double y, double r){
	    return new Circle(x,y,r);
	}
	
	public static Circle makeCircle(Point2D c, double r){
        return new Circle(c,r);
    }


	private java.awt.Shape makeShape (BufferedImage image)
	{
		int h = image.getHeight();
		int w = image.getWidth();
		int[] pixels = new int[w * h];
		try
		{
			PixelGrabber grabber = new PixelGrabber(image, 0, 0, w, h, pixels, 0, w);
			grabber.grabPixels();
		}
		catch (InterruptedException e)
		{}

		// scan line algorithm to find edges at transparent boundaries:
			// stores the intersection points of the scan lines with edges
		// stored in a [y,x] manner since algorithm goes row by row
		List<List<Integer>> pts = new ArrayList<List<Integer>>(h);
		Point start = null;
		Point end = new Point();
		for (int y = 0; y < h; y++)
		{
			pts.add(new ArrayList<Integer>());
			int lastAlpha = 0;
			for (int x = 0; x < w; x++)
			{
				int alpha = (pixels[y * w + x] >> 24) & 0xff;
				// at an edge?
						if ((alpha != 0 && lastAlpha == 0) ||
								(alpha == 0 && lastAlpha != 0))
						{
							pts.get(y).add(x);
							if (start == null)
							{
								start = new Point(x, y);
							}
							end.setLocation(x, y);
						}
						lastAlpha = alpha;
			}
			// sprite overlaps the right side
			if (lastAlpha != 0)
			{
				pts.get(y).add(w - 1);
				end.setLocation(w - 1, y);
			}
		}

		// now create convex trace around image
		GeneralPath total = new GeneralPath();
		total.moveTo(start.x, start.y);
		int count = 1;
		int lastX = start.x;
		for (int y = start.y + 1; y <= end.y; y++)
		{
			List<Integer> row = pts.get(y);
			if (! row.isEmpty())
			{
				lastX = row.get(0);
			}
			total.lineTo(lastX, y);
			count++;
		}
		total.lineTo(end.x, end.y);
		count++;
		lastX = end.x;
		for (int y = end.y - 1; y >= start.y; y--)
		{
			List<Integer> row = pts.get(y);
			if (! row.isEmpty())
			{
				lastX = row.get(row.size() - 1);
			}
			total.lineTo(lastX, y);
			count++;
		}
		total.closePath();
		count++;
		total.transform(AffineTransform.getScaleInstance(1.0 / w, 1.0 / h));
		System.out.println("Number of points = " + count);

		return total;
	}
}
