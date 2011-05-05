package games.jumper.levelstuff;

import java.util.Collection;

import vooga.core.VoogaGame;
import vooga.levels.AbstractLevel;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

public class JumperLevel extends AbstractLevel{

	public JumperLevel(Collection<SpriteGroup<Sprite>> players, VoogaGame game) {
		super(players, game);
	}

	@Override
	public void loadLevel() {
		addAllSpritesFromPool();
		addBackground();
	}


}