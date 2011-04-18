package vooga.stats;

import java.awt.Dimension;
import java.awt.Graphics2D;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.Game;

public class StatsMain extends Game
{
    private PlayField myPlayfield;
    
    @Override
    public void initResources() 
    {
        DisplayTracker tracker = DisplayCreator.createOverlays("src/vooga/stats/resources/statsDisplay.xml");        
        myPlayfield = new PlayField();       
        myPlayfield.addGroup(tracker.getStatsGroup("DisplayGroup"));
    }
    
    @Override
    public void update(long elapsedTime) 
    {
        myPlayfield.update(elapsedTime);
    }
    
    @Override
    public void render(Graphics2D g) 
    {
        myPlayfield.render(g);
    }
    
    public static void main(String[] args)
    {
        GameLoader game = new GameLoader();
        game.setup(new StatsMain(), new Dimension(640,480), true);
        game.start();
    }
}