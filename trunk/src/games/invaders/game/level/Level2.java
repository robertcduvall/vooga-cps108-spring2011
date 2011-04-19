package games.invaders.game.level;

import games.invaders.game.InvadersGame;
import games.invaders.game.enemyFormations.GridFormation;

import java.util.ArrayList;
import java.util.List;

import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;

public class Level2 extends Level {

	public Level2(InvadersGame game) {
		this.game = game;
	}

	@Override
	public void loadLevel() {
		game.hero.setX(50);
		game.hero.setHitpoints(2);
		List<SpriteGroup> enemyGroup = new ArrayList<SpriteGroup>();
		enemyGroup.add(game.enemyGroup);
		enemyFormation = new GridFormation(game, enemyGroup, 30, 5);
		enemyFormation.setActive(true);
		game.discoTimer = new Timer((int) Math.round((1000.0 * 60) / 114 / 4));
		game.bsSound.play("resources/audio/level2.wav", 0);
	}

	@Override
	public Level loadNext() {
		Level levelNext = new Level1(game);
		enemyFormation.setActive(false);
		levelNext.loadLevel();
		return levelNext;
	}
}
