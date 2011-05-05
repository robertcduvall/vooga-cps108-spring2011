package games.frogger;

import games.frogger.sprites.CarA;

import java.util.Collection;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.levels.AbstractLevel;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

public class Level extends AbstractLevel {

	private VoogaGame game;
	
	public Level(Collection<SpriteGroup<Sprite>> players, VoogaGame game) {
		super(players, game);
		
		this.game=game;
		
		game.registerEventHandler("Vechicle.Destroy", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                //create new vehicle - type = string o hopefully
            	addArchetypeSprite("carA", 400, 400);
            }            
        });
		
		game.registerEventHandler("Input.User.Left", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                System.out.println("pressed left");
            }            
        });
		
	}

	@Override
	public void loadLevel() {
		addBackground();
		addAllSpritesFromPool();
		
		this.addSpriteToGroup("frogger", new CarA(game.getImage("src/games/frogger/resources/carA.png"),400, 100));
	}
	
	

}
