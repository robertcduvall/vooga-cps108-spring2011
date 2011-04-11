package vooga.core.event.examples.bubblefishbob.bubbleFish;
import java.awt.Color;
import java.awt.Graphics2D;


public class PartString extends Part
{
    String text;

    public PartString (ProgBob progBob, double x, double y, String text, double speed)
    {
        super(progBob, x, y, null, speed);
        this.text = text;
    }

    @Override
    public void draw (Graphics2D context)
    {
        if (life < 0.25)
        {
            context.setFont(progBob.fnts[(int)(4 * life * 10)]);
        }
        else
        {
            context.setFont(progBob.fnts[9]);
        }
        context.setColor(Color.WHITE);
        context.drawString(text, (int)x, (int)y);
    }
}
