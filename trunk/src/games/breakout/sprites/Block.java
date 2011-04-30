package games.breakout.sprites;

import java.awt.image.BufferedImage;
import vooga.sprites.improvedsprites.Sprite;

@SuppressWarnings("serial")
public class Block extends Sprite
{
    public Block(BufferedImage image, int x, int y)
    {
        super(image, x * image.getWidth(), y * image.getHeight());
    }
}
