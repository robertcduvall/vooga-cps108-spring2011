package games.breakout.sprites;

import vooga.core.VoogaGame;
import vooga.sprites.improvedsprites.Sprite;

@SuppressWarnings("serial")
public class Paddle extends Sprite
{
    public Paddle (VoogaGame game, double x, double y)
    {
        super(game.getImageLoader().getImage("paddle"));
        
        setX(x - getWidth()/2);
        setY(y - getHeight());
    }

}
