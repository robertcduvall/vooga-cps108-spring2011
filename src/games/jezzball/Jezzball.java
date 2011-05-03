package games.jezzball;

import games.jezzball.sprite.Cursor;
import games.jezzball.sprite.Tile;

import java.awt.Dimension;
import java.awt.Point;

import vooga.core.VoogaGame;
import vooga.resources.images.ImageLoader;
import vooga.sprites.spritegroups.SpriteGroup;

public class Jezzball extends VoogaGame{
    public static ImageLoader imageLoader;
    private static Point TOP_LEFT_CORNER = new Point(100,100);
    
    
    Tile[][] tileArrayMap = new Tile[40][30];
    
    @Override
    public void updatePlayField(long elapsedTime) {
        // Depricated?
        
    }

    @Override
    public void initResources() {
        initEngine();
        imageLoader = getImageLoader();
        addPlayer();
        getLevelManager().loadLevel(0);
        
        registerEventHandler(eventName, eventHandler)
        
    }
    
    public static void main(String args[]){
        launchGame(new Jezzball(), new Dimension(400,400), false);
    }
    
    //write event to kill timer
    //write event to add timer
    //write event to expand
    //write event to stop expand
    //write event to check goal
    //write event to
    
    public void checkWalledOffArea(){
        //TODO fill in walled off area
    }
    
    public void stopWallingOff(){
        
    }
    
    public void startWallingOff(){
        
    }
    
    public void loseLife(){
        
    }
    
    public void addPlayer(){
        Cursor c = new Cursor(imageLoader.getImage("cursor"),this, 0,0);
        getLevelManager().addPlayer(new SpriteGroup<Cursor>("cursor", c));
        System.out.println("player added!");
    }
    
    //public void 
    public void createWall(){
        
    }
    
    

}
