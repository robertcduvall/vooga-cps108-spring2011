package games.jezzball;

import games.jezzball.sprite.Cursor;
import games.jezzball.sprite.Tile;

import java.awt.Dimension;
import java.awt.Point;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.resources.images.ImageLoader;
import vooga.sprites.spritegroups.SpriteGroup;

/**
 * Jezzball! Create walls to compartmentalize of grid.
 * Cover 75% to win
 * 
 * @author KevinWang
 *
 */
public class Jezzball extends VoogaGame{
    
    {distribute = true;}
    
    public static ImageLoader imageLoader;
    
    @Override
    public void updatePlayField(long elapsedTime) {}

    @Override
    public void initResources() {
        imageLoader = getImageLoader();
        Cursor c = new Cursor(imageLoader.getImage("vertical_arrow"),this, 140,140, 20);
        getLevelManager().addPlayer(new SpriteGroup<Cursor>("cursor", c));
        getLevelManager().loadLevel(0);
    }

    public static void main(String args[]){
        launchGame(new Jezzball(), new Dimension(600,600), false);
    }

}
