package games.bigfish;

import java.util.Collection;

import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.core.event.IEventHandler;
import vooga.levels.AbstractLevel;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

public class Level extends AbstractLevel {

	public Level(Collection<SpriteGroup<Sprite>> players, VoogaGame game) {
		super(players, game);
		
		game.addEveryTurnEvent("moveFishes",new IEventHandler(){

			@Override
			public void handleEvent(Object o) {
				moveEnemyFishes();
			}
		});
	}

	protected void moveEnemyFishes() {
		for(Sprite f: this.getSpriteGroup("enemyFish").getSprites()){
			f.setHorizontalSpeed(.08);
		}
	}

	@Override
	public void loadLevel() {
		this.
        addAllSpritesFromPool();
        addBackground();
        
	}

}
