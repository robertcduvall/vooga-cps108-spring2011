package games.jezzball;

import java.util.Collection;
import vooga.core.VoogaGame;
import vooga.levels.AbstractLevel;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

/**
 * @author Michael Ansel
 * @author Wesley Brown
 *
 */
public class Level extends AbstractLevel
{
    private Jezzball game;
    public Level (Collection<SpriteGroup<Sprite>> players, VoogaGame game)
    {
        super(players, game);
        this.game = (Jezzball)game;
    }

    @Override
    public void loadLevel ()
    {
        addAllSpritesFromPool();
        addBackground();
        game.addPlayer();
    }
    
    public void mouseClicked(int x, int y){
        addArchetypeSprite("tile", (x%20)*20, (y%20)*20);
    }
}
