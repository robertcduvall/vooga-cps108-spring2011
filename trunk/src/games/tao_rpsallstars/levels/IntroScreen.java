/**
 * 
 */
package games.tao_rpsallstars.levels;

import java.util.Collection;

import vooga.core.VoogaGame;
import vooga.levels.AbstractLevel;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

/**
 * @author Kevin
 *
 */
public class IntroScreen extends AbstractLevel{

	private VoogaGame game;
	/**
	 * @param players
	 * @param game
	 */
	public IntroScreen(Collection<SpriteGroup<Sprite>> players, VoogaGame game) {
		super(players, game);
        this.game = game;
	}

	/* (non-Javadoc)
	 * @see vooga.levels.AbstractLevel#loadLevel()
	 */
	@Override
	public void loadLevel() {
        addAllSpritesFromPool();
        addBackground();
		
	}

}
