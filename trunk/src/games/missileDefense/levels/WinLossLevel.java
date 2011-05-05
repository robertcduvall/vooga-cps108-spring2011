package games.missileDefense.levels;


import games.missileDefense.sprites.Gun;

import java.util.Collection;

import vooga.core.VoogaGame;
import vooga.levels.AbstractLevel;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

/**
 * A level where nothing happens except for a "you win" or "you lose" message. 
 * 
 * @author Misha, Max Egan
 *
 */

public class WinLossLevel extends AbstractLevel
{
	private VoogaGame game;

	public WinLossLevel (Collection<SpriteGroup<Sprite>> players, VoogaGame game)
	{
		super(players, game);
		this.game = game;
	}

	/**
	 * Load the level by loading all the available blocks.
	 */
	@Override
	public void loadLevel()
	{
		this.getAllSpriteGroups().clear();
		
		addAllSpritesFromPool();
		addBackground();
		
		game.getEventManager().removeEventHandlers("*");
	}
}
