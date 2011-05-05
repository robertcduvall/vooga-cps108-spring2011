package games.pong.collisions;

import games.pong.sprites.Ball;
import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;

public class BallAndEdgeCollision extends EdgeCollisionGroup{     
	    @Override
	    public void collidedTop (Sprite s)
	    {
	        ((Ball) s).bounceDown();
	    }


	    @Override
	    public void collidedRight (Sprite s)
	    {
	        ((Ball) s).bounceLeft();
	    }


	    @Override
	    public void collidedLeft (Sprite s)
	    {
	        ((Ball) s).bounceRight();
	    }


	    @Override
	    public void collidedBottom (Sprite s)
	    {
	       
	    	((Ball) s).bounceUp();
	    	// s.setActive(false);
	        
	       // Breakout.eventManager.fireEvent(this, "Game.BallLost");
	    }

}