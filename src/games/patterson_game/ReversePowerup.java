package games.patterson_game;

import games.patterson_game.refactoredVooga.core.VoogaGame;
import games.patterson_game.refactoredVooga.core.event.IEventHandler;
import games.patterson_game.refactoredVooga.resources.bundle.Bundle;
import games.patterson_game.refactoredVooga.sprites.spritebuilder.components.collisions.CollisionCircleC;
import games.patterson_game.refactoredVooga.util.buildable.components.predefined.basic.HealthC;
import java.awt.image.BufferedImage;

/**
 * A "powerup" that reverses the key mappings so that up moves you down, left move you right...etc
 * 
 * @author Andrew
 */
public class ReversePowerup extends AbstractFloatingObject
{
    private static final long serialVersionUID = 13114L;
    private VoogaGame myGame;
    private Bundle myBundle;

    public ReversePowerup(BufferedImage image, int x, int y)
    {
        super(image, x, y); 
        myBundle = AvoiderGame.getBundle();
        myGame = AvoiderGame.getInstance();
        addComponents(new HealthC(myBundle.getDouble("reverse_powerup_strength")),
                      new CollisionCircleC(getCenterPoint(), getWidth() / 3));
        createEventHandler();
    }


    /**
     * Collide this powerup with the ship, giving the ship "special" powers
     */
    @Override
    public void collideWithShip (Ship ship)
    {
        ship.setMaxSpeed(ship.getMaxSpeed()*-1);
        setActive(false);
        myGame.addTimer("RevertRegularDirection", (long) myBundle.getInteger("reverse_powerup_length"), "RevertRegularDirection", ship);
        myGame.fireEvent("DisplayThenResetText", "DisplayThenResetText", myBundle.getString("reverse_powerup_message"));
    }

    /**
     * Creates the event handler for this powerup
     */
    public void createEventHandler ()
    {
        myGame.getEventManager().registerEventHandler("RevertRegularDirection", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                Ship ship = (Ship) o;
                ship.setMaxSpeed(ship.getMaxSpeed()*-1);
                myGame.fireEvent("DisplayThenResetText", "DisplayThenResetText", myBundle.getString("reverse_powerup_revert_message"));

            }

        });
    }

}
