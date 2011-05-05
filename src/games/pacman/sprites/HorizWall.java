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
public class HorizWall extends AbstractWall
{   
    
    /**
     * Construct a wall at the given row and column
     * with the given length, orientation, and dimension.
     * 
     * @param image ignored
     * @param x The column in which to place the block.
     * @param y The row in which to place the block.
     */
    public HorizWall(BufferedImage image, int x, int y,double angle)
    {
      // super(image,x+image.getWidth(),y-image.getHeight()/2+image.getWidth()/2);
    	super(image,x,y);
        setImage(PacManGame.imageLoader.getImage("horizWall"));
   /*     this.setX(this.getHeight()/2+this.getX());
        this.setY(this.getWidth()/2+this.getY());*/

    }
 
}
