package games.patterson_game;

import games.patterson_game.refactoredVooga.sprites.improvedsprites.Sprite;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * A weapon that the ship fires
 * 
 * @author Andrew
 */
public class AbstractWeapon extends Sprite
{
    private static final long serialVersionUID = -4953070389615359003L;

    public AbstractWeapon (BufferedImage image, int x, int y)
    {
        super(image,x,y);
    }

    
    /**
     * Normal rendering except no rotations of image
     */
    @Override
    public void render (Graphics2D g, int x, int y)
    {
        AffineTransform aTransform = new AffineTransform();
        aTransform.translate((int) this.getX(), (int) this.getY());
        g.drawImage(image.getScaledInstance(width, height, 0),aTransform,null);
        renderComponents(g, x, y);
    }
}
