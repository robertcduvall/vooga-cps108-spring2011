package games.mariobros;

import games.mariobros.sprites.Lander;


import java.util.Collection;
import vooga.core.VoogaGame;
import vooga.physics.forceGenerator.MassProportionalForceGenerator;
import vooga.physics.util.Force;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;
import vooga.stats.DisplayString;
import vooga.levels.AbstractLevel;

/**
 * Lunar Lander Lousy Lackluster Level
 * 
 * @author Ethan Goh
 * 
 */
public class Level extends AbstractLevel
{
	public Level(Collection<SpriteGroup<Sprite>> players, VoogaGame game)
	{
		super(players, game);
		super.getPhysics().addGlobalForce(
				(new MassProportionalForceGenerator(new Force(0, -0.00005))),
				true);
	}

	@Override
	public void loadLevel()
	{
		addAllSpritesFromPool();
		addBackground();
	}

}
