package games.missileDefense.levels;

import games.missileDefense.sprites.Gun;
import vooga.levels.IGoal;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;
import vooga.sprites.improvedsprites.Sprite;

public class PlayerIsOutOfLives implements IGoal
{
	private VoogaPlayField playfield;
	private LevelManager levels;

	/**
	 * Check whether the goal is completed.
	 */
	@Override
	public boolean checkCompletion (LevelManager levelManager)
	{
		return playfield.getSpriteGroup("gun").getActiveSprite() == null;
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

		System.out.println(Level.LOSS_LEVEL);
		levels.loadLevel(Level.LOSS_LEVEL);
		System.out.println("you lose!");
		levels.getCurrentGame().finish();
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
