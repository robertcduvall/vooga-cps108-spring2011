package games.missileDefense.levels;

import games.breakout.levels.Level;
import games.missileDefense.sprites.Gun;
import vooga.levels.IGoal;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;
import vooga.sprites.improvedsprites.Sprite;

public class CitiesDead implements IGoal
{
	private VoogaPlayField playfield;
	private LevelManager levels;

	/**
	 * Check whether the goal is completed.
	 */
	@Override
	public boolean checkCompletion (LevelManager levelManager)
	{
		System.out.println(playfield.getSpriteGroup("city") == null);
		return playfield.getSpriteGroup("city").getActiveSprite() == null;
	}


	/**
	 * Lose the game.
	 */
	@Override
	public void progress ()
	{
		Gun gun = (Gun) playfield.getSpriteGroup("gun").getActiveSprite();

		if (gun != null) gun.setActive(false);

		for(Sprite missile : playfield.getSpriteGroup("missile").getSprites())
		{
			if (missile != null)
				missile.setActive(false);
		}
		
		for(Sprite city : playfield.getSpriteGroup("city").getSprites())
		{
			if (city != null)
				city.setActive(false);
		}

		levels.loadLevel(Level.LOSS_LEVEL);
	}


	/**
	 * Prepare the goal for being checked.
	 */
	@Override
	public void setupGoal (LevelManager manager, VoogaPlayField playfield)
	{
		this.playfield = playfield;
		this.levels = manager;
	}
}
