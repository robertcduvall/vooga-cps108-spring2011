package vooga.stats.example02.util.commands;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import vooga.stats.example02.view.components.ErrorCatcher;

/**
 * A command for reading image
 * Actually it is implemented in GTGE...
 * @author Yin
 *
 */
public class ImageReader {
	
	private static String myLocation;
	private static BufferedImage myBufferedImage;
	
	public ImageReader()
	{
		myLocation = new String();
	}
	
	/**
	 * Read image
	 * @param location
	 * @return
	 */
	public static BufferedImage readImage(String location)
	{
		
		if (location != null)
            myLocation = location;

        try {
            BufferedInputStream is = new BufferedInputStream(
                    new FileInputStream(myLocation));
            myBufferedImage = ImageIO.read(is);
        } catch (IOException ex) {
            new ErrorCatcher(ex.getMessage());
        }

        return myBufferedImage;
	}
}
