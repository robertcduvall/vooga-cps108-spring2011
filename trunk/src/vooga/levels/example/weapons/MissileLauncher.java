package weapons;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import main.CustomPlayField;
import sprites.Ship;
import com.golden.gamedev.Game;


/**
 * Launches one missile every time you press the space bar
 */
public class MissileLauncher implements IWeaponLauncher
{
    private ArrayList<Missile> myMissiles;
    private Game myGame;
    private Ship myShip;
    private CustomPlayField myPlayField;


    public MissileLauncher (Ship s, Game g, CustomPlayField pf)
    {
        myGame = g;
        myShip = s;
        myPlayField = pf;
        myMissiles = new ArrayList<Missile>();
    }


    /**
     * Creates one new missile and places on background
     */
    private Missile createNewMissile ()
    {
        Missile newMissile = new Missile(myGame, myPlayField, myShip, this);
        newMissile.setBackground(myPlayField.getBackground());
        newMissile.setActive(true);
        myPlayField.addBullet(newMissile);
        return newMissile;
    }


    /**
     * Shoots one missile if the space bar is pressed
     */
    public void useWeapon ()
    {
        if (myGame.keyPressed(KeyEvent.VK_SPACE))
        {
            myMissiles.add(createNewMissile());
        }
    }


    /**
     * Draws all missiles, regardless of if the missile launcher is active
     */
    public void render (Graphics2D pen)
    {
        for (Missile missile : myMissiles)
        {
            missile.render(pen);
        }
    }


    /**
     * Updates all missiles, regardless of if the missile launcher is active
     */
    public void update (long elapsedTime)
    {
        for (Missile missile : myMissiles)
        {
            missile.update(elapsedTime);
        }
    }
    
    /**
     * Removes a missile from this launcher's "tracking" system
     */
    public void removeMissile(Missile missile)
    {
        myMissiles.remove(missile);
    }

}
