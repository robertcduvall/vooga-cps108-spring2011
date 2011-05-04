package games.bigfish;

import games.bigfish.collisions.PlayerHitsFish;

import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Random;

import com.golden.gamedev.util.ImageUtil;

import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.core.event.IEventHandler;
import vooga.levels.AbstractLevel;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

public class Level extends AbstractLevel {

	private VoogaGame myGame;
	
	public Level(Collection<SpriteGroup<Sprite>> players, VoogaGame game) {
		super(players, game);
		myGame = game;
		game.addPeriodicTimer("period1", 1000, "spawnFish");
        game.registerEventHandler("spawnFish", new IEventHandler(){
			@Override
			public void handleEvent(Object o) {
				spawnRandomFish();
			}
        });
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
	
	protected void spawnRandomFish() {
		Random gen = new Random();
		int size = gen.nextInt(7);
		BufferedImage fishImage = myGame.getImageLoader().getImage("fish"+size,Direction.EAST);
		FishSprite EnemyFish = new FishSprite(ImageUtil.flip(fishImage), -fishImage.getWidth(), gen.nextInt(480),size);
		this.getSpriteGroup("enemyFish").addSprites(EnemyFish);
	}

}
