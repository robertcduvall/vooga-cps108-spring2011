package games.bigfish;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import com.golden.gamedev.util.ImageUtil;

import vooga.collisions.shapes.ShapeFactory;
import vooga.collisions.shapes.collisionShapes.CollisionPolygon;
import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.levels.LevelManager;
import vooga.resources.Direction;
import vooga.resources.images.ImageLoader;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;

public class PlayerFish extends FishSprite {

	private final double SWIM_SPEED = 2;
	private int fishEaten;
	private final int GAME_HEIGHT, GAME_WIDTH;
	private LevelManager myManager;
	private ImageLoader myImageLoader;
	
	public PlayerFish(VoogaGame game, int x, int y, int size) {

    	super(game.getImageLoader().getImage("fish"+size,Direction.WEST),x,y,size);
    	myManager = game.getLevelManager();
    	myImageLoader = game.getImageLoader();
    	GAME_HEIGHT = game.getHeight();
    	GAME_WIDTH = game.getWidth();
		

		game.registerEventHandler("Input.User.Down", new IEventHandler() {
			@Override
			
			public void handleEvent(Object o) {
				VerticalSwim(SWIM_SPEED);
			}
		});

		game.registerEventHandler("Input.User.Up", new IEventHandler() {
			@Override
			public void handleEvent(Object o) {
				VerticalSwim(-1*SWIM_SPEED);
			}
		});

		game.registerEventHandler("Input.User.Left", new IEventHandler() {
			@Override
			public void handleEvent(Object o) {
				HorizontalSwim(-1*SWIM_SPEED);
			}
		});

		game.registerEventHandler("Input.User.Right", new IEventHandler() {
			@Override
			public void handleEvent(Object o) {
				HorizontalSwim(SWIM_SPEED);
			}
		});
	}
	
	public void addToEaten(){
		fishEaten ++;
		checkLevelUp();
	}

	private void checkLevelUp() {
		if(fishEaten > Math.pow(mySize, 3)){
			mySize++;
			this.setImage(myImageLoader.getImage("fish"+mySize,Direction.WEST));
			this.setCollisionShape(
					new CollisionPolygon(ShapeFactory.makePolygonFromImage(ImageUtil.resize(image, width,height), 2)));
			if(getX() + getWidth() > GAME_WIDTH){
				setX(GAME_WIDTH - getWidth());
			}
			if(getY() + getHeight() > GAME_HEIGHT){
				setY(GAME_HEIGHT - getHeight());
			}
		}
	}

	private void HorizontalSwim(double d) {
		double newX = getX() + d;
		if(newX<= GAME_WIDTH-getWidth() && newX >= 0)
		this.moveX(d);
	}
	
	private void VerticalSwim(double d) {
		double newY = getY()+d;
		if(newY <=GAME_HEIGHT-getHeight() && newY >= 0)
		this.moveY(d);
	}

	public void lose() {
		this.setLocation(GAME_WIDTH/2,GAME_HEIGHT/2);
		myManager.loadLevel(0);
		
		
	}	
}
