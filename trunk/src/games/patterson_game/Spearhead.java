package games.patterson_game;

import games.patterson_game.refactoredVooga.resources.bundle.Bundle;
import games.patterson_game.refactoredVooga.sprites.spritebuilder.components.basic.AccelerationC;
import games.patterson_game.refactoredVooga.sprites.spritebuilder.components.collisions.CollisionCircleC;
import games.patterson_game.refactoredVooga.util.buildable.components.predefined.basic.HealthC;
import java.awt.image.BufferedImage;

/**
 * An upgraded weapon that the ship can collect from a powerup
 * 
 * @author Andrew
 */
public class Spearhead extends AbstractWeapon
{
    private static final long serialVersionUID = 7355288217744359622L;
    
    public Spearhead (BufferedImage image, int x, int y)
    {
        super(image, x, y);
        Bundle bundle = AvoiderGame.getBundle();
        addComponents(new HealthC(bundle.getDouble("spearhead_strength")),
                      new AccelerationC(bundle.getDouble("spearhead_acceleration")),
                      new CollisionCircleC(getCenterPoint(), getWidth() / 2));
    }

    public void takeDamage (double damage)
    {
        getComponent(HealthC.class).decrease(damage);
        if (getComponent(HealthC.class).isDead())
        {
            setActive(false);
        }        
    }

}
