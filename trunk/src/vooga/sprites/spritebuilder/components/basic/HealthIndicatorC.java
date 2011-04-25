package vooga.sprites.spritebuilder.components.basic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import vooga.levels.IRenderable;
import vooga.sprites.improvedsprites.interfaces.IRenderXY;
import vooga.util.buildable.components.predefined.basic.HealthC;

public class HealthIndicatorC extends HealthC implements IRenderXY{

	public HealthIndicatorC(Double max, BufferedImage indicator) {
		super(max);
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g, int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
