package games.patterson_game.refactoredVooga.resources.managertags;

import games.patterson_game.refactoredVooga.resources.ResourceManager;
import games.patterson_game.refactoredVooga.resources.xmlparser.Parser;
import games.patterson_game.refactoredVooga.resources.xmlparser.XMLTag;
import org.w3c.dom.Element;


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
