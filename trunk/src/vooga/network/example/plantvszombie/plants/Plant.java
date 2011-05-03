package vooga.network.example.plantvszombie.plants;
import vooga.network.example.plantvszombie.game.PlantVsZombie;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;


public class Plant extends Sprite{
	protected int life;
	protected PlantVsZombie game;
	protected int myRow;
	protected int myColumn;
	
	public Plant(BufferedImage image, int x, int y, int row, int column, PlantVsZombie game){
		super(image, x, y);
		this.game = game;
		this.game.PLANT_GROUP[row].add(this);
		myRow = row;
		myColumn = column;
	}
	public void getDamage(int power){
		life = life - power;
		if(life<=0){
			setActive(false);
			game.occupiedGrid[myRow][myColumn]=false;
		}
	}
	
}