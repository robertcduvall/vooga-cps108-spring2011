package games.starshipdefender;

import games.starshipdefender.gameobjects.PlayerShip;

import java.awt.Dimension;

import vooga.core.VoogaGame;
import vooga.sprites.spritegroups.SpriteGroup;

public class StarshipDefender extends VoogaGame
{
//    public static EventManager EventManager;
//    public static ImageLoader ImageLoader;
    
    
    @Override
    public void updatePlayField(long elapsedTime)
    {
        
    }

    @Override
    public void initResources()
    {
//        EventManager = this.getEventManager();
//        ImageLoader = this.getImageLoader();
        
        PlayerShip ship = new PlayerShip(this);
        this.getLevelManager().addPlayer(new SpriteGroup<PlayerShip>("playership", ship));
        
        this.getLevelManager().loadLevel(0);
        
    }

    public static void main(String[] args)
    {
        launchGame(new StarshipDefender(), new Dimension(640,480), false);
    }
}
