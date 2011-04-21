package vooga.user.voogauser;


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
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import vooga.user.main.ResourceManager;

/**@author Duvall
 * @author Conrad Haynes
*/

public class Display {
	private ResourceManager guiResource = new ResourceManager("vooga.user.resources.GUIResource");
	public static final Dimension DEFAULT_SIZE = new Dimension(640, 480);
	public static final Color DEFAULT_COLOR = Color.WHITE;
	public static final String DEFAULT_NAME = "Default";
	
	protected Image myImage;
	protected Dimension mySize;
	protected String myFileName;
	

	//Create a default display (640x480, completely white)
	public Display() {
		createImage(DEFAULT_SIZE.width, DEFAULT_SIZE.height, DEFAULT_COLOR);
	}
	
	//Create a default display with given size
	public Display(Dimension size) {
		createImage(size.width, size.height, DEFAULT_COLOR);
	}
	
	//returns the bounds of a display
	public Dimension getSize() {
		return new Dimension(mySize);
	}
	
	public Image getBackgroundImage() {
		return new ImageIcon(guiResource.getString("DefaultSplashImage")).getImage();
	}

	//Create a pixmap from the given local file
	public Display(String fileName) {
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
		pen.drawImage(getBackgroundImage(), 0, 0,mySize.width,mySize.height,null);

	}

	//Updates this display to reflect colors from the given local file 
	public void read(String fileName) throws IOException {
		myFileName = fileName;
		myImage = ImageIO.read(new File(myFileName));
		mySize = new Dimension(myImage.getWidth(null), myImage.getHeight(null));
	}

}