package games.patterson_game;

import games.patterson_game.refactoredVooga.core.VoogaGame;
import games.patterson_game.refactoredVooga.resources.bundle.Bundle;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;
import java.awt.Dimension;
import com.golden.gamedev.object.background.ImageBackground;

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
        launchGame(AvoiderGame.getInstance(), new Dimension(1000, 700), false);
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
    
    public void lose()
    {
        fireEvent("ShowLoseScreen", "ShowLoseScreen",
                  new ImageBackground(getImage(myBundle.getString("lose_background"))));
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
    
    { distribute = true; }    

}
