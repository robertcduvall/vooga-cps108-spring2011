package games.jumpman;

import games.jumpman.sprites.Player;
import games.jumpman.sprites.Surface;
import games.patterson_game.refactoredVooga.resources.bundle.Bundle;

import java.util.Collection;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.levels.AbstractLevel;
import vooga.physics.forceGenerator.MassProportionalForceGenerator;
import vooga.physics.util.Force;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.basic.SpriteVelocityC;
import vooga.sprites.spritegroups.SpriteGroup;

public class Level extends AbstractLevel {

	private VoogaGame myGame;
	private double mySpeed=.8;
	private boolean isComplete=false;
	
	public Level(Collection<SpriteGroup<Sprite>> players, VoogaGame game) {
		super(players, game);
		
		this.myGame = game;
		
		
		myGame.registerEventHandler("Game.SpawnPlayer", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
            	addSpriteToGroup("player", new Player(myGame.getImageLoader().getImage("player"), JumpMan.PLAYER_X, JumpMan.PLAYER_Y_START, myGame));
            }            
        });
		
		myGame.registerEventHandler("Game.LevelComplete", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
            	isComplete=true;
            }
        });
	}

	@Override
	public void loadLevel() {
		double grav = -.0005;
		super.getPhysics().addGlobalForce(new MassProportionalForceGenerator(new Force(0, grav)), true);
		addBackground();
		addAllSpritesFromPool();
	}
	
	@Override
	public void update(long elapsedTime){
		super.update(elapsedTime);
		for (Sprite s: getSpriteGroup("surfaces").getSprites()){
			s.move(mySpeed*-1, 0);
		}
		for (Sprite s: getSpriteGroup("finish").getSprites()){
//			System.out.println("Moving "+s.toString());
			s.move(mySpeed*-1, 0);
		}
		for (Sprite s: getSpriteGroup("obstacles").getSprites()){
			s.move(mySpeed*-1, 0);
		}
		mySpeed+=.001;
	}

	public boolean isComplete() {
		return isComplete;
	}

}
