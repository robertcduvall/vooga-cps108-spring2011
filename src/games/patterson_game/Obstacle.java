package games.patterson_game;

import games.patterson_game.refactoredVooga.resources.bundle.Bundle;
import vooga.sprites.spritebuilder.components.collisions.CollisionCircleC;
import vooga.util.buildable.components.predefined.basic.HealthC;
import java.awt.image.BufferedImage;

/**
 * A normal, basic, block obstacle
 * 
 * @author Andrew 
 */
public class Obstacle extends AbstractFloatingObject
{
    private static final long serialVersionUID = 3429138833208028917L;

    public Obstacle(BufferedImage image, int x, int y)
    {
        super(image, x, y); 
        Bundle bundle = AvoiderGame.getBundle();
        addComponents(new HealthC(bundle.getDouble("obstacle_strength")));
        addComponent(new CollisionCircleC(getCenterPoint(), getWidth()/3));
    }
}
