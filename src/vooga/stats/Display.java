package vooga.stats;
import java.util.Map;
import com.golden.gamedev.object.Sprite;

/**
 * Generic abstract StatsLayer class to be extended by any sub-class that
 * uses a Stat object.
 * 
 * @author Chao Chen
 */

public abstract class Display extends Sprite 
{
    private static final long serialVersionUID = 1L;
    //private Stat myStat;	
	
	protected void setLocation(Map<String, String> map)
	{
		String xStr = map.get("x");
		String yStr = map.get("y");
		int x, y;
		x = xStr == null ? 0 : Integer.parseInt(xStr);
		y = yStr == null ? 0 : Integer.parseInt(yStr);
		setLocation(x, y);
	}
}