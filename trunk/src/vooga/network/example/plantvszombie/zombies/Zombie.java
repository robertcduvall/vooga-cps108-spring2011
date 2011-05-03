package vooga.network.example.plantvszombie.zombies;

import vooga.network.example.plantvszombie.game.PlantVsZombie;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

public class Zombie extends Sprite
{
	protected int life;
	protected double speed;
	protected PlantVsZombie game;
	protected int power;
	protected int myRow;
	
	public Zombie(BufferedImage image, int x, int y, int row, PlantVsZombie game){
		super(image, x, y);
		this.game = game;
		power = 1;
		myRow = row;
		game.ZOMBIE_GROUP[myRow].add(this);
	}
	
	public void getDamage(int power){
		life = life - power;
		if(life<=0)
			setActive(false);
	}
	
	@Override
	public void update(long elapsedTime){
		super.update(elapsedTime);
		if(!isOnScreen()){
			setActive(false);
			game.gameLose();
		}
	}
	
	public int getPower(){
		return power;
	}
}
