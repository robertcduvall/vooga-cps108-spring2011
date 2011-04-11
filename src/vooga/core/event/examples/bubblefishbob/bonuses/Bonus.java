package vooga.core.event.examples.bubblefishbob.bonuses;

import vooga.core.event.examples.bubblefishbob.bubbleFish.ProgBob;

import com.golden.gamedev.object.Sprite;


public abstract class Bonus
{
    String name;
    Sprite icon;
    ProgBob progBobContext;
    Bonus next;

    public Bonus (ProgBob progBob, String name, Sprite icon)
    {
        this.progBobContext = progBob;
        this.name = name;
        this.icon = icon;
    }

    public abstract void applyBonus ();
    
    public Sprite getIcon()
    {
    	return icon;
    }
    
    public String getName()
    {
    	return name;
    }
    
}
