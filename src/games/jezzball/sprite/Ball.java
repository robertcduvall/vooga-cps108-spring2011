package games.jezzball.sprite;

import java.awt.image.BufferedImage;

import vooga.sprites.improvedsprites.Sprite;

/**
 * instance of ball in Jezzball. It bounces off the wall but
 * doesn't bounce against each other.
 * 
 * @author KevinWang
 *
 */
public class Ball extends Sprite{

    private static final long serialVersionUID = 1L;
    private double verticalSpeed;

    public Ball(BufferedImage image, int x, int y, int direction, double speed){
        super(image, x ,y);
        setAngle(direction);
        setAbsoluteSpeed(speed);
        
    }
    
    @Override
    public void update(long elapsedTime){
        super.update(elapsedTime);
        //System.out.println(getX()+" "+getY());
    }
    
    /**
     * resolves top side collision
     */
    public void collideTop(){
        if(getVerticalSpeed()<0){
            setVerticalSpeed(-1*getVerticalSpeed());
        }
    }
    
    /**
     * resolves bottom side collision
     */
    public void collideBottom(){
        if(getVerticalSpeed()>0){
            setVerticalSpeed(-1*getVerticalSpeed());
        }
    }
    
    /**
     * resolves left side collision
     */
    public void collideLeft(){
        if(getHorizontalSpeed()<0){
            setHorizontalSpeed(-1*getHorizontalSpeed());
        }
    }
    
    /**
     * resolves right side collision
     */
    public void collideRight(){
        if(getHorizontalSpeed()>0){
            setHorizontalSpeed(-1*getHorizontalSpeed());
        }
    }
    /**
     * returns radius
     * @return radius
     */
    public double getRadius() {
        return getWidth()/2;
    }

    
    
}
