package games.watcher.collisions;
import games.chickenfox.sprites.Chicken;
import games.chickenfox.sprites.Fox;
import games.watcher.sprites.Children;
import games.watcher.sprites.Monster;
import vooga.collisions.collisionManager.BasicCollisionGroup;

/**
 * A collision group for testing whether a ball hits a block or not. 
 * 
 * @author Conrad
 *
 */
public class ChildrenHitsMonster extends BasicCollisionGroup<Children, Monster>
{
    
    /**
     * Tests whether a ball and a block have collided, treating each
     * ball as a perfect circle and each block as a perfect rectangle.
     */
    public boolean areCollide(Children children, Monster monster)
    {
        double dx = Math.abs(children.getCenterX() - monster.getCenterX()) - monster.getWidth()/2D;
        double dy = Math.abs(children.getCenterY() - monster.getCenterY()) - monster.getHeight()/2D;
        
        if (dx > children.getRadius() || dy > children.getRadius())
            return false;
        
        if (dx < 0 || dy < 0)
            return true;
        
        return dx*dx + dy*dy < children.getRadius() * children.getRadius();
    }
    
    
     /**
     * Handle a collision between a ball and a block.
     */
    @Override
    public void collided (Children children, Monster monster)
    { 
     System.out.println("Children was hit by Monster");
     children.setActive(false);
    }

}
