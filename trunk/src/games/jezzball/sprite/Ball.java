package games.jezzball.sprite;

import java.awt.image.BufferedImage;

import vooga.sprites.improvedsprites.Sprite;

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
    
    
    public void collideTop(){
        if(getVerticalSpeed()<0){
            setVerticalSpeed(-1*getVerticalSpeed());
        }
    }
    public void collideBottom(){
        if(getVerticalSpeed()>0){
            setVerticalSpeed(-1*getVerticalSpeed());
        }
    }
    
    public void collideLeft(){
        if(getHorizontalSpeed()<0){
            setHorizontalSpeed(-1*getHorizontalSpeed());
        }
    }
    
    public void collideRight(){
        if(getHorizontalSpeed()>0){
            setHorizontalSpeed(-1*getHorizontalSpeed());
        }
    }
    public double getRadius() {
        return getWidth()/2;
    }
    
}
