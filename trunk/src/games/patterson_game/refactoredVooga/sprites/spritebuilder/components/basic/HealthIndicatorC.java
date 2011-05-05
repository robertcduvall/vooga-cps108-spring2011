package games.patterson_game.refactoredVooga.sprites.spritebuilder.components.basic;

import games.patterson_game.refactoredVooga.levelsRefactored.IRenderable;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.interfaces.IRenderXY;
import games.patterson_game.refactoredVooga.util.buildable.components.predefined.basic.HealthC;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


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
