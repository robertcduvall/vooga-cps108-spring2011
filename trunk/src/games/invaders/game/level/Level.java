package games.invaders.game.level;

import games.invaders.game.InvadersGame;
import games.invaders.game.enemyFormations.GridFormation;

abstract public class Level {
	protected InvadersGame game;
	protected GridFormation enemyFormation;
	private boolean levelCompleted = false;

	abstract public void loadLevel();

	public boolean isComplete() {
		if (levelCompleted) {
			return true;
		}
		boolean returnValue = enemyFormation.getNumberRemainingShips() < 1;
		if (returnValue) {
			levelCompleted = true;
			return true;
		}
		return false;
	}

	abstract public Level loadNext();

	/**
	 * @param levelHasBeenCompleted
	 *            the levelHasBeenCompleted to set
	 */
	public void setLevelCompleted(boolean levelHasBeenCompleted) {
		this.levelCompleted = levelHasBeenCompleted;
	}

}
