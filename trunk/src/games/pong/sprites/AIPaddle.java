package games.pong.sprites;

import java.awt.image.BufferedImage;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.PongTargetC;

public class AIPaddle extends AbstractPaddle{

	
	public AIPaddle(VoogaGame game, double x, double y) {
		super(game.getImageLoader().getImage("paddle"), x, y);
		
		
		//game.getEventManager().addTimer("hi", 500, "AIstart", game);
		
		game.registerEventHandler("AIstart", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
            	//System.out.println("asdf");
            	PongTargetC trackBall = new PongTargetC();
            	removeComponent(trackBall);
            	//System.out.println("asdf");
            	VoogaGame game = (VoogaGame) o;
            	Sprite ball = game.getLevelManager().getCurrentLevel().getSpriteGroup("ball").getActiveSprite();
        		//System.out.println(ball.getCenterX());
        		
        		trackBall.setTarget(ball);
        		addComponent(trackBall);
            }            
        });
		
		game.registerEventHandler("PowerUpAppears", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
            	System.out.println("rere!");
            	//System.out.println("asdf");
            	VoogaGame game = (VoogaGame) o;
            	Sprite newball = game.getLevelManager().getCurrentLevel().getSpriteGroup("ball").getActiveSprite();
        		//System.out.println(ball.getCenterX());
        		
            	getComponent(PongTargetC.class).addSprite(newball);
            }            
        });
		
	}
}
