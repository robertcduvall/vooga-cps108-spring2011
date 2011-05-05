package games.patterson_game.refactoredVooga.resources.managertags;

import games.patterson_game.refactoredVooga.resources.ResourceManager;
import games.patterson_game.refactoredVooga.resources.images.ImageLoader;
import games.patterson_game.refactoredVooga.resources.xmlparser.Parser;
import games.patterson_game.refactoredVooga.resources.xmlparser.XMLTag;
import org.w3c.dom.Element;


public class ImageResourceTag extends XMLTag {
	private static final String TAG_NAME = "images";
	
	private ResourceManager manager;
	
	@Override
	public String getTagName() {
		return TAG_NAME;
	}

	public ImageResourceTag(ResourceManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void parse(Parser context, Element xmlElement) {
		ImageLoader imageLoader = new ImageLoader(manager.getGame().bsLoader, xmlElement);
		manager.setImageLoader(imageLoader);
	}
}
