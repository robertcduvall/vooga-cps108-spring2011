package games.watcher.sprites;
import games.chickenfox.LevelCleared;

import java.awt.Point;
import java.io.File;

import com.golden.gamedev.object.SpriteGroup;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.BaseSprite;
import vooga.sprites.improvedsprites.Sprite;

/**
 * A paddle moved around by the player.
 * 
 * @author Conrad. constructed using Misha's general design as a template
 *
 */
@SuppressWarnings("serial")
public class Leader extends Sprite
{   
    /**
     * The paddle speed, in pixels per ms.
     */
    private VoogaGame game;
    private boolean hasChildren;
    private int fail,success,childCount;
    public vooga.sprites.spritegroups.SpriteGroup<Sprite> myChildren;

    /**
     * Set the number of balls in the supply.
     * 
     * @param childCount The new number of balls.
     */
    public void setChildCount (int childCount)
    {
        this.childCount = childCount;
        this.hasChildren = true;
    }
    
    
    /**
     * Creates a new paddle on top of the given (x,y)
     * coordinate.
     * 
     * @param game The game that created this paddle.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public Leader (VoogaGame game, double x, double y)
    {
        super(game.getImageLoader().getImage("leader"));
        
        setX(x);
        setY(y);
        setAngle(Direction.EAST.getAngle());
        this.game = game;
        hasChildren = true;
        
        /**
         * The user whistles to inform all of it's children to begin listening to his/her directions
         * to cross the arena
         */
        game.registerEventHandler("Input.User.Whistle", new IEventHandler()
        {
            	@Override
                public void handleEvent (Object o)
                {
            		enableChildrenListening();
                }    
        });
        
        game.registerEventHandler("Input.User.Cheat", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
               // setBallCount(100);
            }            
        });
        
        game.registerEventHandler("Input.User.Up", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                shiftY(-2D);
            }            
        });
        
        game.registerEventHandler("Input.User.Down", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                shiftY(2D);
            }            
        });
        
        game.registerEventHandler("Input.User.Left", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                shiftX(-2D);
            }            
        });
        
        game.registerEventHandler("Input.User.Right", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                shiftX(2D);
            }            
        });
        
        game.registerEventHandler("Game.LowerChildCount",  new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {lowerChildCount();}            
        });
    

    game.registerEventHandler("Game.RaiseChildCount",  new IEventHandler(){
        @Override
        public void handleEvent (Object o){
        	raiseChildCount();
        	}
        }	
    );
    }
    
   public boolean checkCount(){
	   return(success > childCount/2);
   }
    
   private void raiseChildCount() {
		success++;
	}
    
    /**
     * Allow a new ball to be launched, if there 
     * are balls remaining.
     */
    public void lowerChildCount (){
        	fail++;
    	if (childCount/2 < fail){
        	hasChildren = false;
            System.out.println("Game Over");
        }
    }
    
    
    /**
     * Move the paddle, if the screen boundaries allow for it.
     * @param dx The difference in x-coordinate.
     */
    protected void shiftX (double dx){
        double newX = getX() + dx;
        if (0 < newX && newX + getWidth() < game.getWidth())
            moveX(dx); 
        for(Sprite s : myChildren.getSprites()){
        	if(s.isActive())
        	((Children)s).move(dx, 0);
        }
    }
    
    /**
     * Move the paddle, if the screen boundaries allow for it.
     * @param dy The difference in y-coordinate.
     */
    protected void shiftY (double dy){
        double newY = getY() + dy;
        if (0 < newY && newY + getHeight() < game.getHeight())
            moveY(dy);
        for(Sprite s : myChildren.getSprites()){
        	if(s.isActive())
        	((Children)s).move(0, dy);
        }
    }

    /**
     * Launches a rock to destroy all monsters in the way
     */
    protected void launchBall (){ 
            game.getLevelManager().addArchetypeSprite("children", (int) getCenterX(), (int) getY());
    }
     
    
    private void enableChildrenListening() {
    	myChildren = game.getLevelManager().getCurrentLevel().getSpriteGroup("children");
	} 
    
    public double getRadius() {return getWidth()/2D;}
}
