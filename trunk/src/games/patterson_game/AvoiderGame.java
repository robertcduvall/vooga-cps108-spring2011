package games.patterson_game;

import games.patterson_game.refactoredVooga.core.VoogaGame;
import games.patterson_game.refactoredVooga.resources.bundle.Bundle;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.Sprite;
import games.patterson_game.refactoredVooga.sprites.spritegroups.SpriteGroup;
import java.awt.Dimension;

/**
 * Avoider Game!
 * 
 * @author Andrew Patterson
 */
public class AvoiderGame extends VoogaGame
{
    private Ship myShip;
    private static VoogaGame instance;
    private static Bundle myBundle;
    
    public static void main (String[] args)
    {
        launchGame(AvoiderGame.getInstance(), new Dimension(1000, 500), false);
    }
    
    /**
     * Initializes the game (loads level 0) and adds the player's ship to the playingfield
     */
    @Override
    public void initResources ()
    {
        myBundle = getResourceManager().getBundle();
        myShip = new Ship(this);
        myLevelManager.addPlayer(new SpriteGroup<Sprite>(myBundle.getString("player_group_name"), myShip));
        myLevelManager.loadLevel(0);
    }
    
    /** This method is deprecated in the VoogaGame class */
    @Override
    public void updatePlayField (long elapsedTime)
    { return; }
    
    
    public static VoogaGame getInstance()
    { if (instance == null) instance = new AvoiderGame(); return instance; }
    
    public static Bundle getBundle()
    {
        return myBundle;
    }

}
