package games.patterson_game.floating_objects;

import vooga.sprites.spritebuilder.components.collisions.CollisionCircleC;
import vooga.util.buildable.components.predefined.basic.HealthC;
import games.patterson_game.AvoiderGame;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


/**
 * A Floating piece of shrapnel that appear when an explosive explodes. Shrapnel
 * can inflict damage to ships as well as other floating objects
 * 
 * @author Andrew
 */
public class Shrapnel extends AbstractFloatingObject
{
    private static final long serialVersionUID = -4911923098191259631L;

    public Shrapnel (BufferedImage image, int x, int y)
    {
        super(image, x, y);
        addComponents(new HealthC(AvoiderGame.getBundle().getDouble("shrapnel_strength")));
        addComponent(new CollisionCircleC(getCenterPoint(), getWidth()/3));
    }
    
    @Override
    public void render(Graphics2D g)
    {
        AffineTransform aTransform = new AffineTransform();
        aTransform.translate((int) this.getX() +width/2, (int) this.getY()+height/2);
        aTransform.rotate(Math.toRadians(this.getAngle()));
        aTransform.translate((int) -width/2, (int) -height/2);
        g.drawImage(image.getScaledInstance(width, height, 0),aTransform,null);
    }

}
