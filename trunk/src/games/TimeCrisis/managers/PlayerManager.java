package games.TimeCrisis.managers;

import games.TimeCrisis.actors.CursorSight;
import games.TimeCrisis.actors.SpritePath;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;

import vooga.core.VoogaGame;
//import vooga.sprites.improvedsprites.Sprite; 
// can't use vooga sprites because they have screwed up orientation drawing

import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.Sprite;

/**
 * @author Troy Ferrell
 *
 */
public class PlayerManager  
{	
	CursorSight mySight;
	
	Sprite cover;
	Sprite reloadImg;
	SpritePath myCoverPath;
	boolean isShowing = false;
	
	ArrayList<Sprite> bullets = new ArrayList<Sprite>();
	private static final int MAX_AMMO = 10;
	private int ammo = MAX_AMMO;
	
	double time = 0;
	int score = 0;
	
	GameFont timerFont;
	GameFont totalScoreFont;
	
	VoogaGame myGame;
	
	public PlayerManager(VoogaGame game)
	{
		myGame = game;
	
		BufferedImage bulletImg = game.getImageLoader().getImage("Bullet0");
		for(int i = 0; i < MAX_AMMO; i++)
		{
			int x = 50 + bulletImg.getWidth()*i - bulletImg.getWidth()/2;
			int y = game.getHeight() - bulletImg.getHeight() - 25;
			bullets.add(new Sprite(bulletImg, x, y));
		}
		
		timerFont = game.fontManager.getFont(new Font("Verdana", Font.PLAIN, 40));
		totalScoreFont = game.fontManager.getFont(new Font("Verdana", Font.PLAIN, 24));
		
		mySight = new CursorSight(game);
		
		BufferedImage img = myGame.getImageLoader().getImage("ReloadImg");
		reloadImg = new Sprite(img, myGame.getWidth()/2 - img.getWidth()/2, myGame.getHeight()/2 - img.getHeight()/2);
		
		cover = new Sprite();
		myCoverPath = new SpritePath(0, 0, 0, 0, 1);
	}
	
	public void loadNewLevel(int lvl)
	{	
		hide();
		reload();
		
		cover.setImage(myGame.getImageLoader().getImage("Level" + lvl + "_Cover"));
		myCoverPath = new SpritePath(0, 0, -cover.getWidth(), 0, 1);
		
		time = myGame.getResourceManager().getBundle().getDouble("PlayerTimeStart" + lvl);
	}
	
	public void damage(double dmg)
	{
		// TODO: allow enemies to fire at user based on probability
	}
	
	public void reload()
	{
		myGame.playSound("src/games/TimeCrisis/resources/sounds/Reload.wav");
		ammo = MAX_AMMO;
	}
	
	public boolean hasAmmo()
	{
		return ammo > 0;
	}
	
	public void fire()
	{
		ammo--;
		myGame.playSound("src/games/TimeCrisis/resources/sounds/gunshot.wav");
	}
	
	public double getTimeLeft()
	{
		return time;
	}
	
	public void addScore(int points)
	{
		score += points;
	}
	
	public void show()
	{
		isShowing = true;
	}
	
	public void hide()
	{
		reload();
		isShowing = false;
	}
	
	public boolean isShowing()
	{
		return isShowing;
	}
	
	public void render(Graphics2D g)
	{
		cover.render(g);
		
		if(!hasAmmo())
			reloadImg.render(g);
		
		for(int i = 0; i < ammo; i++)
			bullets.get(i).render(g);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, myGame.getWidth(), timerFont.getHeight() + totalScoreFont.getHeight());
		
        DecimalFormat df = new DecimalFormat("#.##");
        g.setColor(Color.WHITE);
       
		timerFont.drawString(g, df.format(time), 
				myGame.getWidth()/2 - timerFont.getWidth("0000")/2 , 0);
		
		String scoreStr = "Score: " + Integer.toString(score);
		totalScoreFont.drawString(g, scoreStr,
				myGame.getWidth()/2 - totalScoreFont.getWidth("Score:")/2, timerFont.getHeight());
				//myGame.getWidth()/2 - totalScoreFont.getWidth(Integer.toString(score)), myGame.getHeight() - totalScoreFont.getHeight()*2);
		
		mySight.render(g);
	}
	
	public void update(long deltaTime)
	{
    	time -= ((double)deltaTime)/1000.0;
		
    	mySight.update(deltaTime);
    	
		myCoverPath.move(isShowing, deltaTime/250.0);
		// 250.0 affects how quickly the cover retracts
		
		cover.setX( myCoverPath.getX() );
		cover.setY( myCoverPath.getY() );
	}
	
}
