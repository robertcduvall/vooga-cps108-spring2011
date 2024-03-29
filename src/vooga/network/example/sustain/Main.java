package vooga.network.example.sustain;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;

import javax.management.Query;

import vooga.core.VoogaGame;
import vooga.network.INetworkEngine;
import vooga.network.example.gameGUI.NetworkGame;
import vooga.network.tcpEngine.LocalNetworkEngine;
import vooga.sprites.improvedsprites.AnimatedSprite;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

/**
 * 
 * @author LingZhao Xie
 * @author Hong(Roman) Zhang
 *
 */

public class Main extends VoogaGame implements NetworkGame{

	Sprite plane1;
	Sprite plane2;
	AnimatedSprite explosion1;
	AnimatedSprite explosion2;
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
    long score1 = 0;
    long score2 = 0;
    
    boolean isHost = false;
    int port = 5421;
    String serverIP = "127.0.0.1";
    private INetworkEngine network;
    
    int remainingPlane = 2;
 
    boolean haveInitialized = false;
	
    protected void finalize(){
    	network.disconnect();
    }
    
	@Override
	public void initResources() {
		
			this.setFPS(100);
			
			
			
			Status = BeforeRunningStatus;			//Before Running Status
	
	        // create the background
	        backgr = new ImageBackground(getImage("resources/background3.jpg"), w, h);
	
			plane1 = new Sprite(getImage("resources/plane.png"),w/2-20,h/2);
			
			plane2 = new Sprite(getImage("resources/plane4.png"),w/2+20,h/2);
			
			explosion1 = new AnimatedSprite(getImages("resources/boom.png",16,1),0,0);
			explosion1.setActive(false);
			//explosion1.setAnimationFrame(3, 16);
			
			explosion2 = new AnimatedSprite(getImages("resources/boom.png",16,1),0,0);
			explosion2.setActive(false);
			//explosion2.setAnimationFrame(3, 16);
			
			bullets = new SpriteGroup("bullets");
			planes = new SpriteGroup("planes");
			planes.add(plane1);
			planes.add(plane2);
			
			bulletArr = new ArrayList<Sprite>();
			for (int i = 0; i < MaxBulletSize; i++){
				Sprite bullet = new Sprite(getImage("resources/bullet3.png"),-20,-20);
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
	
	        remainingPlane = 2;
	        
	        if (!haveInitialized){
	        	haveInitialized = true;

	        network = new LocalNetworkEngine(port);
	        if(isHost){
	        	network.createHost(false, false);
	        }else{
	        	network.connect(serverIP);
	        }
		}
	}

	@Override
	public void render(Graphics2D arg0) {
		if (Status == BeforeRunningStatus){
			backgr.render(arg0);
			font.drawString(arg0, "Survive Timer: 00:00:000", 10, 10);
			font.drawString(arg0, "Try to survive as long as possible", w/2 - 120, h/2 - 50);
			if (isHost)
				font.drawString(arg0, "Press Enter to start the game", w/2 - 100, h/2);
			else
				font.drawString(arg0, "Wait for the host to start the game", w/2 - 150, h/2);
		}
		else if (Status == AtRunningStatus){
			backgr.render(arg0);
			planes.render(arg0);
			bullets.render(arg0);
			explosion1.render(arg0);
			explosion2.render(arg0);
			
			
			//draw stopwatch
			font.drawString(arg0, "Survive Timer: " + millisecToString(surviveTime), 10, 10);

		}
		else if (Status == AfterRunningStatus){
			backgr.render(arg0);
			bullets.render(arg0);
			explosion1.render(arg0);
			explosion2.render(arg0);
			font.drawString(arg0, "Game Over", w/2 - 50, h/2 - 50);
			if (isHost)
				font.drawString(arg0, "Survive Time: " + millisecToString(score1), w/2 - 100, h/2);
			else
				font.drawString(arg0, "Survive Time: " + millisecToString(score2), w/2 - 100, h/2);
		}
	}

	@Override
	public void update(long arg0) {		
		if (Status == BeforeRunningStatus){
			initResources();
			if (isHost){
				if (keyPressed(KeyEvent.VK_ENTER)){
					Status = AtRunningStatus;
					startTime = System.currentTimeMillis();
					network.send("GameStart");
				}
			}
			else{
//				String command = receivedCommands.poll();
				List<Object> receivedCommands = network.update();
				for (Object commands : receivedCommands)
					if (((String)commands).equals("GameStart")){
						Status = AtRunningStatus;
						startTime = System.currentTimeMillis();
					}
			}
		}
		else if (Status == AtRunningStatus){
			//backgr.update(arg0);
			planes.update(arg0);
			bullets.update(arg0);
			explosion1.update(arg0);
			explosion2.update(arg0);
			
			//setup stopwatch
			surviveTime = System.currentTimeMillis() - startTime;
			
			if (isHost){
				//System.out.println("time: "+arg0);
				//receive the plane2
				//List<Object> receivedCommands = network.update();
				double plane2SpeedX = 0, plane2SpeedY = 0;
				boolean p1Changed = false;
				boolean p2Changed = false;
				
				//check for plane1
				double plane1SpeedX = 0, plane1SpeedY = 0;
				if (keyDown(KeyEvent.VK_LEFT))     plane1SpeedX = -1 * PlaneSpeed; 
		        if (keyDown(KeyEvent.VK_RIGHT))    plane1SpeedX = PlaneSpeed;
		        if (keyDown(KeyEvent.VK_UP))       plane1SpeedY = -1 * PlaneSpeed;
		        if (keyDown(KeyEvent.VK_DOWN))     plane1SpeedY = PlaneSpeed;
		        plane1.setSpeed(plane1SpeedX, plane1SpeedY);
		        
		        checkValidity(plane1);
				
		        //send p1 info 
		        network.send("p1,x=" + plane1.getX());
		        network.send("p1,y=" + plane1.getY());
		        
		        //update for p2 info
				List<Object> receivedCommands = network.update();
				for (Object commands : receivedCommands){
					String command = (String) commands;				
					if (command.startsWith("p2")){
			        	String info = ((String) command).split(",")[1];
			        	if (info.split("=")[0].equals("x"))
			        		plane2.setX(Double.parseDouble(info.split("=")[1]));
			        	if (info.split("=")[0].equals("y"))
			        		plane2.setY(Double.parseDouble(info.split("=")[1]));
					}
				}
		        
		        //update bullets
		        if (t.action(arg0)){
					currentBulletSize++;
		        	insertBullet(currentBulletSize);
				}
				
		        //check collision
				collisionType.checkCollision();
		        
		        //send command to client
			}
			//receive update for client
			else{
		        //check for plane 2
		        double plane2SpeedX = 0, plane2SpeedY = 0;
				if (keyDown(KeyEvent.VK_LEFT))     plane2SpeedX = -1 * PlaneSpeed; 
		        if (keyDown(KeyEvent.VK_RIGHT))    plane2SpeedX = PlaneSpeed;
		        if (keyDown(KeyEvent.VK_UP))       plane2SpeedY = -1 * PlaneSpeed;
		        if (keyDown(KeyEvent.VK_DOWN))     plane2SpeedY = PlaneSpeed;
		        plane2.setSpeed(plane2SpeedX, plane2SpeedY);
		        
		        checkValidity(plane2);
		        
		        network.send("p2,x=" + plane2.getX());
		        network.send("p2,y=" + plane2.getY());
		      
				List<Object> receivedCommands = network.update();
				for (Object commands : receivedCommands){
					String command = (String) commands;
					if(command.startsWith("p1")){
						String info = command.split(",")[1];
						if (info.split("=")[0].equals("x"))
							plane1.setX(Double.parseDouble(info.split("=")[1]));
						if (info.split("=")[0].equals("y"))
							plane1.setY(Double.parseDouble(info.split("=")[1]));
					}
					if(command.startsWith("b")){
						Sprite bullet = getAvailableSprite();
						bullet.setX(Double.parseDouble(command.split(",")[1]));
						bullet.setY(Double.parseDouble(command.split(",")[2]));
						bullet.setHorizontalSpeed(Double.parseDouble(command.split(",")[3]));
						bullet.setVerticalSpeed(Double.parseDouble(command.split(",")[4]));
		        	}
				}
				//check collision
				collisionType.checkCollision();
		}
	}
		else if (Status == AfterRunningStatus){
			explosion1.update(arg0);
			explosion2.update(arg0);
			bullets.update(arg0);
			//haveInitialized = false;
			if (isHost){
				if (keyPressed(KeyEvent.VK_ENTER)){
					Status = BeforeRunningStatus;
					network.send("GameAboutToStart");
				}
			}
			else{
//				String command = receivedCommands.poll();
				List<Object> receivedCommands = network.update();
				for (Object commands : receivedCommands)
					if (((String)commands).equals("GameAboutToStart")){
						Status = BeforeRunningStatus;
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
				
				//send out the bullet location and velocity
				network.send("b,"+bullet.getX()+","+bullet.getY()+","+bullet.getHorizontalSpeed()+","+bullet.getVerticalSpeed());
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

			if (s1.equals(plane1)) {
				//set explosion
				explosion1.setX(s1.getX());
				explosion1.setY(s1.getY());
				s1.setActive(false);
				s2.setActive(false);
				explosion1.setAnimate(true);
				explosion1.setLoopAnim(false);
				remainingPlane--;
				score1 = System.currentTimeMillis() - startTime;
				
			} else if (s1.equals(plane2)) {
				//set explosion
				explosion2.setX(s1.getX());
				explosion2.setY(s1.getY());
				s1.setActive(false);
				s2.setActive(false);
				explosion2.setAnimate(true);
				explosion2.setLoopAnim(false);
				remainingPlane--;
				score2 = System.currentTimeMillis() - startTime;
			}
			
			if (remainingPlane == 0){
				Status = AfterRunningStatus;
			}
		}
	}

	@Override
	public void updatePlayField(long elapsedTime) {
		
	}
	
	public static void main(String[] args) {
		launchGame(new Main(), new Dimension(640, 480), false);
    }

	@Override
	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public void setIsHost(boolean host) {
		this.isHost = host;
	}

	@Override
	public void setServerIP(String IP) {
		this.serverIP = IP;
	}
}
