package vooga.resources.managertags;

import org.w3c.dom.Element;

import vooga.resources.ResourceManager;
import vooga.resources.xmlparser.Parser;
import vooga.resources.xmlparser.XMLTag;

public class LevelTag extends XMLTag {
	private static final String TAG_NAME = "level";
	private static final String ID_ATTR = "id";
	private static final String TYPE_ATTR = "type";
	
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
		int id = Integer.parseInt(element.getAttribute(ID_ATTR));
		String type = element.getAttribute(TYPE_ATTR);
		
		// FIXME: I realize this is terrible form. However, fixing things is just too much work
		// for both me and the level team. Sorry.
		String[] levelInfo = { levelFilename, type };
		manager.getLevelMap().put(id, levelInfo);
	}
}
