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
public class VertWall extends AbstractWall
{   
    
    /**
     * Construct a wall at the given row and column
     * with the given length, orientation, and dimension.
     * 
     * @param image ignored
     * @param x The column in which to place the block.
     * @param y The row in which to place the block.
     */
    public VertWall(BufferedImage image, int x, int y,double angle)
    {
     //   super(image,x-image.getWidth()/2+image.getHeight()/2, y+image.getHeight());
        super(image, x, y);
    	setImage(PacManGame.imageLoader.getImage("vertWall"));
        
        System.out.println("x: " +x +" "+this.getX());
        System.out.println("y: " +y +" "+this.getY());

    }
 
}
