package games.missilecommand.sprites;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import vooga.sprites.improvedsprites.Sprite;

/**
 * A sprite that represents a banner informing the player of his defeat.
 * @author Alex Lee (hl69)
 */
public class Banner extends Sprite
{

    public Banner(BufferedImage image, int x, int y)
    {
        super(image, x, y);
    }

    public void render(Graphics2D g, int x, int y)
    {
        super.render(g, x, y);
    }
}
