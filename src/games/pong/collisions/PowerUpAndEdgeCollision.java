package games.pong.collisions;

import games.pong.PongGame;
import games.pong.sprites.Ball;
import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;

public class PowerUpAndEdgeCollision extends EdgeCollisionGroup{     
	    @Override
	    public void collidedTop (Sprite s)
	    {
	        ((Ball) s).bounceDown();
	    }


	    @Override
	    public void collidedRight (Sprite s)
	    {
	    	s.setActive(false);//((Ball) s).bounceLeft();
	    }


	    @Override
	    public void collidedLeft (Sprite s)
	    {
	    	s.setActive(false);//((Ball) s).bounceRight();
	        
	    }


	    @Override
	    public void collidedBottom (Sprite s)
	    {
	       
	    	((Ball) s).bounceUp();

	    }

}