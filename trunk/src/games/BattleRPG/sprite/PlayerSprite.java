package games.BattleRPG.sprite;


import java.awt.image.BufferedImage;

import vooga.core.VoogaGame;
/**
 * Extension of StatSprite to handle experience for the player character
 * @author Austin Benesh
 */
public class PlayerSprite extends StatSprite
{
	private static final long serialVersionUID = 1L;
	
	public int myXP;
	private String myName;
	public double mapX = 0;
	public double mapY = 150;
	
	public PlayerSprite(VoogaGame game, BufferedImage playerImage, int x, int y, String name)
	{
		super(game, playerImage, x, y, 2, 3);
		myXP = (int)(10 * Math.pow(myXPLevel, 1.4));
		myName = name;
	}
	
	@Override
	public String updateXP(int xpGained)
	{
		String ret = xpGained+" XP Gained";
		int nextXP = (int)(4 * Math.pow(myXPLevel, 1.3));
		myXP += xpGained;
		if(myXP > nextXP)
		{
			myXPLevel++;
			calculateInfo();
			ret = ret + "...Leveled up! Now level "+myXPLevel;
		}
		return ret;
	}
	
	@Override
	public String getName()
	{
		return myName;
	}
	public String toString()
	{
		return ("\nPlayer level: "+myXPLevel+
				"\nHP: "+curHP+"/"+baseHP+
				"\nXP to level: "+((int)(10 * Math.pow(myXPLevel+1, 1.4))-myXP));
	}
}
