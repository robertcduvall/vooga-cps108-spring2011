package games.breakout.sprites;

import games.breakout.Breakout;
import java.awt.image.BufferedImage;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;

@SuppressWarnings("serial")
public class Block extends Sprite
{
    private int hp;
    
    public Block(BufferedImage image, int x, int y)
    {
        this(image, x, y, 0);
    }
    
    public Block(BufferedImage image, int x, int y, int tile)
    {
        this(image, x, y, tile, 1);
    }
    
    public Block(BufferedImage image, int x, int y, int tile, int durability)
    {
        super(image, x * image.getWidth(), y * image.getHeight());
        
        setImage(Breakout.imageLoader.getImage("block", tile));
        hp = durability;
        
        this.setAngle(Direction.NORTH.getAngle());
    }
    
    public void damage()
    {
        if (--hp == 0)
            setActive(false);
    }
}
