package games.patterson_game.floating_objects;

import games.patterson_game.AvoiderGame;
import games.patterson_game.Ship;
import games.patterson_game.refactoredVooga.core.VoogaGame;
import games.patterson_game.refactoredVooga.core.event.IEventHandler;
import games.patterson_game.refactoredVooga.resources.bundle.Bundle;
import vooga.sprites.spritebuilder.components.collisions.CollisionCircleC;
import vooga.util.buildable.components.predefined.basic.HealthC;
import java.awt.image.BufferedImage;

/**
 * A powerup that speeds the ship up
 * 
 * @author Andrew
 */
public class SpeedUpPowerup extends AbstractFloatingObject
{
    private static final long serialVersionUID = 1314L;
    private VoogaGame myGame;
    private Bundle myBundle;

    public SpeedUpPowerup(BufferedImage image, int x, int y)
    {
        super(image, x, y); 
        myBundle = AvoiderGame.getBundle();
        myGame = AvoiderGame.getInstance();
        addComponents(new HealthC(myBundle.getDouble("speed_up_powerup_strength")));
        addComponent(new CollisionCircleC(getCenterPoint(), getWidth()/3));
        createEventHandler();
    }


    /**
     * Give this powerup to the ship, upon collision
     */
    @Override
    public void collideWithShip (Ship ship)
    {
        ship.setMaxSpeed(ship.getMaxSpeed()*myBundle.getDouble("speed_up_multiplier"));
        setActive(false);
        myGame.addTimer("RevertMaxSpeed", (long) myBundle.getInteger("speed_up_powerup_length"), "RevertMaxSpeed", ship);
        myGame.fireEvent("DisplayThenResetText", "DisplayThenResetText", myBundle.getString("speed_up_powerup_message"));

    }

    public void createEventHandler ()
    {
        myGame.getEventManager().registerEventHandler("RevertMaxSpeed", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                Ship ship = (Ship) o;
                ship.setMaxSpeed(ship.getMaxSpeed()/myBundle.getDouble("speed_up_multiplier"));
                myGame.fireEvent("DisplayThenResetText", "DisplayThenResetText", myBundle.getString("speed_up_powerup_revert_message"));
            }

        });
    }

}
