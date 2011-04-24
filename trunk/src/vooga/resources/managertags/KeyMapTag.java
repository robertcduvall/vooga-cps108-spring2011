package vooga.resources.managertags;

import org.w3c.dom.Element;

import vooga.resources.KeyMapParser;
import vooga.resources.ResourceManager;
import vooga.resources.xmlparser.Parser;
import vooga.resources.xmlparser.XMLTag;

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
