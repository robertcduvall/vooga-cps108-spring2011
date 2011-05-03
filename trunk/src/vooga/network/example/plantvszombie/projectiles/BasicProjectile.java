package vooga.network.example.plantvszombie.projectiles;

import vooga.network.example.plantvszombie.game.PlantVsZombie;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

public class BasicProjectile extends Projectile
{
	public BasicProjectile(BufferedImage image, int x, int y)
	{
		super(image, x, y, 2);
	}
}
