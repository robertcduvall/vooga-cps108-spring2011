package games.spaceinvaders;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends Sprites implements Commons
{
    private final int START_Y = 280; 
    private final int START_X = 270;
    private int width;

    public Player(BufferedImage image) 
    {
        super(image);
        width = image.getWidth(null); 
        setX(START_X);
        setY(START_Y);
    }

    public void act() 
    {
        x += dx;
        if (x <= 2) 
            x = 2;
        if (x >= BOARD_WIDTH - 2*width) 
            x = BOARD_WIDTH - 2*width;
    }

    public void keyPressed(KeyEvent e) 
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT)
        {
            dx = -2;
        }
        if (key == KeyEvent.VK_RIGHT)
        {
            dx = 2;
        }
    }

    public void keyReleased(KeyEvent e) 
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT)
        {
            dx = 0;
        }
        if (key == KeyEvent.VK_RIGHT)
        {
            dx = 0;
        }
    }
}