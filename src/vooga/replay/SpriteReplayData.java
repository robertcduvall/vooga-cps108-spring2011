package vooga.replay;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import vooga.sprites.improvedsprites.Sprite;


/**
 * Encapsulates Sprite data for recording.
 * 
 * @author Josue, Chris
 */
@SuppressWarnings("serial")
public class SpriteReplayData implements Serializable {

	
	private boolean isActive;
	private Point2D.Double myPoint;
	private BufferedImageSerialData myImage;

	public SpriteReplayData(Sprite s, boolean isActive) {
		myPoint = new Point2D.Double(s.getX(), s.getY());
		myImage = new BufferedImageSerialData(s.getImage());
		this.isActive = isActive;
	}

	public BufferedImage getImage() {
		return myImage.getImage();
	}

	public Sprite transformSprite(Sprite s) {
		s.setLocation(myPoint.getX(), myPoint.getY());
		s.setActive(isActive);
		s.setImage(getImage());
		return s;
	}
}
