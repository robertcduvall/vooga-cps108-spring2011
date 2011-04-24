package vooga.resources;

import java.io.File;
import java.net.URISyntaxException;
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
	
	private Map<Integer, String[]> levelMap;
    private VoogaGame myGame;
    private KeyMap myKeyMap;
	
	public class RootTag extends XMLTag {
		@Override
		public String getTagName() {
				return "resources";
		}
	}


	public ResourceManager (VoogaGame game)
    {
        super();
        myGame = game;
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
	
//	TODO: SoundLoader
//	public SoundLoader getSoundLoader() {
//	    return soundLoader;
//	}
//	
//	public void setSoundLoader(SoundLoader soundLoader)
//	{
//	    this.soundLoader = soundLoader;
//	}

	/**
	 * Parse the resources.xml file.
	 */
	public void parse() {
        try
        {
            parse(new File(myGame.getClass().getResource(RESOURCE_FILENAME).toURI()).getAbsolutePath());
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        // Set level order once we've loaded all the level tags.
        myGame.getLevelManager().setLevelOrder(levelMap);
	}
	
	public KeyMap getKeyMap() {
	    return myKeyMap;
	}
	
	public void setKeyMap(KeyMap keyMap)
	{
	    myKeyMap = keyMap;
	}

	public Map<Integer, String[]> getLevelMap() {
		return levelMap;
	}

    public VoogaGame getGame ()
    {
        return myGame;
    }
}
