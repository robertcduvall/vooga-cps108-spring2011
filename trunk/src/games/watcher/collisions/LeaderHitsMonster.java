package games.watcher.collisions;
import games.watcher.sprites.Monster;
import games.watcher.sprites.Leader;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.collisions.collisionManager.CollisionManager;

/**
 * A collision group for handling ball-paddle collisions.
 * 
 * @author Conrad
 *
 */
public class LeaderHitsMonster extends BasicCollisionGroup<Leader, Monster>
{
    /**
     * Tests whether a Chicken and a Detective have collided, treating each
     * Chicken as a perfect circle and each Detective as a perfect rectangle,
     * then looking at the pixel collisions.
     */
    @Override
    public boolean areCollide(Leader leader, Monster monster)
    {
        double dx = Math.abs(leader.getCenterX() - monster.getCenterX()) - monster.getWidth()/2D;
        double dy = Math.abs(leader.getCenterY() - monster.getCenterY()) - monster.getHeight()/2D;
        
        
        if (dx > leader.getRadius() || dy > leader.getRadius())
            return false;
        
        if (dx < 0 || dy < 0)
            return true;
        
        return CollisionManager.isPixelCollide(leader.getX(), leader.getY(), leader.getImage(), 
                                               monster.getX(), monster.getY(), monster.getImage());
        
//        return dx*dx + dy*dy < Sam.getRadius() * Sam.getRadius();
    }
    
    /**
     * Bounces the Sam off the Monster at a variable angle
     * once they have collided.
     */
    public void collided (Leader leader, Monster monster)
    {
        double MonsterRatio = (leader.getCenterX() - monster.getCenterX())/monster.getWidth();
        leader.setAngle(-90 + 90 * MonsterRatio);
    }
}
