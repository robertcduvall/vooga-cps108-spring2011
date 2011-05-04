package games.mariobros.sprites;

import games.mariobros.MarioBros;

import java.awt.image.BufferedImage;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;

/**
 * A block to be destroyed over the course of the game.
 * 
 * @author Misha
 */
@SuppressWarnings("serial")
public class Block extends Sprite
{
    private int hp;
    
//    /**
//     * Constructs a default block at the (x,y)
//     * row and column with 1 hit point and the
//     * default color.
//     * 
//     * @param image ignored
//     * @param x The column in which to place the block.
//     * @param y The row in which to place the block.
//     */
//    public Block(BufferedImage image, int x, int y)
//    {
//        this(image, x, y, 0);
//    }
    
    /**
     * Construct a block at the given row and column
     * with the given color.
     * 
     * @param image ignored
     * @param x The column in which to place the block.
     * @param y The row in which to place the block.
     * @param c The color (an index into the image)
     */
    public Block(BufferedImage image, int x, int y)
    {
        this(image, x, y, 1);
    }
    
    /**
     * Construct a block at the given row and column
     * with the given color and number of hit points.
     * 
     * @param image ignored
     * @param x The column in which to place the block.
     * @param y The row in which to place the block.
     * @param c The color (an index into the image)
     * @param hp The starting amount of HP the block has.
     */
    public Block(BufferedImage image, int x, int y, int hp)
    {
        super(image, 1 + x * (2 + image.getWidth()), 1 + y * (2 + image.getHeight()));
        
        setImage(MarioBros.imageLoader.getImage("block"));
        this.hp = hp;
        
        this.setAngle(Direction.NORTH.getAngle());
    }
    
    /**
     * Damage this block by 1 HP, and destroy if the 
     * HP total becomes 0.
     */
    public void damage()
    {
        if (--hp == 0)
            setActive(false);
    }
}
