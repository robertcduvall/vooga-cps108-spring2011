package vooga.resources;

import java.util.HashMap;
import java.util.Map;

import vooga.core.VoogaGame;
import vooga.levels.LevelManager;
import vooga.resources.images.ImageLoader;
import vooga.resources.managertags.*;
import vooga.resources.xmlparser.Parser;
import vooga.resources.xmlparser.XMLTag;

/**
 * Mega-class that manages all resources.
 * If you want to use resources (which you should), use this class!
 * @author Sterling Dorminey
 *
 */
public class ResourceManager extends Parser {
	
	private ImageLoader imageLoader;
	private LevelManager levelManager;
	private VoogaGame game;
	private String xmlFilename;
	
	private Map<Integer, String[]> levelMap;
	
	public class RootTag extends XMLTag {
		@Override
		public String getTagName() {
				return "resources";
		}
	}
	
	/**
	 * Constructs a resource manager given the root game class, and a filename.
	 * @param game the root game class.
	 * @param xmlFilename the name of the XML file defining all resources.
	 */
	public ResourceManager(String xmlFilename, VoogaGame game) {
		super();
		
		this.xmlFilename = xmlFilename;
		this.game = game;
		
		levelMap = new HashMap<Integer, String[]>();
		
		addDefinitions(	new RootTag(),
						new ImageResourceTag(this),
						new LevelTag(this));
		
		levelManager = new LevelManager(game, game.getPlayerGroup());
		
	}

	public VoogaGame getGame() {
		return game;
	}
	
	public void setImageLoader(ImageLoader imageLoader)	{
		this.imageLoader = imageLoader;
	}
	
	/**
	 * Gets the image loader, which is used to load images.
	 */
	public ImageLoader getImageLoader() {
		return imageLoader;
	}
	
	public LevelManager getLevelManager() {
		return levelManager;
	}

	/**
	 * Parse the resource file.
	 */
	public void init() {
		parse(xmlFilename);
	}

	public Map<Integer, String[]> getLevelMap() {
		return levelMap;
	}
}
