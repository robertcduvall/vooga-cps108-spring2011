package vooga.resources.images;

import java.awt.image.BufferedImage;
import java.util.SortedMap;
import java.util.TreeMap;

public class AnimatedImage
{
    private SortedMap<Integer, BufferedImage> frames;
    private int totalTime;
    
    public AnimatedImage()
    {
        frames = new TreeMap<Integer, BufferedImage>();
        totalTime = 0;
    }
    
    public void addFrame(BufferedImage frame)
    {
        addFrame(frame, 0);
    }
    
    public void addFrame(BufferedImage frame, int duration)
    {
        frames.put(totalTime, frame);
        totalTime += duration;
    }
    
    public BufferedImage getFrame(long time)
    {
        int subTime = totalTime == 0 ? 0 : (int) (time % totalTime);
        int pastDuration = frames.headMap(subTime).lastKey();
        return frames.get(pastDuration);
    }
    
    public BufferedImage[] getFrames()
    {
        BufferedImage result[] = new BufferedImage[frames.size()];
        
        int i=0;
        for(BufferedImage frame : frames.values())
            result[i++] = frame;
        
        return result;
    }

}
