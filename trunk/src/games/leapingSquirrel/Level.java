package games.leapingSquirrel;

import java.util.Collection;
import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.levels.AbstractLevel;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

public class Level extends AbstractLevel
{

    private VoogaGame lsGame;
    
    public Level (Collection<SpriteGroup<Sprite>> players, VoogaGame game)
    {
        super(players, game);
        this.lsGame = game;
      
        game.registerEventHandler("Platform.Destroyed", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                //create new platform in random column - type = string o hopefully
                //addArchetypeSprite("Platform", *randomColumn*, 0);
            }            
        });
        
        game.registerEventHandler("Input.User.Left", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                System.out.println("Moved Left");
                //TODO: Move squirrel left
            }            
        });
        
        game.registerEventHandler("Input.User.Right", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                System.out.println("Moved Right");
                //TODO: Move squirrel right
            }            
        });
        
        game.registerEventHandler("Input.User.Space", new IEventHandler()
        {
            @Override
            public void handleEvent(Object o)
            {
                System.out.println("Jumped");
                //TODO: Implement jump
            }
        });
    }

    @Override
    public void loadLevel ()
    {
        // TODO Auto-generated method stub
        
    }

}
