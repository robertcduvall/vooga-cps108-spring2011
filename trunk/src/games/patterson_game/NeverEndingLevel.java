package games.patterson_game;

import games.patterson_game.refactoredVooga.core.VoogaGame;
import games.patterson_game.refactoredVooga.core.event.EventManager;
import games.patterson_game.refactoredVooga.core.event.IEventHandler;
import games.patterson_game.refactoredVooga.levelsRefactored.AbstractLevel;
import games.patterson_game.refactoredVooga.resources.bundle.Bundle;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.Sprite;
import games.patterson_game.refactoredVooga.sprites.spritegroups.SpriteGroup;
import java.util.Collection;

/**
 * The last level of the game which goes on forever and has the obstacles come at
 * increasingly faster speeds
 * 
 * @author Andrew
 */
public class NeverEndingLevel extends AbstractLevel
{
    private ObstacleReleaseZone myObstacleReleaseZone;
    private EventManager myEventManager;
    private Bundle myBundle;

    public NeverEndingLevel (Collection<SpriteGroup<Sprite>> players, VoogaGame game)
    {
        super(players, game);
        myBundle = AvoiderGame.getBundle();
        myEventManager = myGame.getEventManager();
        createEventHandlers();
        addPeriodicTimers();
    }


    @Override
    public void loadLevel ()
    {
        myObstacleReleaseZone = new ObstacleReleaseZone(myGame);
        addAllSpritesFromPool();
        addBackground();
        myGame.fireEvent("DisplayThenResetText", "DisplayThenResetText", myBundle.getString("bonus_level_message"));

    }


    public void createEventHandlers ()
    {
        myEventManager.registerEventHandler("ReleaseObstacle", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                myObstacleReleaseZone.releaseFloatingObject();
            }
        });
        myEventManager.registerEventHandler("SpeedUpObstacles", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                myObstacleReleaseZone.setObstacleSpeedMultipler(myObstacleReleaseZone.getObstacleSpeedMultipler() *
                                                                myBundle.getDouble("obstacle_speed_increasor"));
            }
        });
    }


    public void addPeriodicTimers()
    {
        myEventManager.addPeriodicTimer("ReleaseObstacle", (long) myBundle.getInteger("obstacle_release_interval"), "ReleaseObstacle");
        myEventManager.addPeriodicTimer("SpeedUpObstacles", (long) myBundle.getInteger("speed_increasor_interval"), "SpeedUpObstacles");
    }
}
