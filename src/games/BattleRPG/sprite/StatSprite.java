package games.BattleRPG.sprite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import vooga.core.VoogaGame;
import vooga.sprites.improvedsprites.Sprite;

/**
 * Extension of Sprite class that holds a number of statistics for enemies and players
 * @author Austin Benesh
 */
public class StatSprite extends Sprite
{
	private static final long serialVersionUID = 1L;

	private int myType;	//denotes type (and therefore difficulty) of enemy
	
	public int hitCenter;	//center of hit distribution
	public int baseHP;
	public int curHP;
	public int mySpeed;
	public boolean alive;
	public int myXPLevel;
	
	private Random rand;
	private VoogaGame myGame;
	
	
	public StatSprite(VoogaGame game, BufferedImage image, int x, int y, int type, int xplevel){
		super(image,x,y);
		myGame = game;
		myType = type;
		myXPLevel = xplevel;
		rand = new Random();
		alive = true;
		calculateInfo();
	}
	
	protected void calculateInfo()
	{
		curHP = myXPLevel * 100 + myType * 15;
		baseHP = curHP;
		hitCenter = (myXPLevel+myType)*10;
		mySpeed = (myXPLevel + myType);
	}
	public void engageSprite(StatSprite enemySprite)
	{
		if(enemySprite.mySpeed > this.mySpeed)
		{
			enemySprite.attackSprite(this);
			if(this.alive)
				this.attackSprite(enemySprite);
			
		}
		else
		{
			this.attackSprite(enemySprite);
			if(enemySprite.alive)
				enemySprite.attackSprite(this);
		}
	}
	public void attackSprite(StatSprite defendingSprite)
	{
		//Prevent hits from piling at once so user can follow more easily
		try
		{
			Thread.sleep(500);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		int randomHit = (int)Math.floor((int)hitCenter * .7) + rand.nextInt((int)Math.ceil(hitCenter * .6));
		
		System.out.println(getFightInfo(this,defendingSprite,randomHit));
		
		defendingSprite.curHP -= randomHit;
		if(defendingSprite.curHP <= 0)
		{
			if(defendingSprite.myType != 2)
			{
				System.out.println("\nKilled "+defendingSprite.getName()+". "+
					this.updateXP(defendingSprite.myXPLevel + defendingSprite.myType));
			}
			if(defendingSprite.myType == 10)
			{
				System.out.println("\nCongratulations! You have slain the fire dragon!");
			}
			defendingSprite.alive = false;
		}
	}
	protected String updateXP(int xpGained)
	{
		return "Error: Enemies do not have xp";
	}
	public String getName()
	{
		switch(myType){
		case 0: return "Wolf";
		case 1: return "Cat";
		case 2: return "Player";
		}
		return "Dragon";
	}
	
	/**
	 * Below method can be heavily refactored with a more applicable text interface
	 */
	public String getFightInfo(StatSprite attacker, StatSprite defender, int randomHit)
	{
		return ("\n"+attacker.getName()+" attacks "+defender.getName()+ 
				" for "+randomHit+"\n"+ defender.getName()+"'s HP : "+defender.curHP+
				"/"+defender.baseHP+" -> "+(defender.curHP-randomHit)+"/"+defender.baseHP);
	}
}
