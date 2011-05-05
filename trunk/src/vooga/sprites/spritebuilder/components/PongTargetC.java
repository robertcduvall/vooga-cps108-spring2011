package vooga.sprites.spritebuilder.components;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.improvedsprites.interfaces.ISpriteUpdater;
import vooga.sprites.spritebuilder.components.basic.TargetC;
import vooga.sprites.spritegroups.SpriteGroup;

public class PongTargetC extends TargetC implements ISpriteUpdater
{
	private double AI_speed = 3.5;
	private SpriteGroup<? extends Sprite> group;

    @Override
    public void update (Sprite s, long elapsedTime)
    {
        //s.setLocation(s.getX(), myTarget.getY());
    	
    	Sprite closestPowerup = findClosestPowerup(s);
    	double middleOfScreen = s.getBackground().getHeight()/2;
    	
    	if(closestPowerup != null)
    		calculateMovement(closestPowerup, s);
    	else 
    	{
    	if(myTarget.getX() > 320)
    		moveTowardsValue(s, myTarget.getY());
    	else 
			moveTowardsValue(s, middleOfScreen);
    	}
    		
    	
    }    

	private void calculateMovement(Sprite closestPowerup, Sprite s) {
		double distance = closestPowerup.getCenterY() - myTarget.getY();
		if(Math.abs(distance) < s.getHeight()/2)
			moveTowardsValue(s, closestPowerup.getCenterY()+Math.signum(distance)*s.getHeight());
		else
			moveTowardsValue(s, myTarget.getY());			
		
	}

	private Sprite findClosestPowerup(Sprite thisSprite) {
		Map<Double, Sprite> distMap = new HashMap<Double, Sprite>();
		if(group!=null){
			Double lowest = Double.MAX_VALUE;
			Sprite closest = null;
			for(Sprite s: group.getSprites()) {
				double distance = getDistance(s, thisSprite);
				//System.out.println("asdf"+distance);
				if(distance < lowest && s.getHorizontalSpeed()>1)
					closest = s;
			}
			return closest;
		}
		return null;
		
	}

	private double getDistance(Sprite s, Sprite thisSprite) {
		Point2D.Double sPosition = new Point2D.Double(s.getCenterX(), s.getCenterY());
		Point2D.Double thisPosition = new Point2D.Double(thisSprite.getCenterX(), thisSprite.getCenterY());
		return sPosition.distance(thisPosition);
	}

	private void moveTowardsValue(Sprite s, double desiredLocation) {
		double diff = ( desiredLocation - s.getCenterY());
		if(diff >= 0)
    		s.setY(s.getY()+Math.min(AI_speed,diff));
    	else
    		s.setY(s.getY()+Math.max(-AI_speed,diff));
		
	}
	
	public void addSpriteGroup(SpriteGroup group) {
		this.group = group;
	}

}
