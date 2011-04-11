package vooga.core.event.examples.bubblefishbob.bubbleFish;
import java.awt.Graphics2D;
import com.golden.gamedev.object.Sprite;


public class PartBub extends Part
{
    public PartBub (ProgBob progBob, double x, double y, Sprite icon, double speed)
    {
        super(progBob, x, y, icon, speed);
    }

    @Override
    public void draw (Graphics2D context)
    {
        icon = progBob.bm_part_bub[(int)((1 - life) * 4)];
        super.draw(context);
    }
}
