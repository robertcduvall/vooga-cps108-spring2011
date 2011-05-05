package games.pacman.sprites.players.enemies;

import games.pacman.sprites.players.Players;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class Dumbie extends Players{
	
	public Dumbie(BufferedImage image, int x, int y) {
		super(image,x,y);
		origX=x*48+2;
	    origY=y*48+2;
	    setOrigPosition();
    	this.setAbsoluteSpeed(.1);
		DumbieLogicC logic = new DumbieLogicC();
		addComponent(logic);
	}

	@Override
	public void changeAngle() {
    	this.setAngle(Math.floor((Math.random()*4))*90);
    	this.setAbsoluteSpeed(.1);		
	}

}
