package vooga.core.event.examples.bubblefishbob.bonuses;

import vooga.core.event.examples.bubblefishbob.bubbleFish.ProgBob;
import vooga.core.event.examples.bubblefishbob.util.ImageProcessor;


import com.golden.gamedev.object.Sprite;


public class BonusPause extends Bonus
{
    public BonusPause (ProgBob progBob)
    {
        super(progBob, "Pause", new Sprite(ImageProcessor.loadImage("vooga.core.event.examples.bubblefishbob.resources/bonus_pause.png")));
    }

    @Override
    public void applyBonus ()
    {
        if (progBobContext.getTime_paused() > 0)
        {
            progBobContext.setTime_paused(5);
        }
        else
        {
        	progBobContext.setTime_paused(6);
        }
    }
}
