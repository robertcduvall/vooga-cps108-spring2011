package games.patterson_game;

import games.patterson_game.refactoredVooga.core.VoogaGame;
import games.patterson_game.refactoredVooga.core.event.EventManager;
import games.patterson_game.refactoredVooga.core.event.IEventHandler;
import games.patterson_game.refactoredVooga.levelsRefactored.AbstractLevel;
import games.patterson_game.refactoredVooga.resources.bundle.Bundle;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.Sprite;
import games.patterson_game.refactoredVooga.sprites.spritegroups.SpriteGroup;
import java.util.Collection;


public class AvoiderLevel extends AbstractLevel
{
    private ObstacleReleaseZone myObstacleReleaseZone;
    private EventManager myEventManager;
    private Bundle myBundle;

    public AvoiderLevel (Collection<SpriteGroup<Sprite>> players, VoogaGame game)
    {
        super(players, game);
        myBundle = AvoiderGame.getBundle();
        myEventManager = myGame.getEventManager();
        createEventHandler();
        addPeriodicTimer();
    }


    @Override
    public void loadLevel ()
    {
        myObstacleReleaseZone = new ObstacleReleaseZone(myGame);
        myObstacleReleaseZone.setObstacleSpeedMultipler(myId + 1);
        addAllSpritesFromPool();
        addBackground();
        addMusic();
        myGame.fireEvent("DisplayThenResetText","DisplayThenResetText",
                         myBundle.getString("level_message_pt_1") + myId + myBundle.getString("level_message_pt_2"));
    }


    public void createEventHandler ()
    {
        myEventManager.registerEventHandler("ReleaseObstacle", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                myObstacleReleaseZone.releaseFloatingObject();
            }
        });
    }


    public void addPeriodicTimer()
    {
        myEventManager.addPeriodicTimer("ReleaseObstacle", (long) myBundle.getInteger("obstacle_release_interval"), "ReleaseObstacle");
    }
}
