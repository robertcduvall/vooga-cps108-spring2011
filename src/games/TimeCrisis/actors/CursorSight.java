package games.TimeCrisis.actors;
import java.awt.Graphics2D;

import vooga.core.VoogaGame;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;


/**
 * @author Troy Ferrell
 *
 */

public class CursorSight 
{
	private VoogaGame myGame;
	
	Sprite cursorImg;
	
	public CursorSight(VoogaGame game)
	{
		myGame = game;
		cursorImg = new Sprite(game.getImage("games/TimeCrisis/resources/images/CrossHair.png"));
	}
	
	public void render(Graphics2D arg0)
	{
		cursorImg.render(arg0);
	}
	
	public void update(long deltaTime)
	{
		cursorImg.setX(myGame.getMouseX() - cursorImg.getWidth()/2);
		cursorImg.setY(myGame.getMouseY() - cursorImg.getHeight()/2);
	}
	
}
