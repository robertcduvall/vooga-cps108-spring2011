package games.missileDefense.sprites;

import com.golden.gamedev.util.ImageUtil;

import vooga.collisions.shapes.ShapeFactory;
import vooga.collisions.shapes.collisionShapes.CollisionPolygon;
import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;
import vooga.util.buildable.components.IComponent;
import vooga.util.buildable.components.predefined.basic.HealthC;

public class Gun extends Sprite
{
	VoogaGame game;

	public Gun(VoogaGame game, double x, double y)
	{
		super(game.getImageLoader().getImage("gun"));

		this.setX(x);
		this.setY(y);
		setAngle(Direction.NORTH.getAngle());
		this.addComponents(new HealthC(1.0), new CollisionPolygonC(new CollisionPolygon(ShapeFactory.makePolygonFromImage(ImageUtil.resize(image, width,height), 2))));

		this.game = game;

		this.game.registerEventHandler("Input.User.Fire", new IEventHandler()
		{
			@Override
			public void handleEvent(Object o) 
			{
				shoot(getAngle());
			}
		});
		
		this.game.registerEventHandler("Input.User.Right", new IEventHandler()
		{
			@Override
			public void handleEvent(Object o) 
			{
				turnRight();
			}
		});
		
		this.game.registerEventHandler("Input.User.Left", new IEventHandler()
		{
			@Override
			public void handleEvent(Object o) 
			{
				turnLeft();
			}
		});
	}

	public void shoot (double angle)
	{
		System.out.println("fire");
		Sprite bullet = game.getLevelManager().addArchetypeSprite("bullet", (int)getCenterX(), (int)getCenterY());
		bullet.move(-bullet.getWidth(), -bullet.getHeight());
		bullet.setMovement(0.35, angle);
	}
	
	public void turnLeft ()
    {
       this.setAngle(getAngle() - 1);
    }


    public void turnRight ()
    {
        setAngle(getAngle() + 1);
    }
    
    public void damage()
	{
		this.getComponent(HealthC.class).decrease(1.0);
		{
			if(this.getComponent(HealthC.class).isDead())
			{
				this.setActive(false);
			}
		}
	}
    
    public double getHealth()
    {
    	return(this.getComponent(HealthC.class).getCurrent());
    }
    
    public boolean isDead()
    {
    	return(this.getComponent(HealthC.class).isDead());
    }
}
