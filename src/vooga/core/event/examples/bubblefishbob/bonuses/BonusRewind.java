package vooga.core.event.examples.bubblefishbob.bonuses;

import vooga.core.event.examples.bubblefishbob.bubbleFish.ProgBob;
import vooga.core.event.examples.bubblefishbob.util.ImageProcessor;

import com.golden.gamedev.object.Sprite;


public class BonusRewind extends Bonus
{
    public BonusRewind (ProgBob progBob)
    {
    	   super(progBob, "Rewind", new Sprite(ImageProcessor.loadImage("vooga.core.event.examples.bubblefishbob.resources/bonus_rewind.png")));
    }

    @Override
    public void applyBonus ()
    {
        if (progBobContext.getTime_rewind() > 0)
        {
            progBobContext.setTime_rewind(3);
        }
        else
        {
        	progBobContext.setTime_rewind(4);
        }
    }
}
