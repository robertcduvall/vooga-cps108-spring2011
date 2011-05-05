package games.fishin;

import java.awt.Graphics2D;
import java.util.Collection;
import java.util.Random;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.levels.AbstractLevel;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;
import vooga.stats.DisplayTracker;
import vooga.stats.NumDisplayCreator;

public class Level extends AbstractLevel
{
	
    public VoogaGame game;
    private NumDisplayCreator myCreator;
    private DisplayTracker myTracker;
    private long delay;
    private Random generator;
    
    public Level (Collection<SpriteGroup<Sprite>> players, VoogaGame game)
    {
        super(players, game);
        this.game = game;
        generator = new Random();
        delay = 0;  
        
        myCreator = new NumDisplayCreator("src/games/fishin/resources/statsNumDisplay.xml");
        myTracker = myCreator.getTracker();
        bindEvents();
        
        game.addPeriodicTimer("OceanTimer", 90, "MoveOcean");
        game.addPeriodicTimer("BaseFishTimer", 2001, "BaseFishBite");   //Base fish delay is 2 s
    }

    @Override
    public void loadLevel ()
    {
        addAllSpritesFromPool();
        addBackground();
    }
    
    @Override
    public void render(Graphics2D g){
        super.render(g);
        g.drawString(myTracker.getStat("fishcaught").toString(), 100, 100);
    }
    
    private void bindEvents() {
        game.registerEventHandler("FishCaught", new IEventHandler()
        {
            @Override
            public void handleEvent(Object o)
            {
                myTracker.getStat("fishcaught").update();
            }
        });
        game.registerEventHandler("BaseFishBite", new IEventHandler()
        {
            @Override
            public void handleEvent(Object o)
            {
                delay = generator.nextInt(4001); //Delay between bites is 2-6 seconds. (2 to 2+4 s)
                game.addTimer("FishRandomTimer", delay, "FishBiteStart");
                game.addTimer("FishRandomEnd", delay+500, "FishBiteDone");      //You have 0.5 s to land fish
            }
        });
    }
    
   
}
