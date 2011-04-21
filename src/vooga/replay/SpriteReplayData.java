package vooga.replay;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import com.golden.gamedev.object.Sprite;

/**
 * Encapsulates Sprite data for recording.
 * 
 * @author Josue, Chris
 */
public class SpriteReplayData implements Serializable {

	
	private boolean isActive;
	private Point2D.Double myPoint;
	private BufferedImageSerialData myImage;

	public SpriteReplayData(Sprite s, boolean isActive) {
		myPoint = new Point2D.Double(s.getX(), s.getY());
		myImage = new BufferedImageSerialData(s.getImage());
		this.isActive = isActive;
	}

	public double getX() {
		return myPoint.getX();
	}
	
	public boolean isActive(){
		return isActive;
	}

	public double getY() {
		return myPoint.getY();
	}

	public BufferedImage getImage() {
		return myImage.getImage();
	}

}
