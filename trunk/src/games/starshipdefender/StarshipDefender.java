package games.starshipdefender;


import games.starshipdefender.gameobjects.TargetingImage;

import java.awt.Dimension;

import vooga.core.VoogaGame;
import vooga.sprites.spritegroups.SpriteGroup;
import vooga.core.event.*;
import vooga.resources.images.*;


public class StarshipDefender extends VoogaGame
{
    public static EventManager EventManager;
    public static ImageLoader ImageLoader;
    
    
    @Override
    public void updatePlayField(long elapsedTime)
    {
        
    }

    @Override
    public void initResources()
    {
        EventManager = this.getEventManager();
        ImageLoader = this.getImageLoader();
        
        TargetingImage target = new TargetingImage(this);
        this.getLevelManager().addPlayer(new SpriteGroup<TargetingImage>("target", target));
        
        this.getLevelManager().loadLevel(0);
        
    }

    public static void main(String[] args)
    {
        launchGame(new StarshipDefender(), new Dimension(640,480), false);
    }
}
