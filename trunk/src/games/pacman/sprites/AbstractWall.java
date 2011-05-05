package games.pacman.sprites;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import com.golden.gamedev.util.ImageUtil;

import vooga.sprites.improvedsprites.Sprite;

public abstract class AbstractWall extends Sprite{

	public AbstractWall(BufferedImage image, int x, int y) {
		super(image,x,y);
        this.setAngle(0);
	}
	
	@Override
	public void render(Graphics2D g,int x,int y) {
		AffineTransform aTransform = new AffineTransform();
        aTransform.translate((int) this.getX() +width/2, 
                             (int) this.getY()+height/2);
        aTransform.rotate(Math.toRadians(this.getAngle()));
        aTransform.translate((int) -width/2, 
                             (int) -height/2);
        g.drawImage(ImageUtil.resize(image, width, height),aTransform,null);
        super.renderComponents(g, x, y);
	}

}
