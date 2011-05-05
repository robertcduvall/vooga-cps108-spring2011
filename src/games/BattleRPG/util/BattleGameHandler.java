package games.BattleRPG.util;

import games.BattleRPG.sprite.PlayerSprite;
import games.BattleRPG.sprite.StatSprite;

import java.awt.image.BufferedImage;

import vooga.core.VoogaGame;
import vooga.resources.Direction;

/**
 * This class will store all of our game information that is used
 * between levels, along with methods that are repeated
 * 
 * @author Austin Benesh
 */
public class BattleGameHandler
{
	public static PlayerSprite myPlayer;
	private static VoogaGame myGame;
	private static ResourceManager resources;
	
	public static void initHandler(VoogaGame game)
	{
		myGame = game;
		resources = ResourceManager.getInstance();
		resources.addResourcesFromFile("player","games.BattleRPG.resources");
		createCharacter();
	}
	private static void createCharacter()
	{
		String playerName = resources.getString("name");
		String playerGender = resources.getString("gender");
		if(!playerGender.equals("girl"))
			playerGender = "boy";
		BufferedImage playerImage = myGame.getImageLoader().getImage(playerGender+"Char",Direction.WEST);
		myPlayer = new PlayerSprite(myGame, playerImage, 0, 150, playerName);
	}
}
