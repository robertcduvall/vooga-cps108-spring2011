package vooga.core.event.examples.bubblefishbob.bubbleFish;
import java.awt.Graphics2D;
import com.golden.gamedev.object.Sprite;


public class Part
{
    double x;
    double y;
    double vx;
    double vy;
    Sprite icon;
    double life;
    double speed;
    ProgBob progBob;
    Part next;

    public Part (ProgBob progBob, double x, double y, Sprite icon, double speed)
    {
        this.progBob = progBob;
        this.x = x;
        this.y = y;
        this.icon = icon;
        this.speed = speed;
        life = 0.99;
    }

    public void draw (Graphics2D context)
    {
        icon.render(context, (int)x, (int)y);
    }
}
