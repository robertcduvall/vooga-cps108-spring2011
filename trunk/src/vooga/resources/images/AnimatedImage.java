package vooga.resources.images;

import java.awt.image.BufferedImage;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Contains a sequence of BufferedImages with
 * associated time delays.
 * 
 * @author Misha
 *
 */
public class AnimatedImage
{
    private TreeMap<Integer, BufferedImage> frames;
    private int totalTime;
    
    /**
     * Creates a new AnimatedImage with no frames.
     */
    public AnimatedImage()
    {
        frames = new TreeMap<Integer, BufferedImage>();
        totalTime = 0;
    }
    
    /**
     * Adds a frame with no duration (this will never
     * be used if the getFrame() method is called, but
     * it will be returned with the getFrames() method).
     * 
     * @param frame The image to add
     */
    public void addFrame(BufferedImage frame)
    {
        addFrame(frame, 0);
    }
   
    /**
     * Adds a frame which lasts for a specified duration.
     * 
     * @param frame The image to add
     * @param duration The duration (in milliseconds)
     */
    public void addFrame(BufferedImage frame, int duration)
    {
        frames.put(totalTime, frame);
        totalTime += duration;
    }
    
    /**
     * Retrieves a frame of this image based on a time counter.
     * 
     * This could be used to update an animation (e.g., in a
     * Sprite class) as follows:
     * 
     * <br /> <br />
     * 
     * <code>
     * update(long elapsedTime) {<br />
     *  myTime += elapsedTime; <br />
     *  setImage(myAnimatedImage.getFrame(myTime)); <br />
     * }
     * </code>
     * 
     * @param time The value of a time counter, in milliseconds. Warning: do not use this method
     * in a program that is intended to last for over 292 million years, else this counter will overflow.
     * @return The corresponding frame of the animation.
     */
    public BufferedImage getFrame(long time)
    {
        int subTime = totalTime == 0 ? 0 : (int) (time % totalTime);
        int pastDuration = frames.headMap(subTime, true).lastKey();
        return frames.get(pastDuration);
    }
    
    /**
     * Returns all the frames of this animation, stripped
     * of their duration values.
     * 
     * @return The frames, as a BufferedImage[].
     */
    public BufferedImage[] getFrames()
    {
        BufferedImage result[] = new BufferedImage[frames.size()];
        
        int i=0;
        for(BufferedImage frame : frames.values())
            result[i++] = frame;
        
        return result;
    }

}
