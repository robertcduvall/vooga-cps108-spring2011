package games.watcher.collisions;
import games.watcher.sprites.Children;
import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;

/**
 * Collision group for bouncing balls off the edge of the screen.
 * 
 * 
 * @author Conrad
 */
@SuppressWarnings("unchecked")
public class ChildrenHitsEdge extends EdgeCollisionGroup
{    
    @Override
    public void collidedTop (Sprite s)
    {
        ((Children) s).hitTopBorder();
    }

	@Override
    public void collidedRight (Sprite s)
    {
        ((Children) s).hitRightBorder();
    }


    @Override
    public void collidedLeft (Sprite s)
    {
        ((Children) s).hitLeftBorder();
    }


    @Override
    public void collidedBottom (Sprite s)
    {
    	((Children) s).hitBottomBorder();
      //  s.setActive(false); 
      //  Breakout.eventManager.fireEvent(this, "Game.BallLost");
    }
}
