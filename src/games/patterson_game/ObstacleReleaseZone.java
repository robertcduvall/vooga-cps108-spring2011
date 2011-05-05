package games.patterson_game;

import games.patterson_game.refactoredVooga.core.VoogaGame;
import games.patterson_game.refactoredVooga.core.event.IEventHandler;
import games.patterson_game.refactoredVooga.levelsRefactored.LevelManager;
import games.patterson_game.refactoredVooga.resources.bundle.Bundle;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.Sprite;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Controls the release of all AbstractFloating objects (obstacles, powerups and explosives)
 * 
 * @author Andrew
 */
public class ObstacleReleaseZone
{
    private Random myGenerator;
    private VoogaGame myGame;
    private boolean[] myReleaseZone;    // Represents availability
    private LevelManager myLevelManager;
    private ArrayList<String> myOptionList; // A list representing the probability of getting a specific floating object
    private double myObstacleSpeedMultipler;
    private Bundle myBundle;


    public ObstacleReleaseZone (VoogaGame game)
    {
        myGame = game;
        myBundle = AvoiderGame.getBundle();
        myObstacleSpeedMultipler = 1;
        myReleaseZone = new boolean[myGame.getHeight() / myBundle.getInteger("obstacle_height")];
        for(int i = 0; i < myReleaseZone.length; i ++)
            myReleaseZone[i] = true;
        myGenerator = new Random();
        myLevelManager = myGame.getLevelManager();
        createEventHandler();
        createOptionList();
    }


    /**
     * Creates the option list, where the more times a specific type of floating
     * object is listed, the higher chance it has to be released
     */
    private void createOptionList ()
    {
        myOptionList = new ArrayList<String>();
        Map<String, Integer> probabilityMap = new HashMap<String, Integer>();
        probabilityMap.put("obstacle", myBundle.getInteger("obstacle_frequency"));
        probabilityMap.put("explosive", myBundle.getInteger("explosive_frequency"));
        probabilityMap.put("reversePowerup", myBundle.getInteger("reversePowerup_frequency"));
        probabilityMap.put("speedUpPowerup", myBundle.getInteger("speedUpPowerup_frequency"));
        probabilityMap.put("weaponChangePowerup", myBundle.getInteger("weaponChangePowerup_frequency"));
        for(String currentOption : probabilityMap.keySet())
        {
            int numOfOccurences = probabilityMap.get(currentOption);
            for(int i = 0; i < numOfOccurences; i ++)
            {
                myOptionList.add(currentOption);
            }
        }
    }


    /**
     * Releases one floating object onto the playingfield. The floating object
     * that is released is determined by the probabilities specified in the option list
     */
    public void releaseFloatingObject ()
    {
        int bayToOpen = myGenerator.nextInt(myReleaseZone.length);
       // if(myReleaseZone[bayToOpen] == true)
        {
            String randomOption = myOptionList.get(myGenerator.nextInt(myOptionList.size()));
            Sprite newObstacle = myLevelManager.addArchetypeSprite(randomOption, myGame.getWidth(), bayToOpen * myBundle.getInteger("obstacle_height"));
            newObstacle.setSpeed(myObstacleSpeedMultipler * myBundle.getDouble("default_obstacle_speed"), 0);
            myReleaseZone[bayToOpen] = false;
            myGame.addTimer("OpenReleaseZone", myBundle.getInteger("open_release_zone_interval"), "OpenReleaseZone", bayToOpen);
        }
    }

    /**
     * Creates all the event handlers for the obstacle release zone
     */
    public void createEventHandler ()
    {
        myGame.getEventManager().registerEventHandler("OpenReleaseZone", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                myReleaseZone[(Integer) o] = true;
            }
        });
    }
    
    /**
     * Sets the obstacle speed multiplier (which is factored into how fast obstacles move)
     * @param multipler
     */
    public void setObstacleSpeedMultipler(double multipler)
    {
        myObstacleSpeedMultipler = multipler;
    }
    
    /**
     * Returns the obstacle speed multiplier
     */
    public double getObstacleSpeedMultipler()
    {
        return myObstacleSpeedMultipler;
    }
}
