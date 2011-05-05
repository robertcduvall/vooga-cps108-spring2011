package games.jezzball.collision;

import games.breakout.sprites.Paddle;
import games.jezzball.sprite.Ball;
import games.jezzball.sprite.Tile;
import games.jezzball.sprite.Wall;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.collisions.collisionManager.CollisionManager;
import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;
/**
 * Handles collisions between the ball and the wall.
 * @author KevinWang
 * @author Misha (areCollide method is modified from Misha's)
 *
 */
public class WallCollision extends BasicCollisionGroup<Ball, Wall>{

    private final static int TOP = 0;
    private final static int RIGHT = 1;
    private final static int BOTTOM = 2;
    private final static int LEFT = 3;
    
    private int margin = 3;// Small margin to make sure after collision is resolve there won't be a second collision
    
    /**
     * Math voodo to see if Collision actually happened
     */
    @Override
    public boolean areCollide(Ball ball, Wall wall)
    {
        double dx = Math.abs(ball.getCenterX() - wall.getCenterX()) - wall.getWidth()/2D;
        double dy = Math.abs(ball.getCenterY() - wall.getCenterY()) - wall.getHeight()/2D;
        
        
        if (dx > ball.getRadius() || dy > ball.getRadius())
            return false;
        
        if (dx < 0 || dy < 0)
            return true;
        
        return CollisionManager.isPixelCollide(ball.getX(), ball.getY(), ball.getImage(), 
                wall.getX(), wall.getY(), wall.getImage());
        
    }
    
    /**
     * resolve collision
     */
    @Override
    public void collided(Ball ball, Wall wall) {
        int side = checkCollisionSide(ball, wall);
        
        switch (side){
        
        case TOP: ball.collideBottom(); ball.move(0, wall.getY()-ball.getHeight()-ball.getY()-margin); break;
        case BOTTOM: ball.collideTop(); ball.move(0, wall.getY()+wall.getHeight()-ball.getY()+margin); break; 
        case RIGHT : ball.collideLeft(); ball.move(wall.getX()+wall.getWidth()-ball.getX()+margin, 0); break; 
        case LEFT: ball.collideRight(); ball.move(wall.getX()-ball.getWidth()-ball.getX()-margin, 0); break;
        }
        
    }

    /**
     * Determines which side of the wall the collision happened.
     * @param ball
     * @param wall
     * @return int representing the side
     */
    private int checkCollisionSide(Ball ball, Wall wall) {
        
        boolean rightSide = (ball.getHorizontalSpeed()<0);
        boolean topSide = (ball.getVerticalSpeed()>0);
        double slope = Math.abs((ball.getY()-wall.getY())/(ball.getX()-wall.getX()));
        if(slope<1){
            return rightSide? RIGHT:LEFT;
        }
        else{
            return topSide? TOP:BOTTOM;
        }
    }

}
