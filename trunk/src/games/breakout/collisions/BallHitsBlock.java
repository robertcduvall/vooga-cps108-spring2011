package games.breakout.collisions;

import games.breakout.sprites.Ball;
import games.breakout.sprites.Block;
import vooga.collisions.collisionManager.BasicCollisionGroup;

/**
 * A collision group for testing whether a ball hits a block or not. 
 * 
 * @author Misha
 *
 */
public class BallHitsBlock extends BasicCollisionGroup<Ball, Block>
{
    
    /**
     * Tests whether a ball and a block have collided, treating each
     * ball as a perfect circle and each block as a perfect rectangle.
     */
    @Override
    public boolean areCollide(Ball ball, Block block)
    {
        double dx = Math.abs(ball.getCenterX() - block.getCenterX()) - block.getWidth()/2D;
        double dy = Math.abs(ball.getCenterY() - block.getCenterY()) - block.getHeight()/2D;
        
        if (dx > ball.getRadius() || dy > ball.getRadius())
            return false;
        
        if (dx < 0 || dy < 0)
            return true;
        
        return dx*dx + dy*dy < ball.getRadius() * ball.getRadius();
        
//      return CollisionManager.isPixelCollide(ball.getX(), ball.getY(), ball.getImage(), 
//                                             block.getX(), block.getY(), block.getImage());

    }
    
    /**
     * Handle a collision between a ball and a block.
     */
    @Override
    public void collided (Ball ball, Block block)
    {
        if (ball.getCenterX() < block.getX())
            ball.bounceLeft();
        
        if (ball.getCenterX() > block.getX() + block.getWidth())
            ball.bounceRight();
        
        if (ball.getCenterY() < block.getY())
            ball.bounceUp();
        
        if (ball.getCenterY() > block.getY() + block.getHeight())
            ball.bounceDown();
        
        block.damage();
    }

}
