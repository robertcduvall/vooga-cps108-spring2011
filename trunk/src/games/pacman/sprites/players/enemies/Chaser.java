package games.pacman.sprites.players.enemies;

import games.pacman.sprites.players.PacMan;
import games.pacman.sprites.players.Players;
import java.awt.image.BufferedImage;

import vooga.sprites.improvedsprites.Sprite;

@SuppressWarnings("serial")
public class Chaser extends Players{
	
	public Double oldHeading;
	
	public Chaser(BufferedImage image, int x, int y,PacMan pacman) {
		super(image,x*48+2,y*48+2);
		origX=x*48+2;
	    origY=y*48+2;
	    oldHeading=this.getAngle();
	    setOrigPosition();
    	this.setAbsoluteSpeed(.1);
    	
		ChaserLogicC logic = new ChaserLogicC();
		logic.setTarget(pacman);
		addComponent(logic);
	}

	@Override
	public void changeAngle() {
    	this.setAngle(Math.floor((Math.random()*4))*90);
    	this.setAbsoluteSpeed(.1);		
	}

	@Override
	public void respondToWall() {
	    	this.setAbsoluteSpeed(.1);	
	}
}
