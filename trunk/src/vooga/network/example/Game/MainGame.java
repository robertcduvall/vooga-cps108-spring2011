package vooga.network.example.Game;
import games.pong.PongGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import vooga.core.VoogaGame;
import vooga.network.INetworkEngine;
import vooga.network.tcpEngine.LocalNetworkEngine;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import com.golden.gamedev.object.sprite.AdvanceSprite;


public class MainGame extends VoogaGame {

	Sprite plane1;
	Sprite plane2;
	ArrayList<Sprite> bulletArr;
	SpriteGroup  bullets;
	SpriteGroup  planes;
	CollisionManager collisionType;
	Timer t;
	Background backgr;
	final static int w = 640;   			// background width
	final static int h = 480;   			// background height
	final static int planesize = 27;
	final static int ballsize = 8;
    int Status;
    int currentBulletSize = 0;
    final static int BeforeRunningStatus = 0;
    final static int AtRunningStatus = 1;
    final static int AfterRunningStatus = 2;
    final static int MaxBulletSize = 1000;
    final static int MaxAllowedBulletEveryInterval = 10;
    final static int BulletGenerationInteval = 500;
    final static double BulletSpeed = 0.1;
    final static double PlaneSpeed = 0.12;
    final static int fromLeft = 0;
    final static int fromUp = 1;
    final static int fromRight = 2;
    final static int fromDown = 3;
    GameFont font;
    
    long startTime = 0;
    long stopTime = 0;
    long surviveTime = 0;
    
    boolean isHost = false;
    INetworkEngine network;
 
    boolean haveInitialized = false;
	
	@Override
	public void initResources() {
		if (!haveInitialized){
			
			haveInitialized = true;
			
			Status = AtRunningStatus;			//Before Running Status
	
	        // create the background
	        backgr = new ImageBackground(getImage("resources/background3.jpg"), w, h);
	
			plane1 = new Sprite(getImage("resources/plane.png"),w/2-20,h/2);
			
			plane2 = new Sprite(getImage("resources/plane4.png"),w/2+20,h/2);
			
			bullets = new SpriteGroup("bullets");
			planes = new SpriteGroup("planes");
			planes.add(plane1);
			planes.add(plane2);
			
			bulletArr = new ArrayList<Sprite>();
			for (int i = 0; i < MaxBulletSize; i++){
				Sprite bullet = new Sprite(getImage("resources/bullet3.png"),-1,-1);
				bulletArr.add(bullet);
				bullets.add(bullet);
			}
			
	        collisionType = new PlayerShotToBulletsCollision();
	        collisionType.setCollisionGroup(planes, bullets);
	
			
			t = new Timer(BulletGenerationInteval);
			
			// Game Font Manager
	        font = fontManager.getFont(getImages("resources/smallfont.png", 8, 12),
	                                   " !\"#$%&'()*+,-./0123456789:;<=>?" +
	                                   "@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_" +
	                                   "`abcdefghijklmnopqrstuvwxyz{|}~~");
	
	
	        //networking part
	        isHost = true;
	        network = new LocalNetworkEngine();
	        
	        //set up host
	        network.createHost(false);
	        
	        //connect to the host
	        //network.connect(hostIP)
		}
	
	}

	@Override
	public void render(Graphics2D arg0) {
//		if (Status == BeforeRunningStatus){
//			backgr.render(arg0);
//			font.drawString(arg0, "Survive Timer: 00:00:000", 10, 10);
//			font.drawString(arg0, "Try to survive as long as possible", w/2 - 120, h/2 - 50);
//			font.drawString(arg0, "Press Enter to start the game", w/2 - 100, h/2);
//		}
//		else if (Status == AtRunningStatus){
//			backgr.render(arg0);
//			plane.render(arg0);
//			bullets.render(arg0);
//			
//			
//			//draw stopwatch
//			font.drawString(arg0, "Survive Timer: " + millisecToString(surviveTime), 10, 10);
//
//		}
//		else if (Status == AfterRunningStatus){
//			font.drawString(arg0, "Game Over", w/2 - 50, h/2 - 50);
//			font.drawString(arg0, "Survive Time: " + millisecToString(surviveTime), w/2 - 100, h/2);
//		}
		
		//currently only render the plane to the screen
		backgr.render(arg0);
		planes.render(arg0);
		
	}

