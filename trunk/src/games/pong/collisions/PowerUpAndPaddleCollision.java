package games.pong.collisions;

import games.breakout.sprites.Paddle;
import games.pong.sprites.Ball;
import games.pong.sprites.AbstractPaddle;
import games.pong.sprites.PlayerPaddle;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.collisions.collisionManager.CollisionManager;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

public class PowerUpAndPaddleCollision extends BasicCollisionGroup<Ball, AbstractPaddle>{

    @Override
    public void collided (Ball ball, AbstractPaddle paddle)
    {
    	paddle.decreaseHealth();
    	System.out.println(paddle.getClass() );
    	ball.setActive(false);
    }
}
