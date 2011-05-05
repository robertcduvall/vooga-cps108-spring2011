package games.jezzball.level;

import games.jezzball.sprite.Text;

import java.util.Collection;

import vooga.core.VoogaGame;
import vooga.levels.AbstractLevel;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

public class TextLevel extends AbstractLevel{
    VoogaGame game;
    public TextLevel(Collection<SpriteGroup<Sprite>> players, VoogaGame game) {
        super(players, game);
        this.game = game;
    }

    @Override
    public void loadLevel() {
        Text t = new Text(game.getImageLoader().getImage("text"), 200,200, game);
        addSpriteToGroup("text", t);
    }

}
