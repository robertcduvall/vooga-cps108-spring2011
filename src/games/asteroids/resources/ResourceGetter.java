/**
 * 
 */
package games.asteroids.resources;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.util.ImageUtil;

import vooga.resources.images.ImageLoader;

/**
 * @author Kevin Shun Nathan David C-S
 *
 */
public class ResourceGetter {

	//private static Game dummyGame = new Game(
	
	private static BaseLoader bsLoader = new BaseLoader(new BaseIO(ResourceGetter.class), null);
	
	public static void main (String [] args) throws IOException {
		//bsLoader.getImage("images/asteroid.gif");

		getAsteroidImage();
		getBackgroundImage();
		getBulletImage();
		getExplosionImage();
		getShipImage();
	}
			
	public static BufferedImage getAsteroidImage(){
		
		try {
			return ImageIO.read(new File("src/games/asteroids/resources/images/asteroid.gif"));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static BufferedImage getBackgroundImage(){

		try {
			return ImageIO.read(new File("src/games/asteroids/resources/images/space.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public static BufferedImage getBulletImage(){

		try {
			return ImageIO.read(new File("src/games/asteroids/resources/images/green_bullet.gif"));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static BufferedImage getExplosionImage(){

		try {
			return ImageIO.read(new File("src/games/asteroids/resources/images/explosion.gif"));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static BufferedImage getShipImage(){

		try {
			return ImageIO.read(new File("src/games/asteroids/resources/images/ship.gif"));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
