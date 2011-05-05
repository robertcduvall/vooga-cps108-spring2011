package games.pacman.sprites.players;

import games.pacman.sprites.players.Players;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;

/**
 * Pacman, controllable by arrows keys.
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
     * Creates a new pacman at (x,y)
     * 
     * @param game The game that created this paddle.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public PacMan (VoogaGame game, double x, double y)
    {
        super(game.getImageLoader().getImage("pacman"), (int)x, (int)y);
        this.game=game;
        origX=x - getWidth()/2;
        origY=y - getHeight();
        setOrigPosition();
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

	@Override
	public void changeAngle() {		
	}

	public void loseLife() {
		this.numLives--;
		this.game.fireEvent(this, "SpawnEnemies");
		setOrigPosition();
	}

	public int getLives() {
		return numLives;
	}

	@Override
	public void respondToWall() {
		// TODO Auto-generated method stub
		
	}

	


    
 //   System.exit(0);

  //  game.getLevelManager().addArchetypeSprite("ball", (int) getCenterX(), (int) getY());

}
