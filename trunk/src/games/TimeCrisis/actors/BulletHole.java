package games.TimeCrisis.actors;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;


public class BulletHole extends Sprite 
{	
	private int timer = 0;
	
	public BulletHole(VoogaGame game, int x, int y)
	{
		this.setImage(game.getImage("games/TimeCrisis/resources/images/BulletHole.png"));
		
		this.setX(x - this.getWidth()/2);
		this.setY(y - this.getHeight()/2);
	}
	
	public void update(long arg0)
	{
		timer++;
		if(timer > 250)
			this.setActive(false);
	}
}
