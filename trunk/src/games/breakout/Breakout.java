package games.breakout;

import java.awt.Dimension;
import vooga.core.VoogaGame;

public class Breakout extends VoogaGame
{
    public static void main (String[] args)
    {
        launchGame(new Breakout(), new Dimension(640, 480), false);
    }

    @Override
    public void updatePlayField (long elapsedTime)
    {
        return;
    }

    @Override
    public void initResources ()
    {
        // TODO Auto-generated method stub
        
    }
}
