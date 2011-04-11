package vooga.levels.example.weapons;

import vooga.levels.example.main.CustomPlayField;
import vooga.levels.example.sprites.Ship;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

/**
 * The default type of bullet that an alien fires
 */
public class AlienBullet extends AbstractWeapon
{
    private static final long serialVersionUID = 1L;

    /**
     * Create a bullet with default speed
     */
    public AlienBullet(Game game, CustomPlayField playfield, double startX, double startY)
    {
        this(game, playfield, startX, startY,weaponResources.getDouble("alien_bullet_x_speed"), weaponResources.getDouble("alien_bullet_y_speed"));
    }
    
    /**
     * Create a bullet with a given speed
     */
    public AlienBullet(Game game, CustomPlayField playfield, double startX, double startY, double x_speed, double y_speed)
    {
        super(weaponResources.getString("alien_bullet_image"), weaponResources.getInteger("default_damage"), game, playfield, startX, startY);
        setSpeed(x_speed, y_speed);
    }

    /**
     * Performs damage to a ship target
     */
    public void performDamage (Sprite target)
    {
        Ship ship = (Ship) target;
        ship.takeDamage(myDamage);
    }

    /**
     * Removes this bullet from the game
     */
    public void remove ()
    {
        this.setActive(false);
        myPlayField.removeAlienBullet(this);
    }

}
