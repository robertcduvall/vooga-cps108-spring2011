package vooga.stats;
import java.util.Map;
import com.golden.gamedev.object.Sprite;


/**
 * Generic abstract StatsLayer class to be extended by any sub-class that
 * uses a Stat object.
 * 
 * @author Chao Chen
 */

public abstract class StatsLayer extends Sprite {
    private static final long serialVersionUID = 1L;
    private Stat myStat;	
	
	protected void setLocation(Map<String, String> attributes){
		String xLocStr = attributes.get("xLoc");
		int xLoc;
		if(xLocStr == null){
			xLoc = 0;
		}else{
			xLoc = Integer.parseInt(xLocStr);
		}
		String yLocStr = attributes.get("yLoc");
		int yLoc;
		if(yLocStr == null){
			yLoc = 0;
		}else{
			yLoc = Integer.valueOf(yLocStr);
		}
		setLocation(xLoc, yLoc);
	}
}