package vooga.resources;

import java.util.HashMap;
import java.util.Map;
import vooga.core.VoogaGame;
import vooga.resources.images.ImageLoader;
import vooga.resources.managertags.ImageResourceTag;
import vooga.resources.managertags.LevelTag;
import vooga.resources.xmlparser.Parser;
import vooga.resources.xmlparser.XMLTag;

/**
 * Mega-class that manages all resources.
 * If you want to use resources (which you should), use this class!
 * @author Sterling Dorminey
 *
 */
public class ResourceManager extends Parser {
	
	private static final String RESOURCE_FILENAME = "resources.xml";
    private ImageLoader imageLoader;
    private String myFilename;
	
	private Map<Integer, String[]> levelMap;
	
	public class RootTag extends XMLTag {
		@Override
		public String getTagName() {
				return "resources";
		}
	}


	public ResourceManager (Class<? extends VoogaGame> gameClass)
    {
	    this(gameClass.getResource(RESOURCE_FILENAME).getPath());
    }
	
	public ResourceManager (String resourceFilePath)
	{
        super();
        myFilename = resourceFilePath;
        levelMap = new HashMap<Integer, String[]>();
        addDefinitions( new RootTag(),
                        new ImageResourceTag(this),
                        new LevelTag(this));
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

	/**
	 * Parse the resource file.
	 */
	public void init() {
		parse(myFilename);
	}

	public Map<Integer, String[]> getLevelMap() {
		return levelMap;
	}
}
