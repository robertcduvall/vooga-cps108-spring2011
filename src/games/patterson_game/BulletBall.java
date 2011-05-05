package games.patterson_game;

import games.patterson_game.refactoredVooga.resources.bundle.Bundle;
import vooga.sprites.spritebuilder.components.basic.AccelerationC;
import vooga.sprites.spritebuilder.components.collisions.CollisionCircleC;
import vooga.util.buildable.components.predefined.basic.HealthC;
import java.awt.image.BufferedImage;

/**
 * The default red, ball bullet that the ship fires
 * 
 * @author Andrew
 */
public class BulletBall extends AbstractWeapon
{
    private static final long serialVersionUID = 12L;
    
    public BulletBall(BufferedImage image, int x, int y)
    {
        super(image, x, y); 
        Bundle bundle = AvoiderGame.getBundle();
        addComponents(new HealthC(bundle.getDouble("bullet_ball_strength")),
                      new AccelerationC(bundle.getDouble("bullet_ball_acceleration")),
                      new CollisionCircleC(getCenterPoint(), getWidth() / 2));
    }

}
