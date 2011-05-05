package games.pacman.sprites.players;

import games.pacman.sprites.players.Players;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import com.golden.gamedev.util.ImageUtil;

import vooga.collisions.shapes.Vertex;
import vooga.collisions.shapes.collisionShapes.CollisionCircle;
import vooga.collisions.shapes.collisionShapes.CollisionPolygon;
import vooga.collisions.shapes.collisionShapes.CollisionQuadrilateral;
import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.collisions.CollisionCircleC;
import vooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;

/**
 * PacMan, controllable by arrows keys.
 * 
 * @author DJ Sharkey
 *
 */
@SuppressWarnings("serial")
public class PacMan extends Players
{   
    /**
     * The pacman speed, in pixels per ms.
     */
    public static final Double PACMAN_SPEED = .1; 
    
    private VoogaGame game;
    private int numLives;
    
    
	
    /**
     * @return The number of balls remaining in the supply.
     */
    public int getNumLives ()
    {
        return numLives;
    }

    /**
     * Set the number of balls in the supply.
     * 
     * @param ballCount The new number of balls.
     */
    public void setNumLives (int numLives)
    {
        this.numLives = numLives;
    }

    /**
     * Creates a new pacman at (x,y)
     * 
     * @param game The game that created this paddle.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public PacMan (VoogaGame game, double x, double y)
    {
        super(game, x, y, game.getImageLoader().getImage("pacman"));
        
      
       this.numLives = 3;
     
        game.registerEventHandler("Input.User.Start", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
            		//TODO
            }            
        });
        
        game.registerEventHandler("Input.User.Left", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                move(180D);
            }            
        });
        
        game.registerEventHandler("Input.User.Right", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                move(0D);
            }            
        });
        
        game.registerEventHandler("Input.User.Up", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                move(270D);
            }            
        });
        
        game.registerEventHandler("Input.User.Down", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                move(90D);
            }            
        });
        
        game.registerEventHandler("Game.LifeLost",  new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                //TODO
            }            
        });
    }
    
    /**
     * Move paddle, if the screen boundaries/walls allow.
     * @param dx The difference in x-coordinate.
     */
    protected void move (Double angle)
    {
    	
    	this.setAngle(angle);
    	this.setAbsoluteSpeed(PACMAN_SPEED);
    }
	


    
 //   System.exit(0);

  //  game.getLevelManager().addArchetypeSprite("ball", (int) getCenterX(), (int) getY());

}
