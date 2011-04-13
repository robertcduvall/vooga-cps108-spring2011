/**
 * 
 */
package games.asteroids.resources;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

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
	
	public static void main (String [] args) {
		bsLoader.getImage("images/asteroid.gif");
	}
			
	public static BufferedImage getAsteroidImage() throws MalformedURLException{
		URL url = new URL("file:src.games.asteroids.resources.asteroid.gif");
		
		return ImageUtil.getImage(url);
	}
	
	public static BufferedImage getBackgroundImage(){
		return null;
		
	}
	
	public static BufferedImage getBulletImage(){
		return null;
	}
	
	public static BufferedImage getExplosionImage(){
		return null;
	}
	
	public static BufferedImage getShipImage(){
		return null;
	}
}
