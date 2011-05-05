package games.patterson_game.floating_objects;

import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.collisions.CollisionCircleC;
import vooga.util.buildable.components.predefined.basic.HealthC;
import games.patterson_game.AvoiderGame;
import games.patterson_game.Ship;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


/**
 * Any object that is floating across the screen (includes powerups, obstacles, explosives and shrapnel)
 * 
 * @author Andrew Patterson
 */
public abstract class AbstractFloatingObject extends Sprite
{
    private static final long serialVersionUID = 3L;


    public AbstractFloatingObject (BufferedImage image, int x, int y)
    {
        super(image, x, y);
        addComponent(new CollisionCircleC(getCenterPoint(), getWidth() / 3));
    }


    /**
     * Normal rendering except no rotations of image
     */
    @Override
    public void render (Graphics2D g, int x, int y)
    {
        AffineTransform aTransform = new AffineTransform();
        aTransform.translate((int) this.getX(), (int) this.getY());
        g.drawImage(image.getScaledInstance(width, height, 0), aTransform, null);
        renderComponents(g, x, y);
    }


    /**
     * Performs damage to the floating object and removes it from the screen if its health is < 0
     * @param damage amount of damage to inflict
     */
    public void takeDamage (double damage)
    {
        getComponent(HealthC.class).decrease(damage);
        if (getComponent(HealthC.class).isDead())
        {
            setActive(false);
        }
    }

    
    /**
     * Deal damage to ship if collision occurs
     * @param ship
     */
    public void collideWithShip (Ship ship)
    {
        ship.takeDamage(AvoiderGame.getBundle().getDouble("floating_object_to_ship_damage"));
        setActive(false);
    }
}
