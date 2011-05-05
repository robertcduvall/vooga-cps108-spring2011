package games.keen.levels;

import java.util.Collection;

import vooga.core.VoogaGame;
import vooga.levels.AbstractLevel;
import vooga.physics.forceGenerator.MassProportionalForceGenerator;
import vooga.physics.util.Force;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

public class Level extends AbstractLevel {
	
	public Level(Collection<SpriteGroup<Sprite>> players, VoogaGame game) {
		super(players, game);
	}

	@Override
	public void loadLevel() {
		double grav = super.getBundle().getDouble("gravity");
		super.getPhysics().addGlobalForce(new MassProportionalForceGenerator(new Force(0, grav)), true);
		super.addAllSpritesFromPool();
		super.addBackground();
	}
}