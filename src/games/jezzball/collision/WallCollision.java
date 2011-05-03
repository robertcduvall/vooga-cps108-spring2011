package games.jezzball.collision;

import games.breakout.sprites.Paddle;
import games.jezzball.sprite.Ball;
import games.jezzball.sprite.Tile;
import games.jezzball.sprite.Wall;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.collisions.collisionManager.CollisionManager;
import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;

public class WallCollision extends BasicCollisionGroup<Ball, Wall>{

    private final static int TOP = 0;
    private final static int RIGHT = 1;
    private final static int BOTTOM = 2;
    private final static int LEFT = 3;
    
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
        
//        return dx*dx + dy*dy < ball.getRadius() * ball.getRadius();
    }
    
    @Override
    public void collided(Ball ball, Wall wall) {
        int side = checkCollisionSide(ball, wall);
        System.out.println(side);
        System.out.println(ball.getX() + " "+ ball.getY());
        switch (side){
        
        case TOP: ball.collideBottom(); ball.move(0, wall.getY()-ball.getHeight()-ball.getY()); break;
        case BOTTOM: ball.collideTop(); ball.move(0, wall.getY()+wall.getHeight()-ball.getY());break; 
        case RIGHT : ball.collideLeft(); ball.move(wall.getX()+wall.getWidth()-ball.getX(), 0);break; 
        case LEFT: ball.collideRight(); ball.move(wall.getX()-ball.getWidth()-ball.getX(), 0);break;
        }
        
    }

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
