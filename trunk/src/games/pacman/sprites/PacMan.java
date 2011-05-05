package games.pacman.sprites;

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
public class PacMan extends Sprite
{   
    /**
     * The pacman speed, in pixels per ms.
     */
    public static final Double PACMAN_SPEED = .1; 
    
    private VoogaGame game;
    private int numLives;
    
    
	@Override
	public void render(Graphics2D g,int x,int y) {
		AffineTransform aTransform = new AffineTransform();
        aTransform.translate((int) this.getX() +width/2, 
                             (int) this.getY()+height/2);
        aTransform.rotate(Math.toRadians(this.getAngle()+90));
        
        aTransform.translate((int) -width/2, 
                             (int) -height/2);
       
        
        if (this.getAngle()==180.0){
        	g.drawImage(ImageUtil.flip(	
        								ImageUtil.resize(
        										image, width, height)),aTransform,null);
        }else 
        	g.drawImage(ImageUtil.resize(image, width, height),aTransform,null);
        super.renderComponents(g, x, y);
        g.setColor(Color.WHITE);
        this.getCollisionShape().render(g);
        g.setColor(Color.BLACK);
	}
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
        super(game.getImageLoader().getImage("pacman"));
        
        setX(x - getWidth()/2);
        setY(y - getHeight());
        
        this.game = game;
        this.numLives = 3;
      //  this.addComponents(new CollisionPolygonC(new CollisionQuadrilateral(new Vertex(0,0), new Vertex(0,this.getWidth()), new Vertex(this.getHeight(),0), new Vertex(this.getWidth(),this.getHeight()))));
        //this.addComponent(new CollisionCircleC(this.getCenterPoint(),this.getWidth()/2));
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
	
    public void collided(Sprite spr) {
		this.setAbsoluteSpeed(0);
	    int colWidth =(int) this.getCollisionShape().getWidth();
	    int colHeight=(int) this.getCollisionShape().getHeight();
	    int colX = (int) this.getCollisionShape().getTopLeftCorner().getX();
	    int colY = (int) this.getCollisionShape().getTopLeftCorner().getY();
	    
	    int xOverlap=0;
	    int yOverlap=0;
	    if(this.getAngle()==0){
		    xOverlap = (1*(int) (spr.getCollisionShape().getTopLeftCorner().getX()-(colX+colWidth)))-2;
	    }else if(this.getAngle()==90){
		    yOverlap = 1*(int) (spr.getCollisionShape().getTopLeftCorner().getY()-(colHeight+colY))-2;
	    }else if(this.getAngle()==180){
	    	xOverlap=(int)(spr.getCollisionShape().getTopLeftCorner().getX()+spr.getCollisionShape().getWidth()-colX)+2;
	    }else if(this.getAngle()==270){
	    	yOverlap=(1*(int)(spr.getCollisionShape().getTopLeftCorner().getY()+spr.getCollisionShape().getHeight()-colY))+2;
	    }
	    this.move(xOverlap,yOverlap);
	    System.out.println(this.getAngle()+" "+xOverlap+" "+yOverlap);
	    System.out.println("collided");		
	}

    
 //   System.exit(0);

  //  game.getLevelManager().addArchetypeSprite("ball", (int) getCenterX(), (int) getY());

}
