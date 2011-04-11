package weapons;

import java.awt.Graphics2D;


/**
 * Implemented by all weapon launchers. This is needed because each type of
 * weapon is launched differently. For example, only one tornado can be fired at
 * once by holding the space bar, but multiple missiles can be launched by
 * pressing the space bar.
 */
public interface IWeaponLauncher
{
    /**
     * Uses the weapon of the implementing class
     */
    public void useWeapon ();


    /**
     * Draws the weapon(s) that was fired
     */
    public void render (Graphics2D pen);


    /**
     * Updates the weapon's location
     */
    public void update (long elapsedTime);
}
