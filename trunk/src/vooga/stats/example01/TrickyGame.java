package vooga.stats.example01;

import java.awt.Dimension;
import java.awt.Graphics2D;

import vooga.stats.DisplayTracker;
import vooga.stats.NumDisplayCreator;
import vooga.stats.NumStat;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;

public class TrickyGame extends Game{
	private NumDisplayCreator myCreator;
	private DisplayTracker myTracker;
	int x1 = 100;
	int x2 = 200;
	int y = 50;
	int old_y = y;
	
	public void initResources() {
        myCreator = new NumDisplayCreator("src/vooga/stats/resources/statsNumDisplay.xml");
        myTracker = myCreator.getTracker();
    }

    public void update(long elapsedTime) {
        if (click())
        {
        	((NumStat) myTracker.getStat("score")).update();
        	((NumStat) myTracker.getStat("health")).update();
        	old_y = y;
        	y += 20;
        }
        
    }

    public void render(Graphics2D g) {
    	if (y!=old_y)
    	{
    		g.drawString(myTracker.getStat("score").toString(), x1, y);
    		g.drawString(myTracker.getStat("health").toString(), x2, y);
    	}
    }
}
