package games.jezzball;

import games.jezzball.sprite.Cursor;
import games.jezzball.sprite.Tile;

import java.awt.Dimension;
import java.awt.Point;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.resources.images.ImageLoader;
import vooga.sprites.spritegroups.SpriteGroup;

public class Jezzball extends VoogaGame{
    public static ImageLoader imageLoader;
    
    
    
    
    
    @Override
    public void updatePlayField(long elapsedTime) {
        // Depricated?
        
    }

    @Override
    public void initResources() {
        //initEngine();
        imageLoader = getImageLoader();
        Cursor c = new Cursor(imageLoader.getImage("cursor"),this, 140,140);
        getLevelManager().addPlayer(new SpriteGroup<Cursor>("cursor", c));
        getLevelManager().loadLevel(0);
        
    }

    
    public static void main(String args[]){
        launchGame(new Jezzball(), new Dimension(400,400), false);
    }

}
