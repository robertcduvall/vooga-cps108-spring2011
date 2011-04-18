package games.invaders.game.enemyFormations;

import games.invaders.game.ships.Enemy;

import java.util.ArrayList;
import java.util.List;

import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;

public class GridFormation extends Formation {
	private final Timer enemyMoveTimer = new Timer(1000); // Change direction
	// delay
	private final Timer enemyAttackTimer = new Timer(3000); // Attack Delay
	private final double[] moveSpeed = new double[] { 0.02, 0.02, 0.02, 0.02,
			0.02 };
	private final double[] moveAngle = new double[] { 180, 45, 0, 0, 0, 345 };

	private List<Sprite> units;

	public GridFormation(GameObject game, List<SpriteGroup> spriteGroups,
			int formationShipsWide, int formationShipsHigh) {
		units = new ArrayList<Sprite>();
		String[] lineColors = new String[] { "EneShipRed1.png",
				"EneShipRed2.png", "EneShipGrn1.png", "EneShipGrn2.png" };
		units = new ArrayList<Sprite>();
		double centerX = game.getWidth() / 2;
		double shipDistanceX = game.getWidth() * 2 / 3 / formationShipsWide;
		double centerY = game.getHeight() * 3 / 4;
		double shipDistanceY = game.getHeight() / 3 / formationShipsHigh;
		for (int i = 0; i < formationShipsHigh; i++) {
			for (int j = 0; j < formationShipsWide; j++) {
				int verticalIndex = Math.min(i, lineColors.length);
				Sprite enemy = new Enemy(game, lineColors[verticalIndex]);
				int x = (int) Math.round(centerX + (j - formationShipsWide / 2)
						* shipDistanceX);
				int y = (int) Math.round(centerY + (i - lineColors.length / 2)
						* shipDistanceY);
				enemy.setLocation(x, y);
				units.add(enemy);
			}
		}
		for (SpriteGroup spriteGroup : spriteGroups) {
			for (Sprite unit : units) {
				spriteGroup.add(unit);
			}
		}
	}

	public void setActive(boolean isActive) {
		for (Sprite unit : units) {
			unit.setActive(isActive);
		}
	}

	@Override
	public void update(long elapsedTime) {
		int index = 0;
		if (enemyMoveTimer.action(elapsedTime)) {
			for (Sprite unit : units) {
				unit.setMovement(moveSpeed[index], moveAngle[index]);
			}
			index++;
			if (index >= moveSpeed.length) {
				index = 0;
			}
			enemyMoveTimer.refresh();
		}
	}

	public int getNumberRemainingShips() {
		int unitsRemaining = 0;
		for (Sprite unit : units) {
			if (unit.isActive()) {
				unitsRemaining++;
			}
		}
		return unitsRemaining;
	}

}
