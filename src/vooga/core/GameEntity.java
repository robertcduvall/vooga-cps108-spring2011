package vooga.core;

import java.util.ArrayList;
import vooga.sprites.improvedsprites.Sprite;

/**
 * @author Andrea Scripa
 */
public abstract class GameEntity
{
    private ArrayList<Sprite> mySprites = new ArrayList<Sprite>();
    
    public void addSprite(Sprite s)
    {
        mySprites.add(s);
    }
    
    public abstract String interpretCommand();
    
    public abstract void addEventListeners();
}
