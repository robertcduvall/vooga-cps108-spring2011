package vooga.sprites.spritebuilder.components;

import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.improvedsprites.interfaces.ISpriteUpdater;
import vooga.sprites.spritebuilder.components.basic.TargetC;

public class PongTargetC extends TargetC implements ISpriteUpdater
{
	private double AI_speed = 3.3;

    @Override
    public void update (Sprite s, long elapsedTime)
    {
        //s.setLocation(s.getX(), myTarget.getY());
    	
    	
    	double middleOfScreen = s.getBackground().getHeight()/2;
    	
    	if(myTarget.getX() > 320)
    		moveTowardsValue(s, myTarget.getY());
    	else 
			moveTowardsValue(s, middleOfScreen);
    		
    	
    }    

	private void moveTowardsValue(Sprite s, double desiredLocation) {
		double diff = ( desiredLocation - s.getCenterY());
		if(diff >= 0)
    		s.setY(s.getY()+Math.min(AI_speed,diff));
    	else
    		s.setY(s.getY()+Math.max(-AI_speed,diff));
		
	}

}
