package games.mariobros;

import java.util.Collection;
import vooga.core.VoogaGame;
import vooga.physics.forceGenerator.MassProportionalForceGenerator;
import vooga.physics.util.Force;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;
import vooga.levels.AbstractLevel;

public class Level extends AbstractLevel
{
    public Level (Collection<SpriteGroup<Sprite>> players, VoogaGame game)
    {
        super(players, game);
        super.getPhysics().addGlobalForce((new MassProportionalForceGenerator(new Force(0, -0.00005))),true);
    }

    /**
     * Load the level by loading all the available blocks.
     */
    @Override
    public void loadLevel ()
    {
        addAllSpritesFromPool();
        addBackground();
    }

}
