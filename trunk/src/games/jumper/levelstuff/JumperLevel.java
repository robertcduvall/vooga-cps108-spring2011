package games.jumper.levelstuff;

import java.util.Collection;

import vooga.core.VoogaGame;
import vooga.levels.AbstractLevel;
import vooga.physics.forceGenerator.MassProportionalForceGenerator;
import vooga.physics.util.Force;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

public class JumperLevel extends AbstractLevel{

	public JumperLevel(Collection<SpriteGroup<Sprite>> players, VoogaGame game) {
		super(players, game);
		this.getPhysics().addGlobalForce(new MassProportionalForceGenerator(new Force(0,-.0005)), true);
	}

	@Override
	public void loadLevel() {
		addAllSpritesFromPool();
		addBackground();
	}


}