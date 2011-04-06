package vooga.resources.images;

import java.awt.image.BufferedImage;
import java.io.File;
import vooga.resources.Direction;

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
public class ImageLoader //TODO add functionality
{
    /**
     * Initializes the ImageLoader from a resource file. 
     * 
     * @param resource The file to load the image specs from
     */
    public ImageLoader(File resource)
    {
    }
    
    /** See getImage(name, imageIndex, direction, time). */
    public BufferedImage getImage(String name) {return getImage(name, 0, null, 0);}
    /** See getImage(name, imageIndex, direction, time). */
    public BufferedImage getImage(String name, int imageIndex) {return getImage(name, imageIndex, null, 0);}
    /** See getImage(name, imageIndex, direction, time). */
    public BufferedImage getImage(String name, Direction direction) {return getImage(name, 0, direction, 0);}
    /** See getImage(name, imageIndex, direction, time). */
    public BufferedImage getImage(String name, int imageIndex, Direction direction) {return getImage(name, imageIndex, direction, 0);}
    /** See getImage(name, imageIndex, direction, time). */
    public BufferedImage getImage(String name, int imageIndex, long time) {return getImage(name, imageIndex, null, time);}
    /** See getImage(name, imageIndex, direction, time). */
    public BufferedImage getImage(String name, Direction direction, long time) {return getImage(name, 0, direction, time);}

    /**
     * Returns an image specified in the resource file.
     * 
     * @param name The name this image is identified by in the resource file.
     * @param imageIndex Chooses which of several images corresponding to this name
     * to choose (defaults to the first (or only) image supplied)
     * @param direction Chooses which direction to use for the image (can either rotate
     * or use separate images). Defaults to first (or only) direction specified.
     * @param time Chooses which frame to use for animated images. Defaults to 
     * first (or only) frame.
     * @return The corresponding image.
     */
    public BufferedImage getImage(String name, int imageIndex, Direction direction, long time)
    {
        return null;
    }

    /** See getAnimation(name, imageIndex, direction) */    
    public BufferedImage[] getAnimation(String name) {return getAnimation(name, 0, null);}
    /** See getAnimation(name, imageIndex, direction) */    
    public BufferedImage[] getAnimation(String name, int imageIndex) {return getAnimation(name, imageIndex, null);}
    /** See getAnimation(name, imageIndex, direction) */
    public BufferedImage[] getAnimation(String name, Direction direction) {return getAnimation(name, 0, direction);}
    
    /**
     * Returns an animation specified in the resource file.
     * 
     * @param name The name this animation is identified by in the resource file.
     * @param imageIndex Chooses which of several animations corresponding to this name
     * to choose (defaults to the first (or only) animation supplied)
     * @param direction Chooses which direction to use for the animation (can either rotate
     * or use separate animations). Defaults to first (or only) direction specified.
     * @return An array of images corresponding to the animation.
     */
    public BufferedImage[] getAnimation(String name, int imageIndex, Direction direction)
    {
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
        return 0;
    }
}
