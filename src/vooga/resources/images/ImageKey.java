package vooga.resources.images;

import vooga.resources.Direction;

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