	@Override
	public void update(long arg0) {
//		if (Status == BeforeRunningStatus){
//			initResources();
//			if (keyPressed(KeyEvent.VK_ENTER)){
//				Status = AtRunningStatus;
//				startTime = System.currentTimeMillis();
//			}
//		}
//		else if (Status == AtRunningStatus){
//			//backgr.update(arg0);
//			plane1.update(arg0);
//			bullets.update(arg0);
//			
//			//setup stopwatch
//			surviveTime = System.currentTimeMillis() - startTime;
//			
//			
//			//setup plane action
//			double planeSpeedX = 0, planeSpeedY = 0;
//			if (keyDown(KeyEvent.VK_LEFT))     planeSpeedX = -1 * PlaneSpeed;
//	        if (keyDown(KeyEvent.VK_RIGHT))    planeSpeedX = PlaneSpeed;
//	        if (keyDown(KeyEvent.VK_UP))       planeSpeedY = -1 * PlaneSpeed;
//	        if (keyDown(KeyEvent.VK_DOWN))     planeSpeedY = PlaneSpeed;
//	        plane1.setSpeed(planeSpeedX, planeSpeedY);
//	        
//	        //set inside the border
//	        if (plane1.getX() < 0)                plane1.setX(0);
//	        if (plane1.getX() > w - planesize)    plane1.setX(w - planesize);
//	        if (plane1.getY() < 0)                plane1.setY(0);
//	        if (plane1.getY() > h - planesize)    plane1.setY(h - planesize);
//	       
//			//plane.setBackground(backgr);
//			
//			//bullet.setSpeed(0.1, 0.1);
//			if (t.action(arg0)){
//				currentBulletSize++;
//	        	insertBullet(currentBulletSize);
//			}
//			
//			collisionType.checkCollision();
//
//		}
//		else if (Status == AfterRunningStatus){
//			if (keyPressed(KeyEvent.VK_ENTER)){
//				Status = BeforeRunningStatus;
//			}
//		}
		
		//if isHost, do the processing, update and send
		//otherwise, send and update
		planes.update(arg0);
//		bullets.update(arg0);
		if (isHost){
			//receive the plane2
			List<Object> receivedCommands = network.update();
			double plane2SpeedX = 0, plane2SpeedY = 0;
			for (Object command : receivedCommands){
				if (((String) command).equals("MoveUp"))
					plane2SpeedY = -1 * PlaneSpeed;
				if (((String) command).equals("MoveDown"))
					plane2SpeedY = PlaneSpeed;
				if (((String) command).equals("MoveLeft"))
					plane2SpeedX = -1 * PlaneSpeed;
				if (((String) command).equals("MoveRight"))
					plane2SpeedX = PlaneSpeed;
			}
			plane2.setSpeed(plane2SpeedX, plane2SpeedY);
			
			//check for plane1
			double plane1SpeedX = 0, plane1SpeedY = 0;
			if (keyDown(KeyEvent.VK_LEFT))     plane1SpeedX = -1 * PlaneSpeed;
	        if (keyDown(KeyEvent.VK_RIGHT))    plane1SpeedX = PlaneSpeed;
	        if (keyDown(KeyEvent.VK_UP))       plane1SpeedY = -1 * PlaneSpeed;
	        if (keyDown(KeyEvent.VK_DOWN))     plane1SpeedY = PlaneSpeed;
	        plane1.setSpeed(plane1SpeedX, plane1SpeedY);
	        
	        //check validity
	        checkValidity(plane1);
	        checkValidity(plane2);
	        
	        //send command to client
	        network.send("p1,x=" + plane1.getX());
	        network.send("p1,y=" + plane1.getY());
	        network.send("p2,x=" + plane2.getX());
	        network.send("p2,y=" + plane2.getY());
		}
		else{
			if (keyDown(KeyEvent.VK_LEFT))     network.send("MoveLeft");
	        if (keyDown(KeyEvent.VK_RIGHT))    network.send("MoveRight");
	        if (keyDown(KeyEvent.VK_UP))       network.send("MoveUp");
	        if (keyDown(KeyEvent.VK_DOWN))     network.send("MoveDown");
	        
	        List<Object> receivedCommands = network.update();
	        for (Object command : receivedCommands){
	        	if(((String) command).startsWith("p1")){
	        		String info = ((String) command).split(",")[1];
	        		if (info.split("=")[0].equals("x"))
	        			plane1.setX(Double.parseDouble(info.split("=")[1]));
	        		if (info.split("=")[0].equals("y"))
	        			plane1.setY(Double.parseDouble(info.split("=")[1]));
	        	}
	        	else if (((String) command).startsWith("p2")){
	        		String info = ((String) command).split(",")[1];
	        		if (info.split("=")[0].equals("x"))
	        			plane2.setX(Double.parseDouble(info.split("=")[1]));
	        		if (info.split("=")[0].equals("y"))
	        			plane2.setY(Double.parseDouble(info.split("=")[1]));
	        	}
	        }
	        
		}
	}
	
