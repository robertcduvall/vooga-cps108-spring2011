package vooga.core.event.examples.bubblefishbob.bonuses;
import vooga.core.event.examples.bubblefishbob.bubbleFish.ProgBob;
import vooga.core.event.examples.bubblefishbob.util.ImageProcessor;

import com.golden.gamedev.object.Sprite;


public class BonusTorpedo extends Bonus
{
    public BonusTorpedo (ProgBob progBob)
    {
    	super(progBob, "Rewind", new Sprite(ImageProcessor.loadImage("vooga.core.event.examples.bubblefishbob.resources/bon_torpedo.png")));
    }

    @Override
    public void applyBonus ()
    {
        progBobContext.setTorpedo(true);
    }
}
