package games.missileDefense.levels;

import java.util.Collection;
import vooga.core.VoogaGame;
import vooga.physics.forceGenerator.MassProportionalForceGenerator;
import vooga.physics.util.Force;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;
import vooga.levels.AbstractLevel;

/**
 * just defines the various levels
 * @author johnegan
 *
 */
public class Level extends AbstractLevel
{
    public static final int GAME_LEVELS = 2;
    public static final int WIN_LEVEL = 2;
    public static final int LOSS_LEVEL = 3;
    
    public Level(Collection<SpriteGroup<Sprite>> players, VoogaGame game) 
    {
		super(players, game);
	}

	@Override
    public void loadLevel ()
    {
		addAllSpritesFromPool();
        addBackground();
        this.getPhysics().addGlobalForce(new MassProportionalForceGenerator(new Force(0, -0.0035)), true);
    }

}
