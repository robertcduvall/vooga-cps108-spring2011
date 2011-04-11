package weapons;

import main.CustomPlayField;
import sprites.Alien;
import sprites.Ship;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

/**
 * The standard weapon on the ship.  The user can shoot an unlimited amount
 * of missiles by pressing the space bar
 */
public class Missile extends AbstractWeapon
{
    private static final long serialVersionUID = 1L;
    private MissileLauncher myLauncher;


    public Missile (Game game, CustomPlayField playfield, Ship ship, MissileLauncher launcher)
    {
        super(weaponResources.getString("missile_image"), weaponResources.getInteger("missile_damage"),
              game, playfield, ship.getX(), ship.getY());
        setSpeed(weaponResources.getDouble("missile_x_speed"), weaponResources.getDouble("missile_y_speed"));
        setLocation(ship.getX(), ship.getY() - getHeight());
        myLauncher = launcher;
    }


    public void performDamage (Sprite a)
    {
        if (a instanceof Alien)
        {
            Alien alien = (Alien) a;
            int points = alien.takeDamage(myDamage);
            myPlayField.updateScoreBoard(points);
        }
    }


    public void remove ()
    {
        myPlayField.removeBullet(this);
        myLauncher.removeMissile(this);
    }
}
