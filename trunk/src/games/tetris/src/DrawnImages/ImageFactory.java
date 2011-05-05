package games.tetris.src.DrawnImages;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class ImageFactory
{
	public static BufferedImage drawMatrixBorder(int width,int height)
	{
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = image.createGraphics();
		// Draw on the buffered image
		g2d.setComposite(AlphaComposite.Src);
		g2d.setColor(new Color(50,50,50));
		g2d.fill(new RoundRectangle2D.Double(0, 0, width, height,20,20));
		g2d.setColor(new Color(200,200,200));
		g2d.fill(new RoundRectangle2D.Double(10, 10, width-20, height-20,20,20));
		g2d.setColor(new Color(255,255,255));
		g2d.fill(new RoundRectangle2D.Double(25, 25, width-50, height-50,20,20));
		g2d.setColor(new Color(200,200,255));
		g2d.fill(new RoundRectangle2D.Double(30, 30, width-60, height-60,20,20));
		g2d.setColor(new Color(0,0,0,0));
		g2d.fill(new RoundRectangle2D.Double(35, 35, width-70, height-70,20,20));
		
		g2d.dispose();
		return image;
	}
}
