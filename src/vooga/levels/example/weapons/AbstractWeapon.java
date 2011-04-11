package weapons;

import main.CustomPlayField;
import resourceManager.ResourceManager;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;


/**
 * A sprite that represents any type of weapon
 */
public abstract class AbstractWeapon extends Sprite
{
    protected static final ResourceManager weaponResources = new ResourceManager("weapon");
    private static final long serialVersionUID = 1L;
    
    protected CustomPlayField myPlayField;
    protected int myDamage;
    protected Game myGame;


    public AbstractWeapon (String imagePath, int damage, Game game, CustomPlayField playfield, double startX, double startY)
    {
        super(game.getImage(imagePath), startX, startY);
        myDamage = damage;
        myPlayField = playfield;
        myGame = game;
        setImmutable(true);     // VERY IMPORTANT! DO NOT REMOVE!
    }


    /**
     * Performs damage to target
     */
    public abstract void performDamage (Sprite target);


    /**
     * Removes the bullet from play
     */
    public abstract void remove ();


    /**
     * Returns how much damage this weapon inflicts
     */
    public int getDamage ()
    {
        return myDamage;
    }
}
