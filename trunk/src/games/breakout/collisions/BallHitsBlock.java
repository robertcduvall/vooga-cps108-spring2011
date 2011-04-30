package games.breakout.collisions;

import games.breakout.sprites.Ball;
import games.breakout.sprites.Block;
import vooga.collisions.collisionManager.BasicCollisionGroup;

public class BallHitsBlock extends BasicCollisionGroup<Ball, Block>
{

    @Override
    public void collided (Ball ball, Block block)
    {
        if (block.getX() < ball.getCenterX() && ball.getCenterX() < block.getX() + block.getWidth())
            ball.setVerticalSpeed(-ball.getVerticalSpeed());
        
        if (block.getY() < ball.getCenterY() && ball.getCenterY() < block.getY() + block.getHeight())
            ball.setHorizontalSpeed(-ball.getHorizontalSpeed());
        
        block.setActive(false);
    }

}
