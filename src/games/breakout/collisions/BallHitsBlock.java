package games.breakout.collisions;

import games.breakout.sprites.Ball;
import games.breakout.sprites.Block;
import vooga.collisions.collisionManager.BasicCollisionGroup;

public class BallHitsBlock extends BasicCollisionGroup<Ball, Block>
{
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
        
        block.setActive(false);
    }

}
