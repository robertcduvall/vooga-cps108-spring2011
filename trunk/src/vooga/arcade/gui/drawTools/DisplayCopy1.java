package vooga.arcade.gui.drawTools;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import vooga.arcade.gui.helper.ResourceManager;


public class DisplayCopy1 {
	private ResourceManager guiResource = new ResourceManager("GUIResource");
	public static final Dimension DEFAULT_SIZE = new Dimension(640, 480);
	public static final Color DEFAULT_COLOR = Color.WHITE;
	public static final String DEFAULT_NAME = "Default";
	private String myFileName;
	private static IDrawable myDisplay = new Screen();

	protected BufferedImage myImage;
	protected Dimension mySize;
	protected Map<Point, Image> myImageMap = new HashMap<Point, Image>();
	protected Map<Point, Shape> myShapeMap = new HashMap<Point, Shape>();
	
	
	
	/**
	 * Create a default pixmap (300x300, completely black)
	 */
	public DisplayCopy1() {
		this(DEFAULT_SIZE.width, DEFAULT_SIZE.height, DEFAULT_COLOR, myDisplay);
	}

	/**
	 * Create a Jpanel viewer with given width and height and filled with given
	 * initial color
	 */
	public DisplayCopy1(int width, int height, Color color, IDrawable ID) {
		createImage(width, height, color);
	}

	/**
	 * Create a default pixmap with given size
	 */
	public DisplayCopy1(Dimension size) {
		this(size.width, size.height, DEFAULT_COLOR, myDisplay);
	}

	/**
	 * Create a default pixmap with given width and height
	 */
	public DisplayCopy1(int width, int height) {
		this(width, height, DEFAULT_COLOR, myDisplay);
	}

	public DisplayCopy1(Map<Point, Image> imageMap, Map<Point, Shape> shapeMap) {
		myImageMap = imageMap;
		myShapeMap = shapeMap;
		mySize = DEFAULT_SIZE;
		
	}

	/**
	 * @return true iff the given coordinate is within the bounds of this pixmap
	 */
	public boolean isInBounds(int x, int y) {
		return (0 <= x && x < mySize.width) && (0 <= y && y < mySize.height);
	}

	/**
	 * @return bounds of this pixmap
	 */
	public Dimension getSize() {
		return new Dimension(mySize);
	}

	/**
	 * Changes the size of this pixmap Adds default color to fill in blank space
	 * if necessary
	 */
	public void setSize(Dimension size) {
		setSize(size.width, size.height);
	}

	public Image getImage() {
		return new ImageIcon(guiResource.getString("DefaultSplashImage")).getImage();
	}

	/**
	 * Changes the size of this pixmap Adds default color to fill in blank space
	 * if necessary
	 */
	public void setSize(int width, int height) {
		if (width != mySize.width || height != mySize.height) {
			Dimension newSize = new Dimension(width, height);
			if (width > mySize.width || height > mySize.height) {
				myImage = copyImage(mySize, newSize, myImage);
			} else {
				// BUGBUG: scale image down instead?
				myImage = myImage.getSubimage(0, 0, width, height);
			}
			mySize = newSize;
		}
	}

	// was private
	public BufferedImage copyImage(Dimension from, Dimension to,
			BufferedImage original) {
		int[] data = new int[from.width * from.height];
		BufferedImage result = new BufferedImage(to.width, to.height,
				BufferedImage.TYPE_INT_RGB);
		original.getRGB(0, 0, from.width, from.height, data, 0, from.width);
		result.setRGB(0, 0, Math.min(from.width, to.width), Math.min(
				from.height, to.height), data, 0, from.width);
		return result;
	}

	/**
	 * @return name of this pixmap (without file system information)
	 */
	public String getName() {
		int index = myFileName.lastIndexOf(File.separator);
		if (index >= 0)
			return myFileName.substring(index + 1);
		else
			return myFileName;
	}

	/**
	 * Create a pixmap from the given local file
	 */
	public DisplayCopy1(String fileName) {
		if (fileName == null) {
			createImage(DEFAULT_SIZE.width, DEFAULT_SIZE.height, DEFAULT_COLOR);
		} else {
			try {
				read(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void createImage(int width, int height, Color color) {
		myFileName = DEFAULT_NAME;
		myImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		mySize = new Dimension(width, height);
	}

	public void paint(Graphics g) {
		Graphics2D pen = (Graphics2D) g.create();
		//myDisplay.painter(pen, this);
	}

	/**
	 * /* Updates this pixmap to reflect colors from the given local file Note,
	 * changes this pixmap's size if necessary
	 * 
	 * @throws IOException
	 */
	public void read(String fileName) throws IOException {
		myFileName = fileName;
		myImage = ImageIO.read(new File(myFileName));
		mySize = new Dimension(myImage.getWidth(), myImage.getHeight());
	}

	/**
	 * Returns this pixmap as as an Icon to be used in swing.
	 */
	public Icon toIcon() {
		return new ImageIcon(myImage);
	}

	/**
	 * Saves this pixmap to the given local file as a JPEG image
	 * 
	 * @throws IOException
	 */
	public void write(String fileName) throws IOException {
		ImageIO.write(myImage, "jpg", new File(fileName));
	}

	/**
	 * Create a pixmap as a copy of the given pixmap
	 */
	public DisplayCopy1(DisplayCopy1 other) {
		myFileName = other.myFileName;
		mySize = other.getSize();
		myImage = copyImage(mySize, mySize, other.myImage);
	}

	/**
	 * @return color of the pixel at the given coordinate in this pixmap
	 */
	public Color getColor(int x, int y) {
		if (isInBounds(x, y))
			return new Color(myImage.getRGB(x, y));
		else
			return DEFAULT_COLOR;
	}

	/**
	 * Changes the color of the pixel at the given coordinate in this pixmap
	 */
	public void setColor(int x, int y, Color value) {
		if (isInBounds(x, y)) {
			myImage.setRGB(x, y, value.getRGB());
		}
	}
	
	public Point alterCoordinates(Point position) {
		int alteredWidth = position.x + DEFAULT_SIZE.width / 2;
		alteredWidth = handleBounds(alteredWidth, DEFAULT_SIZE.width);
		int alteredHeight = position.y + DEFAULT_SIZE.height / 2;
		alteredHeight = handleBounds(alteredHeight, DEFAULT_SIZE.height);
		System.out.println(alteredWidth + " width");
		return new Point(alteredWidth, alteredHeight);
	}
	public Point alterCoordinates(Point2D position){
		Point pt = new Point((int)position.getX(), (int)position.getY());
		return alterCoordinates(pt);
	}

	public int handleBounds(int location, int dimension) {
		System.out.println("predim" + dimension + "   loc" + location);
		while (location > (dimension - 50)) {
			System.out.println(location);
			location = location - dimension;
			if (location < 0) {
				location = location * -1;
			}
		}
		while (location < 0) {
			location = location + dimension;
		}
		System.out.println(location + "loc");
		return location;
	}
	
}