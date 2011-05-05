package games.frogger;

import games.frogger.sprites.Frog;
import games.frogger.sprites.Vehicle;
import games.patterson_game.refactoredVooga.resources.bundle.Bundle;

import java.util.Collection;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.levels.AbstractLevel;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.basic.SpriteVelocityC;
import vooga.sprites.spritegroups.SpriteGroup;

public class Level extends AbstractLevel {

	private VoogaGame myGame;
	
	public Level(Collection<SpriteGroup<Sprite>> players, VoogaGame game) {
		super(players, game);
		
		this.myGame = game;
		
		myGame.registerEventHandler("Vehicle.Destroy", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
            	// Create new vehicle to replace destroyed one
            	addArchetypeSprite((String) o, getCoordinate(o, 0), getCoordinate(o, 1));
            }            
        });
		
		myGame.registerEventHandler("Game.NextFrog", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
            	addSpriteToGroup("frog", new Frog(myGame.getImageLoader().getImage("frog"), myGame.getWidth()/2, Frogger.FROG_Y_START, myGame));
            }            
        });
		
	}

	@Override
	public void loadLevel() {
		addBackground();
		addAllSpritesFromPool();
	}
	
	/**
	 * Returns xy coordinate for new sprite generation 
	 * 
	 * @param o object from event
	 * @param i location 
	 * @return
	 */
	private int getCoordinate(Object o, int i) {
		return getBundle().getIntegerArray((String) o)[i];
	}
	
	

}
