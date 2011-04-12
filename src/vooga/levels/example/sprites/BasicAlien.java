package vooga.levels.example.sprites;

import java.util.Random;
import vooga.levels.example.main.CustomPlayField;
import vooga.levels.example.weapons.AlienBullet;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;


/**
 * The basic default alien which is used to create more complex types of aliens.
 * Do not use just a basic alien, it most likely will not have all the functionality you want.
 */
public class BasicAlien extends Sprite implements Alien
{
    private static final long serialVersionUID = 1L;

    private CustomPlayField myPlayField;
    private int myHealth;
    private int myPoints; // How many points is this alien worth if destroyed
    private Sprite mySuperAlien;
    protected Game myGame;


    public BasicAlien (int x, int y, int strength, Game game, CustomPlayField pf)
    {
        super(game.getImage(alienResources.getString("alien_image")), x, y);
        myGame = game;
        myHealth = strength;
        myPlayField = pf;
        myPoints = alienResources.getInteger("default_point_value");
    }

    
    /**
     * Takes damage to this alien and returns how many points that was worth
     */
    public int takeDamage (int damage)
    {
        myHealth -= damage;
        if (myHealth <= 0)
        {
            myPlayField.removeAlien(mySuperAlien);
            return myPoints;
        }
        else return 0;
    }


    /**
     * Fires a slow steady stream of bullets
     */
    public void fireBullet ()
    {
        Random generator = new Random();
        int random = generator.nextInt(alienResources.getInteger("default_bullet_delay"));
        if (random == 1)
        {
            myPlayField.addAlienBullet(new AlienBullet(myGame, myPlayField, getX(), getY()));
        }
    }
    
    
    /**
     * By default a basic alien does not move
     */
    public void move ()
    {}


    public void setSuperAlien (Sprite alien)
    {
        mySuperAlien = alien;
    }


    public Game getGame ()
    {
        return myGame;
    }


    public CustomPlayField getPlayField ()
    {
        return myPlayField;
    }
}
