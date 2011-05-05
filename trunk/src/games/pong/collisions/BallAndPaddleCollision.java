package games.pong.collisions;

import games.breakout.sprites.Paddle;
import games.pong.sprites.Ball;
import games.pong.sprites.AbstractPaddle;
import games.pong.sprites.PlayerPaddle;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.collisions.collisionManager.CollisionManager;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

public class BallAndPaddleCollision extends BasicCollisionGroup<Ball, PlayerPaddle>{
	@Override
    public boolean areCollide(Ball ball, PlayerPaddle paddle)
    {

        return CollisionManager.isPixelCollide(ball.getX(), ball.getY(), ball.getImage(), 
                                              paddle.getX(), paddle.getY(), paddle.getImage());
        
        
//        return dx*dx + dy*dy < ball.getRadius() * ball.getRadius();
    }

    @Override
    public void collided (Ball ball, PlayerPaddle paddle)
    {
    	System.out.println("collided!");
        double paddleRatio = (ball.getCenterY() - paddle.getCenterY())/paddle.getHeight();
        ball.setAngle(0+90*paddleRatio);

    }

}
