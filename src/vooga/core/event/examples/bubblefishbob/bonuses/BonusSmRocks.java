
package vooga.core.event.examples.bubblefishbob.bonuses;
import vooga.core.event.examples.bubblefishbob.bubbleFish.ProgBob;
import vooga.core.event.examples.bubblefishbob.util.ImageProcessor;


import com.golden.gamedev.object.Sprite;


public class BonusSmRocks extends Bonus
{
    public BonusSmRocks (ProgBob progBob)
    {
    	   super(progBob, "Small Rocks", new Sprite(ImageProcessor.loadImage("vooga.core.event.examples.bubblefishbob.resources/bon_smrocks.png")));
    }

    @Override
    public void applyBonus ()
    {
        progBobContext.setSmrocks(6);
    }
}
