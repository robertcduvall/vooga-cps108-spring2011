package games.jumper.levelstuff;

import java.util.Collection;

import vooga.core.VoogaGame;
import vooga.levels.AbstractLevel;
import vooga.physics.forceGenerator.MassProportionalForceGenerator;
import vooga.physics.util.Force;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;
/**
 * 
 * @author Charlie Hatcher
 *
 */
public class JumperLevel extends AbstractLevel{

	private static final double Y_COMPONENT_FOR_GRAVITY = -.0005;

	/**
	 * Creates a level of jumper with a physics engine for gravity.
	 * @param players
	 * @param game
	 */
	public JumperLevel(Collection<SpriteGroup<Sprite>> players, VoogaGame game) {
		super(players, game);
		this.getPhysics().addGlobalForce(new MassProportionalForceGenerator(new Force(0,Y_COMPONENT_FOR_GRAVITY)), true);
	}

	/**
	 * Loads all of the sprites in the given level file
	 */
	@Override
	public void loadLevel() {
		addAllSpritesFromPool();
		addBackground();
	}


}