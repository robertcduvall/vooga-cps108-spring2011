package games.TimeCrisis.actors;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import vooga.core.VoogaGame;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.util.ImageUtil;

/**
 * @author Troy Ferrell
 *
 */
public class EnemyTarget extends Sprite
{
	private SpritePath myPath;
	
	public int health = 100;
	
	private boolean isShowing = false;

	private ArrayList<Sprite> enemyWounds = new ArrayList<Sprite>();
	
	int showFrequency, showLimit, showCounter = 0; // get from file
	
	VoogaGame myGame;
	
	public EnemyTarget(VoogaGame game, int level, SpritePath path)
	{	
		myGame = game;
		myPath = path;
		
		showFrequency = myGame.getResourceManager().getBundle().getInteger("EnemyShowFrequency" + level);
		showLimit = (int)((1.0 + Math.random())*showFrequency);
		
		int numOfEnemyImgs = myGame.getResourceManager().getBundle().getInteger("NumOfEnemyImgs");
		int woundIndex = (int)(Math.random() * numOfEnemyImgs);
		
		BufferedImage img = myGame.getImageLoader().getImage("EnemyTargetImg" + woundIndex);
		this.setImage(ImageUtil.resize(img, (int)(img.getWidth()*myPath.getScale()),(int)(img.getHeight()*myPath.getScale())));
	}
		
	public void switchShowState()
	{
		if(isShowing)
			hide();
		else
			show();
	}
	
	public void hide()
	{
		isShowing = false;
	}
	
	public void show()
	{
		isShowing = true;
	}
	
	public boolean isShowing()
	{
		return isShowing;
	}
	
	public boolean isAlive()
	{
		return health > 0;
	}
	
	public void damage(int dmg, int x, int y)
	{
		health -= dmg;
		
		int numOfWoundImgs = myGame.getResourceManager().getBundle().getInteger("NumOfWoundImgs");
		int woundIndex = (int)(Math.random() * numOfWoundImgs);
		BufferedImage img = myGame.getImageLoader().getImage("WoundImg" + woundIndex);
		
		enemyWounds.add(new Sprite(img, x - this.getX() - img.getWidth()/2, y - this.getY() - img.getHeight()/2));
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)this.getX(), (int)this.getY(), this.getWidth(), this.getHeight());
	}
	
	public void update(long deltaTime)
	{	 
		showCounter++;
		if(showCounter > showLimit)
		{
			showCounter = 0;
			showLimit = (int)((1.0 + Math.random())*showFrequency);
			switchShowState();
		}
		
		myPath.move(isShowing, deltaTime/250.0);
		
		this.setX( myPath.getX() - this.getWidth()/2 );
		this.setY( myPath.getY() - this.getHeight()/2 );
	}
	
	public void render(Graphics2D arg0)
	{		
		super.render(arg0);
		
		for(Sprite wound : enemyWounds)
		{
			wound.render(arg0, (int)(this.getX() + wound.getX()), (int)(this.getY() + wound.getY()));
		}
	}
}
