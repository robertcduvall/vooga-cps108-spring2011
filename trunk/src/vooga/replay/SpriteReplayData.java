package vooga.replay;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import com.golden.gamedev.object.Sprite;

public class SpriteReplayData implements Serializable{
	
	private Point2D.Double myPoint;
	private BufferedImage myImage;
	
	public SpriteReplayData(Sprite s){
		myPoint = new Point2D.Double(s.getX(),s.getY());
		myImage = s.getImage();
	}
	
	public SpriteReplayData(Sprite s, double x, double y){
		this(s);
		myPoint = new Point2D.Double(x,y);
	}

	public double getX() {
		return myPoint.getX();
	}

	public double getY() {
		return myPoint.getY();
	}
	
	public BufferedImage getImage(){
		return myImage;
	}
	

}
