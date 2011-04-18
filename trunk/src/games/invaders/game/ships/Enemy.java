package games.invaders.game.ships;

import com.golden.gamedev.GameObject;

public class Enemy extends Ship {
	public Enemy(GameObject gameObject, String shipImage) {
		super(gameObject.getImage(shipImage));
	}
}
