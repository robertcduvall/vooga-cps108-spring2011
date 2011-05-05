package games.BattleRPG.level;

import games.BattleRPG.sprite.PlayerSprite;
import games.BattleRPG.sprite.StatSprite;
import games.BattleRPG.util.BattleGameHandler;

import java.util.Collection;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.levels.AbstractLevel;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

public class MapLevel extends AbstractLevel
{
	private VoogaGame myGame;
	private PlayerSprite myPlayer;
	public boolean isDead;
	
	public MapLevel(Collection<SpriteGroup<Sprite>> players, VoogaGame game)
	{
		super(players, game);
		myGame = game;
	}

	@Override
	public void loadLevel()
	{
		myPlayer = BattleGameHandler.myPlayer;
		myPlayer.setX(myPlayer.mapX);
		myPlayer.setY(myPlayer.mapY);
		addBackground();
		addPlayer();
		loadEventHandlers();
	}
	private void addPlayer()
	{
		this.getSpriteGroup("playerSprite").addSprites(BattleGameHandler.myPlayer);
	}
	
	private void movePlayer(int right, int down)
	{
		myPlayer.moveX(right * 2);
		myPlayer.moveY(down * 2);
	}
	
	private void calculatePosition()
	{
		double myX = myPlayer.getX();
		double myY = myPlayer.getY();
		
		myPlayer.mapX = myX;
		myPlayer.mapY = myY;
		
		if(myX < 70 && myY > 95 && myY < 200)	//healing house
		{
			myPlayer.curHP = myPlayer.baseHP;
			myPlayer.alive = true;
			System.out.println("\nYou feel rested, and your hitpoints are restored.");
		}
		else if(myX > 300)							//ice environment
			myGame.getLevelManager().loadLevel(2);
		else
			myGame.getLevelManager().loadLevel(1);
	}
	private void loadEventHandlers()
	{
		myGame.registerEventHandler("moveRight", new IEventHandler(){
			@Override
			public void handleEvent(Object o) {
				movePlayer(1,0);
			}
        });
		myGame.registerEventHandler("moveDown",new IEventHandler(){
			@Override
			public void handleEvent(Object o) {
				movePlayer(0,1);
			}
		});
		myGame.registerEventHandler("moveLeft",new IEventHandler(){
			@Override
			public void handleEvent(Object o) {
				movePlayer(-1,0);
			}
		});
		myGame.registerEventHandler("moveUp",new IEventHandler(){
			@Override
			public void handleEvent(Object o) {
				movePlayer(0,-1);
			}
		});
		myGame.registerEventHandler("enterButton",new IEventHandler(){
			@Override
			public void handleEvent(Object o) {
				calculatePosition();
			}
		});
		myGame.registerEventHandler("escapeKey",new IEventHandler(){
			@Override
			public void handleEvent(Object o) {
				displayPlayerInfo();
			}
		});
	}
	/**
	 * Ideally, do this using the TextBox in util. Using system.out.println because
	 * the text interfaces in Vooga are written for the original GoldenT, which doesn't
	 * help much.
	 */
	private void displayPlayerInfo()
	{
		System.out.println(myPlayer);
	}
}
