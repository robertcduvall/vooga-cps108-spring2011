package vooga.resources.managertags;

import org.w3c.dom.Element;

import vooga.resources.ResourceManager;
import vooga.resources.images.ImageLoader;
import vooga.resources.xmlparser.Parser;
import vooga.resources.xmlparser.XMLTag;

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
