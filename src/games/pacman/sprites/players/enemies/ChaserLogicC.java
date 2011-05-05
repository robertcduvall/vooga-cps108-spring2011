package games.pacman.sprites.players.enemies;

import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.improvedsprites.interfaces.ISpriteUpdater;
import vooga.sprites.spritebuilder.components.basic.TargetC;

public class ChaserLogicC  extends TargetC implements ISpriteUpdater
{

	@Override
	public void update(Sprite s, long elapsedTime) {
		boolean moveRight = (myTarget.getX()-s.getX())>=0;
		boolean moveUp =    (myTarget.getY()-s.getX())>0;
		((Chaser)s).oldHeading=s.getAngle();

		if(moveRight&&moveUp){
			s.setAngle(Math.floor(Math.random()*2)*270); //0 or 270 //right or up
		}else if(moveRight&&!moveUp){
			s.setAngle((Math.floor(Math.random()*2))*90); //0 or 90 //right or down
		}else if(!moveRight&&moveUp){
			s.setAngle(Math.floor(Math.random()*2+2)*90); //180 or 270 //left or up
		}else if(!moveRight&&!moveUp){
			double number =Math.floor(Math.random()*2+1)*90;
			s.setAngle(number); //90 or 180 //down or left
		}
	}


}
