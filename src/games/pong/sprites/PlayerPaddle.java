package games.pong.sprites;

import java.awt.image.BufferedImage;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;

public class PlayerPaddle extends AbstractPaddle{

	public PlayerPaddle(VoogaGame game, double x, double y) {
		super(game.getImageLoader().getImage("paddle"), x, y);
		        
        game.registerEventHandler("Input.Player.Up", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                //System.out.println("up!");
            	setY(getY()-5);
            }            
        });
        
        game.registerEventHandler("Input.Player.Down", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
            	//System.out.println("down!");
            	setY(getY()+5);
            }            
        });

	}
	
	

}
