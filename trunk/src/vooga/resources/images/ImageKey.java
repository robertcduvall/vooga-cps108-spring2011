package vooga.resources.images;

import vooga.resources.Direction;

/**
 * Contains all the data by which an image or animation 
 * is indexed in the ImageLoader class: 
 * name, index, and direction.
 * 
 * Not really intended for use by any other classes, since
 * it doesn't have much associated functionality beyond being
 * capable of being a key in a TreeMap.
 * 
 * @author Misha
 *
 */
public class ImageKey implements Comparable<ImageKey>
{
    private String name;
    private int index;
    private Direction dir;

    public ImageKey(String name, int index, Direction dir)
    {
        this.name = name;
        this.index = index;
        this.dir = dir;
    }

    @Override
    public int compareTo (ImageKey other)
    {
        if (name.equals(other.name))
        {
            if (index == other.index)
            {
                return dir.getAngle() - other.dir.getAngle();
            }
            else
            {
                return index - other.index;
            }
        }
        else
        {
            return name.compareTo(other.name);
        }
    }
    
    @Override
    public boolean equals(Object other)
    {
        if (other instanceof ImageKey)
        {
            return this.compareTo((ImageKey) other) == 0;
        }
        else return false;
    }

    public int getIndex ()
    {
        return index;
    }
}
