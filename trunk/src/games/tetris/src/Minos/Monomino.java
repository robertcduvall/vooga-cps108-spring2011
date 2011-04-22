package games.tetris.src.Minos;

import games.tetris.src.Helper.ImageProcessor;
import games.tetris.src.Helper.ResourceManager;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;



import com.golden.gamedev.object.Sprite;

public class Monomino
{
	private ResourceManager resources = new ResourceManager("TetrisResource");

	private int xPos;
	private int yPos;
	private int cellSize;
	private Sprite sprite;

	public Monomino(Color c, int x, int y)
	{
		xPos = x;
		yPos = y;
		cellSize = resources.getInteger("MatrixCellSize");
		generateMinoImage(c, cellSize);
	}

	public Monomino(BufferedImage bi, int x, int y)
	{
		xPos = x;
		yPos = y;
		cellSize = resources.getInteger("MatrixCellSize");
		sprite = new Sprite(ImageProcessor.scaleImageWithSetX(bi, cellSize),
				xPos * cellSize
						+ resources.getInteger("MatrixSpawnCenterWidth"), -yPos
						* cellSize
						+ resources.getInteger("MatrixSpawnCenterHeight"));
		sprite.setLayer(1);
	}

	private void generateMinoImage(Color c, int cellSize)
	{
		BufferedImage image = new BufferedImage(cellSize, cellSize,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = image.createGraphics();
		// Draw on the buffered image
		g2d.setColor(new Color(150, 150, 150));
		g2d.setComposite(AlphaComposite.Src);
		g2d.fill(new Rectangle2D.Double(0, 0, cellSize, cellSize));
		g2d.setColor(c);
		g2d.fill(new Rectangle2D.Double(1, 1, cellSize - 2, cellSize - 2));
		g2d.dispose();

		sprite = new Sprite(image, xPos * cellSize
				+ resources.getInteger("MatrixSpawnCenterWidth"), -yPos
				* cellSize + resources.getInteger("MatrixSpawnCenterHeight"));
		sprite.setLayer(1);
	}

	public void moveX(int dx)
	{
		xPos += dx;
		sprite.setX(xPos * cellSize
				+ resources.getInteger("MatrixSpawnCenterWidth"));
	}

	public void moveY(int dy)
	{
		yPos += dy;
		sprite.setY(-yPos * cellSize
				+ resources.getInteger("MatrixSpawnCenterHeight"));
	}

	public int getX()
	{
		return xPos;
	}

	public int getY()
	{
		return yPos;
	}

	public Sprite getSprite()
	{
		return sprite;
	}

	public String toString()
	{
		return xPos + " " + yPos;
	}
}
