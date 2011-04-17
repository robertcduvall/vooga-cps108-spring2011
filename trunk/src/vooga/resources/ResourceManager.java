package vooga.resources;

import vooga.core.VoogaGame;
import vooga.resources.images.ImageLoader;
import vooga.resources.xmlparser.Parser;

/**
 * Mega-class that manages all resources.
 * If you want to use resources (which you should), use this class!
 * @author Sterling Dorminey
 *
 */
public class ResourceManager extends Parser {
	
	private ImageLoader imageLoader;
	private VoogaGame game;
	
	/**
	 * Constructs a resource manager given the root game class, and a filename.
	 * @param game the root game class.
	 * @param filename the name of the XML file defining all resources.
	 */
	public ResourceManager(VoogaGame game, String filename) {
		super();
		
		this.game = game;
		
		parse(filename);
	}

	public VoogaGame getGame() {
		return game;
	}
	
	public void setImageLoader(ImageLoader imageLoader)	{
		this.imageLoader = imageLoader;
	}
}
