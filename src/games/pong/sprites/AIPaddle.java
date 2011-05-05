package games.pong.sprites;

import java.awt.image.BufferedImage;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.PongTargetC;
import vooga.sprites.spritegroups.SpriteGroup;

public class AIPaddle extends AbstractPaddle{
	
	public AIPaddle(VoogaGame game, double x, double y) {
		super(game.getImageLoader().getImage("paddle"), x, y);
		health = 10;
		
		
		//game.getEventManager().addTimer("hi", 500, "AIstart", game);
		
		game.registerEventHandler("AIstart", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
            	System.out.println("aistarts!");
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
		
		game.registerEventHandler("DecreaseAIHealth.bounds", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
            	decreaseHealth();
            	System.out.println("AI HEalth: "+health);
            }            
        });		
		
		game.registerEventHandler("FirePowerUp.one", new IEventHandler() 
        {
            @Override
            public void handleEvent (Object o)
            {
            	System.out.println("rere!");
            	//System.out.println("asdf");
            	VoogaGame game = (VoogaGame) o;
            	SpriteGroup powerupGroup = game.getLevelManager().getCurrentLevel().getSpriteGroup("powerup");
        		
            	getComponent(PongTargetC.class).addSpriteGroup(powerupGroup);
            }            
        });
		
	}
}
