package games.pacman.sprites.players;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import com.golden.gamedev.util.ImageUtil;

import vooga.core.VoogaGame;
import vooga.sprites.improvedsprites.Sprite;

public class Players extends Sprite{
	protected VoogaGame game;
	public Players (VoogaGame game, double x, double y,BufferedImage image)
    {
        super(image);
        setX(x - getWidth()/2);
        setY(y - getHeight());
        
	}


	@Override
	public void render(Graphics2D g,int x,int y) {
		AffineTransform aTransform = new AffineTransform();
        aTransform.translate((int) this.getX() +width/2, 
                             (int) this.getY()+height/2);
        aTransform.rotate(Math.toRadians(this.getAngle()+90));
        
        aTransform.translate((int) -width/2, 
                             (int) -height/2);
       
        
        if (this.getAngle()==180.0){
        	g.drawImage(ImageUtil.flip(	
        								ImageUtil.resize(
        										image, width, height)),aTransform,null);
        }else 
        	g.drawImage(ImageUtil.resize(image, width, height),aTransform,null);
        super.renderComponents(g, x, y);
        g.setColor(Color.WHITE);
        this.getCollisionShape().render(g);
        g.setColor(Color.BLACK);
	}
	
    public void collided(Sprite spr) {
		this.setAbsoluteSpeed(0);
	    int colWidth =(int) this.getCollisionShape().getWidth();
	    int colHeight=(int) this.getCollisionShape().getHeight();
	    int colX = (int) this.getCollisionShape().getTopLeftCorner().getX();
	    int colY = (int) this.getCollisionShape().getTopLeftCorner().getY();
	    
	    int xOverlap=0;
	    int yOverlap=0;
	    if(this.getAngle()==0){
		    xOverlap = (1*(int) (spr.getCollisionShape().getTopLeftCorner().getX()-(colX+colWidth)))-2;
	    }else if(this.getAngle()==90){
		    yOverlap = 1*(int) (spr.getCollisionShape().getTopLeftCorner().getY()-(colHeight+colY))-2;
	    }else if(this.getAngle()==180){
	    	xOverlap=(int)(spr.getCollisionShape().getTopLeftCorner().getX()+spr.getCollisionShape().getWidth()-colX)+2;
	    }else if(this.getAngle()==270){
	    	yOverlap=(1*(int)(spr.getCollisionShape().getTopLeftCorner().getY()+spr.getCollisionShape().getHeight()-colY))+2;
	    }
	    this.move(xOverlap,yOverlap);
	    System.out.println(this.getAngle()+" "+xOverlap+" "+yOverlap);
	    System.out.println("collided");		
	}
}
