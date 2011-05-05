package games.pong.collisions;

import games.breakout.sprites.Paddle;
import games.pong.sprites.Ball;
import games.pong.sprites.AbstractPaddle;
import games.pong.sprites.PlayerPaddle;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.collisions.collisionManager.CollisionManager;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

public class BallAndPaddleCollision extends BasicCollisionGroup<Ball, AbstractPaddle>{

    @Override
    public void collided (Ball ball, AbstractPaddle paddle)
    {
    	ball.setSpeed(1.005*ball.getHorizontalSpeed(), 1.01*ball.getVerticalSpeed());
    	//System.out.println("collided!");
    	
        double paddleRatio = (ball.getCenterY() - paddle.getCenterY())/paddle.getHeight();
        //System.out.println(ball.getAngle());
        //if(ball.getAngle()<=270 && ball.getAngle()>=90)
        if(ball.getHorizontalSpeed()<0) {
        	ball.setX(ball.getX()+3);
        	ball.setAngle(90*paddleRatio);
        	
        }
        else {
        	ball.setX(ball.getX()-3);
        	ball.setAngle(180-90*paddleRatio);
        	
        }
    }
}
