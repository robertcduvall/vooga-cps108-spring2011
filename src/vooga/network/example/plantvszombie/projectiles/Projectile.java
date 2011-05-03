package vooga.network.example.plantvszombie.projectiles;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;


public class Projectile extends Sprite
{
	int power;
	
	
	public Projectile(BufferedImage image, int x, int y, int power){
		super(image, x, y);
		this.power = power;
	}
	
	public int getPower(){
		return power;
	}
	
	@Override
	public void update(long elapsedTime){
		super.update(elapsedTime);
		if(!isOnScreen())
			setActive(false);
	}
}
