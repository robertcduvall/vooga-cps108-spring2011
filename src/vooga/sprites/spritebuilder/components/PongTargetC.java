package vooga.sprites.spritebuilder.components;

import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.improvedsprites.interfaces.ISpriteUpdater;
import vooga.sprites.spritebuilder.components.basic.TargetC;

public class PongTargetC extends TargetC implements ISpriteUpdater
{

    @Override
    public void update (Sprite s, long elapsedTime)
    {
        //s.setLocation(s.getX(), myTarget.getY());
    	
    	int diff = (int)( myTarget.getY() - s.getCenterY());
    	if(myTarget.getX() > 50)
    		moveTowards(s, diff);
    	
    }

	private void moveTowards(Sprite s, int diff) {
		if(diff >= 0)
    		s.setY(s.getY()+Math.min(4,diff));
    	else
    		s.setY(s.getY()+Math.max(-4,diff));
		
	}

}
