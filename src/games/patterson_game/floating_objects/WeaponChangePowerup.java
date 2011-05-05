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
 * A powerup that changes your ship's weapon to the spearhead weapon
 * @author Andrew
 */
public class WeaponChangePowerup extends AbstractFloatingObject
{
    private static final long serialVersionUID = 13114L;
    private VoogaGame myGame;
    private Bundle myBundle;

    public WeaponChangePowerup(BufferedImage image, int x, int y)
    {
        super(image, x, y);
        myBundle = AvoiderGame.getBundle();
        myGame = AvoiderGame.getInstance();
        addComponents(new HealthC(15.0));
        addComponent(new CollisionCircleC(getCenterPoint(), getWidth()/3));
        createEventHandler();
    }

    /**
     * Give the ship this new weapon
     */
    @Override
    public void collideWithShip (Ship ship)
    {
        String weapon = myBundle.getString("weapon_change_powerup_weapon");
        ship.setWeapon(weapon);
        setActive(false);
        myGame.addTimer("RevertWeapon", (long) myBundle.getInteger("revert_weapon_delay"), "RevertWeapon", ship);
        myGame.fireEvent("DisplayThenResetText", "DisplayThenResetText", myBundle.getString("weapon_change_message") + weapon.toUpperCase());

    }

    public void createEventHandler ()
    {
        myGame.getEventManager().registerEventHandler("RevertWeapon", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                Ship ship = (Ship) o;
                ship.revertToDefaultWeapon();
                myGame.fireEvent("DisplayThenResetText", "DisplayThenResetText", myBundle.getString("weapon_change_revert_message"));
            }

        });
    }

}