	private void checkValidity(Sprite plane){
		if (plane.getX() < 0)                plane.setX(0);
        if (plane.getX() > w - planesize)    plane.setX(w - planesize);
        if (plane.getY() < 0)                plane.setY(0);
        if (plane.getY() > h - planesize)    plane.setY(h - planesize);
	}
	
	public static void main(String[] args) {
//        GameLoader game = new GameLoader();
//        game.setup(new MainGame(), new Dimension(640,480), false);
//        game.start();
		launchGame(new MainGame(), new Dimension(640, 480), false);
    }
	
	private String millisecToString(long time){
		if (time >= (long)3600*1000*1000){
			time -= (long)3600*1000*1000;
		}
		String milliseconds = Integer.toString((int)(time % 1000));
		time /= (long)1000;
		String seconds = Integer.toString((int)(time % 60));
		String minutes = Integer.toString((int)(time / 60));
		StringBuffer result = new StringBuffer();
		result.append(minutes.length()==2? minutes: "0"+minutes);
		result.append(":");
		result.append(seconds.length()==2? seconds: "0"+seconds);
		result.append(":");
		for (int i = 0; i < 3 - milliseconds.length();i++)
			result.append("0");
		result.append(milliseconds);
		return result.toString();
	}
	
	//insert num of bullet by random into the space
	private void insertBullet(int num){
		
		if (num > MaxAllowedBulletEveryInterval)
			num = MaxAllowedBulletEveryInterval;
		
		for (int i = 0; i < num; i++){
			//determine where to insert
			int location = (int)(Math.random()*4) % 4; 
			//System.out.println(location);
			Sprite bullet = getAvailableSprite();
			if (bullet != null){
				setRandomLocation(bullet,location);
				setRandomSpeed(bullet,BulletSpeed,location);
			}
		}
	}

	private Sprite getAvailableSprite() {
		for (int i = 0; i < MaxBulletSize; i++){
			Sprite bullet = bulletArr.get(i);
			if (bullet.getX() < 0 || bullet.getY() < 0 || bullet.getX() > (w + planesize) || bullet.getY() > (h + planesize))
				return bullet;
		}
		return null;
	}
	
	private void setRandomSpeed(Sprite obj, double maxSpeed, int location){
		double xSpeed = Math.random() * maxSpeed;
		double ySpeed = Math.sqrt(Math.pow(maxSpeed,2) - Math.pow(xSpeed, 2));
		if (location == fromRight)
			xSpeed *= -1;
		else if (location == fromDown)
			ySpeed *= -1;
		obj.setSpeed(xSpeed, ySpeed);
	}
	
	private void setRandomLocation(Sprite obj, int location){
		if (location == fromUp){
			double x = Math.random() * w;
			obj.setLocation(x, 0);
		}
		else if (location == fromRight){
			double y = Math.random() * h;
			obj.setLocation(w, y);
		}
		else if (location == fromDown){
			double x = Math.random() * w;
			obj.setLocation(x, h);
		}
		else if (location == fromLeft){
			double y = Math.random() * h;
			obj.setLocation(0, y);
		}
		
	}

	public class PlayerShotToBulletsCollision extends BasicCollisionGroup {
		public PlayerShotToBulletsCollision() {
			pixelPerfectCollision = true;
		}

		   public void collided(Sprite s1, Sprite s2) {
		      // we make both of sprites to vanish!
		      s1.setActive(false);
		      s2.setActive(false);
		      Status = AfterRunningStatus;
		   }
	}

	@Override
	public void updatePlayField(long elapsedTime) {
		// TODO Auto-generated method stub
		
	}

}
