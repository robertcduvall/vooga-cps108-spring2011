package sprites;

import java.awt.Graphics2D;
import main.CustomPlayField;
import resourceManager.ResourceManager;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;


/**
 * An alien interface that must be implemented by all sprites acting as aliens.
 * Most, if not all classes implementing Alien should also extend Sprite;
 * therefore, the majority of these methods are already implemented in the
 * Sprite class.
 */
public interface Alien
{
    static final ResourceManager alienResources = new ResourceManager("alien");


    /**
     * Fires a bullet from this alien
     */
    public void fireBullet ();


    /**
     * The normal update method for a Sprite. This should call move() if the
     * Alien needs to change location and fire() if the alien should fire
     */
    public void update (long elapsedTime);


    /**
     * Inflicts damage to the alien and returns how many points that was worth
     */
    public int takeDamage (int damage);


    /**
     * Moves the alien
     */
    public void move ();


    /**
     * Returns the background on which this alien resides
     */
    public Background getBackground ();


    /**
     * Draws this alien
     */
    public void render (Graphics2D pen);


    /**
     * Sets this alien's speed
     */
    public void setSpeed (double dx, double dy);


    /**
     * Sets this alien's vertical speed
     */
    public void setVerticalSpeed (double ySpeed);


    /**
     * Sets this alien's horizontal speed
     */
    public void setHorizontalSpeed (double xSpeed);

    /**
     * Returns the alien's x location
     */
    public double getX ();

    /**
     * Returns the alien's y location
     */
    public double getY ();

    /**
     * Set what alien is the super (super class) of this alien
     */
    public void setSuperAlien (Sprite alien);

    /**
     * Returns this alien's game
     */
    public Game getGame ();

    /**
     * Returns this alien's playing field
     * @return
     */
    public CustomPlayField getPlayField ();
}
