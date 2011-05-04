package games.nemo;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import games.nemo.view.components.ErrorCatcher;

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
	 * @throws IOException 
	 */
	public static BufferedImage readImage(String location)
	{
		
		if (location != null)
			myLocation = location;            

        try {
        	/*
            BufferedInputStream is = new BufferedInputStream(
                    new FileInputStream(myLocation));
            myBufferedImage = ImageIO.read(is);
            */
            
        	System.out.println(myLocation);
            myBufferedImage = ImageIO.read(new File(myLocation));
        } catch (IOException ex) {
            new ErrorCatcher(ex.getMessage());
        }

        return myBufferedImage;
	}
}
