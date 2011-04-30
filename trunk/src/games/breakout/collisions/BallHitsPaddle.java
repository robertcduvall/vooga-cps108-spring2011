package games.breakout.collisions;

import games.breakout.sprites.Ball;
import games.breakout.sprites.Paddle;
import vooga.collisions.collisionManager.BasicCollisionGroup;

public class BallHitsPaddle extends BasicCollisionGroup<Ball, Paddle>
{

    @Override
    public void collided (Ball ball, Paddle paddle)
    {
        if (paddle.getX() < ball.getCenterX() && ball.getCenterX() < paddle.getX() + paddle.getWidth())
            ball.setVerticalSpeed(-ball.getVerticalSpeed());
        
        if (paddle.getY() < ball.getCenterY() && ball.getCenterY() < paddle.getY() + paddle.getHeight())
            ball.setHorizontalSpeed(-ball.getHorizontalSpeed());
    }

}
