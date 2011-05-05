package games.patterson_game.refactoredVooga.resources.managertags;

import games.patterson_game.refactoredVooga.resources.KeyMapParser;
import games.patterson_game.refactoredVooga.resources.ResourceManager;
import games.patterson_game.refactoredVooga.resources.xmlparser.Parser;
import games.patterson_game.refactoredVooga.resources.xmlparser.XMLTag;
import org.w3c.dom.Element;


/**
 * Tag to dispatch keymap subparser.
 * @author Sterling Dorminey
 *
 */
public class KeyMapTag extends XMLTag {
	private static final String TAG_NAME = "keymap";
	
	private ResourceManager manager;
	
	@Override
	public String getTagName() {
		return TAG_NAME;
	}
	
	public KeyMapTag(ResourceManager manager) {
		this.manager = manager;
	}

	@Override
	public void parse(Parser context, Element xmlElement) {
		new KeyMapParser(manager.getKeyMap()).parseElement(xmlElement);
	}
}
