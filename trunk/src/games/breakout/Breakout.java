package games.breakout;

import games.breakout.sprites.Paddle;
import java.awt.Dimension;
import vooga.core.VoogaGame;
import vooga.sprites.spritegroups.SpriteGroup;

public class Breakout extends VoogaGame
{
    {distribute = true;}
    
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
        Paddle paddle = new Paddle(this, getWidth()/2, getHeight());
        getLevelManager().addPlayer(new SpriteGroup<Paddle>("paddle", paddle));
        getLevelManager().loadLevel(0);
    }
}
