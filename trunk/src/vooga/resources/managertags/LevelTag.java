package vooga.resources.managertags;

import org.w3c.dom.Element;

import vooga.resources.ResourceManager;
import vooga.resources.xmlparser.Parser;
import vooga.resources.xmlparser.XMLTag;

public class LevelTag extends XMLTag {
	private static final String TAG_NAME = "level";
	private ResourceManager manager;
	
	@Override
	public String getTagName() {
		return TAG_NAME;
	}

	public LevelTag(ResourceManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void parse(Parser context, Element element) {
		String levelFilename = getValue(element);
		// TODO: Integrate into ResourceManager.
	}
}
