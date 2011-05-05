package games.patterson_game.refactoredVooga.resources.images;

import games.patterson_game.refactoredVooga.resources.Direction;
import games.patterson_game.refactoredVooga.resources.ResourceException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.util.ImageUtil;

/**
 * Loads images from sources specified in an XML resource file.
 * 
 * <br /> <br />
 * 
 * The program need not know the actual name and location of the image file.
 * After the image file is parsed, the image files are indexed by a name
 * supplied in the XML resource file. When retrieving an image, you can
 * also request:
 * <ul>
 * <li>a Direction: the XML file can specify that an alternate image
 * can be used for rotation, or the image will be rotated.</li>
 * <li>An index: this can be used for many purposes, from indicating sprite
 * status to choosing an image from a tile set</li>
 * <li>A time value: this will be used for choosing one of several images in
 * an animation.
 * </ul>
 * As an alternative, the getAnimation() methods can be used, which 
 * return an array of images to time the animation elsewhere.
 * 
 * @author Misha
 *
 */
public class ImageLoader
{
    SortedMap<ImageKey, AnimatedImage> images; // can be accessed by ImageParser

    /**
     * Initializes the ImageLoader from a resource file. 
     * 
     * @param resource The file to load the image specs from
     */
    @Deprecated
    public ImageLoader(File resource, BaseLoader bsLoader)
    {
        images = new TreeMap<ImageKey, AnimatedImage>();
        
        try 
        {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.parse(resource);
            ImageParser parser = new ImageParser(this, bsLoader);
            
            parser.parse(doc);
        }
        catch (IOException e)
        {
            throw ResourceException.resourceReadingException(resource.getName());
        } catch (SAXException e) {
            throw ResourceException.resourceReadingException(resource.getName());
		} catch (ParserConfigurationException e) {
            throw ResourceException.resourceReadingException(resource.getName());
		}
    }
    
    /**
     * Construct image loader.
     * @param bsLoader the base loader (gotten from a VoogaGame).
     * @param rootElement the root tag (gotten from a root XML document.)
     */
    public ImageLoader(BaseLoader bsLoader, Element rootElement) {
    	images = new TreeMap<ImageKey, AnimatedImage>();
    	
    	ImageParser parser = new ImageParser(this, bsLoader);
    	parser.parseElement(rootElement);
    }
    
    /** See getImage(name, imageIndex, direction, time). */
    public BufferedImage getImage(String name) {return getImage(name, 0, Direction.NORTH, 0);}
    /** See getImage(name, imageIndex, direction, time). */
    public BufferedImage getImage(String name, int imageIndex) {return getImage(name, imageIndex, Direction.NORTH, 0);}
    /** See getImage(name, imageIndex, direction, time). */
    public BufferedImage getImage(String name, Direction direction) {return getImage(name, 0, direction, 0);}
    /** See getImage(name, imageIndex, direction, time). */
    public BufferedImage getImage(String name, int imageIndex, Direction direction) {return getImage(name, imageIndex, direction, 0);}
    /** See getImage(name, imageIndex, direction, time). */
    public BufferedImage getImage(String name, int imageIndex, long time) {return getImage(name, imageIndex, Direction.NORTH, time);}
    /** See getImage(name, imageIndex, direction, time). */
    public BufferedImage getImage(String name, Direction direction, long time) {return getImage(name, 0, direction, time);}

    /**
     * Returns an image specified in the resource file.
     * 
     * @param name The name this image is identified by in the resource file.
     * @param imageIndex Chooses which of several images corresponding to this name
     * to choose (defaults to the first (or only) image supplied)
     * @param direction Chooses which direction to use for the image (can either rotate
     * or use separate images). Defaults to the default direction, assumed Direction.NORTH.
     * @param time Chooses which frame to use for animated images. Defaults to 
     * first (or only) frame.
     * @return The corresponding image; null if an image was not found.
     */
    public BufferedImage getImage(String name, int imageIndex, Direction direction, long time)
    {
        ImageKey key = new ImageKey(name, imageIndex, direction);
        
        if (images.containsKey(key))
            return images.get(key).getFrame(time);
        
        key = new ImageKey(name, imageIndex, Direction.NORTH);
        
        if (images.containsKey(key))
        {
            BufferedImage frame = images.get(key).getFrame(time);
            
            int angle = Direction.NORTH.getAngle() - direction.getAngle();
            
            return ImageUtil.rotate(frame, angle);
        }
        
        throw ResourceException.notFoundException();
    }

    /** See getAnimation(name, imageIndex, direction) */    
    public BufferedImage[] getAnimation(String name) {return getAnimation(name, 0, Direction.NORTH);}
    /** See getAnimation(name, imageIndex, direction) */    
    public BufferedImage[] getAnimation(String name, int imageIndex) {return getAnimation(name, imageIndex, Direction.NORTH);}
    /** See getAnimation(name, imageIndex, direction) */
    public BufferedImage[] getAnimation(String name, Direction direction) {return getAnimation(name, 0, direction);}
    
    /**
     * Returns an animation specified in the resource file.
     * 
     * @param name The name this animation is identified by in the resource file.
     * @param imageIndex Chooses which of several animations corresponding to this name
     * to choose (defaults to the first (or only) animation supplied)
     * @param direction Chooses which direction to use for the animation (can either rotate
     * or use separate animations). Defaults to the default direction, assumed Direction.NORTH.
     * @return An array of images corresponding to the animation; null if an animation was not found.
     */
    public BufferedImage[] getAnimation(String name, int imageIndex, Direction direction)
    {
        ImageKey key = new ImageKey(name, imageIndex, direction);
        if (images.containsKey(key))
            return images.get(key).getFrames();
        
        key = new ImageKey(name, imageIndex, Direction.NORTH);
        
        if (images.containsKey(key))
        {
            BufferedImage frames[] = images.get(key).getFrames();
            int angle = Direction.NORTH.getAngle() - direction.getAngle();
            
            for(int i=0; i<frames.length; i++)
            {
                frames[i] = ImageUtil.rotate(frames[i], angle);
            }
        }
        
        return null;
    }

    /**
     * Returns the range of indices that are valid for the given image (or animation):
     * from 0 (inclusive) to the returned value (exclusive).
     * 
     * @param name The name this image is identified by in the resource file
     * @return The range of possible indices that can be used for the image.
     */
    public int getIndexRange(String name)
    {
        ImageKey last = images.headMap(new ImageKey(name, Integer.MAX_VALUE, Direction.NORTH)).lastKey();
        
        return last.getIndex() + 1;
    }
}
