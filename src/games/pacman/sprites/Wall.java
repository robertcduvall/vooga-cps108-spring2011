package games.pacman.sprites;

import games.pacman.PacManGame;
import java.awt.image.BufferedImage;

import vooga.collisions.shapes.Vertex;
import vooga.collisions.shapes.collisionShapes.CollisionQuadrilateral;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;

/**
 * A Wall that constructs the level of the game
 * 
 * @author DJ Sharkey
 */
@SuppressWarnings("serial")
public class Wall extends Sprite
{   
    
    /**
     * Construct a wall at the given row and column
     * with the given length, orientation, and dimension.
     * 
     * @param image ignored
     * @param x The column in which to place the block.
     * @param y The row in which to place the block.
     */
    public Wall(BufferedImage image, int x, int y)
    {
        super(image, 1 + x * (2 + image.getWidth()), 1 + y * (2 + image.getHeight()));
        
     //   this.addComponents(new CollisionPolygonC(new CollisionQuadrilateral(new Vertex(0,0), new Vertex(0,this.getWidth()), new Vertex(this.getHeight(),0), new Vertex(this.getWidth(),this.getHeight()))));

        
    	System.out.println("created");
        setImage(PacManGame.imageLoader.getImage("wall"));
        this.setAngle(Direction.NORTH.getAngle());
    }
 
}
