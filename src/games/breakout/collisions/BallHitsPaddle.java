package games.breakout.collisions;

import games.breakout.sprites.Ball;
import games.breakout.sprites.Paddle;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.collisions.collisionManager.CollisionManager;

public class BallHitsPaddle extends BasicCollisionGroup<Ball, Paddle>
{
    @Override
    public boolean areCollide(Ball ball, Paddle paddle)
    {
        double dx = Math.abs(ball.getCenterX() - paddle.getCenterX()) - paddle.getWidth()/2D;
        double dy = Math.abs(ball.getCenterY() - paddle.getCenterY()) - paddle.getHeight()/2D;
        
        
        if (dx > ball.getRadius() || dy > ball.getRadius())
            return false;
        
        if (dx < 0 || dy < 0)
            return true;
        
//        return CollisionManager.isPixelCollide(ball.getX(), ball.getY(), ball.getImage(), 
//                                               paddle.getX(), paddle.getY(), paddle.getImage());
        
        return dx*dx + dy*dy < ball.getRadius() * ball.getRadius();
    }
    
    @Override
    public void collided (Ball ball, Paddle paddle)
    {
        double paddleRatio = (ball.getCenterX() - paddle.getCenterX())/paddle.getWidth();
        ball.setAngle(-90 + 90 * paddleRatio);
    }
}
