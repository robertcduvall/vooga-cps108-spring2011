package games.jezzball;

import games.jezzball.sprite.Tile;

import java.awt.Dimension;

import vooga.core.VoogaGame;

public class Jezzball extends VoogaGame{

    Tile[][] tileArrayMap = new Tile[40][30];
    
    @Override
    public void updatePlayField(long elapsedTime) {
        // Depricated?
        
    }

    @Override
    public void initResources() {
        initEngine();
        
    }
    
    public static void main(String args[]){
        launchGame(new Jezzball(), new Dimension(400,400), true);
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
    
    //public void 

}
