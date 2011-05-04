package games.starshipdefender.gameobjects;

import vooga.core.VoogaGame;
import vooga.sprites.improvedsprites.Sprite;

public class Torpedo extends Sprite
{
    private VoogaGame myGame;
    private int xpos;
    private int ypos;
    
    public Torpedo(VoogaGame game)
    {
        myGame = game;
    }
    
    public Torpedo(VoogaGame game, int x, int y)
    {
        this(game);
        xpos = x;
        ypos = y;
    }

    
    
}
