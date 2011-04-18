package games.invaders.game.level;

import games.invaders.game.InvadersGame;
import games.invaders.game.enemyFormations.GridFormation;

import java.util.ArrayList;
import java.util.List;

import com.golden.gamedev.object.SpriteGroup;

public class Level2 extends Level {

	public Level2(InvadersGame game) {
		this.game = game;
	}

	@Override
	public void loadLevel() {
		List<SpriteGroup> enemyGroup = new ArrayList<SpriteGroup>();
		enemyGroup.add(game.enemyGroup);
		enemyFormation = new GridFormation(game, enemyGroup, 4, 4);
		enemyFormation.setActive(true);
	}

	@Override
	public Level loadNext() {
		Level levelNext = new Level1(game);
		enemyFormation.setActive(false);
		levelNext.loadLevel();
		return levelNext;
	}
}
