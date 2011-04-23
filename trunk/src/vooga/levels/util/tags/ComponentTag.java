package vooga.levels.util.tags;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.levels.util.LevelParser;
import vooga.levels.util.SpriteConstructor;
import vooga.resources.xmlparser.Parser;
import vooga.resources.xmlparser.XMLTag;
import vooga.util.buildable.components.*;

/**
 * Represents a component tag.
 * @author Sterling Dorminey
 *
 */
public class ComponentTag extends XMLTag {
	private static final String TAG_NAME = "component";
	private static final String CLASS_ATTR = "class";
	
	private LevelParser parser;
	
	@Override
	public String getTagName() {
		return TAG_NAME;
	}
	
	public ComponentTag(LevelParser parser) {
		this.parser = parser;
	}
	
	@Override
	public void parse(Parser context, Element xmlElement) {
		Element parent = (Element) xmlElement.getParentNode();
		String className = xmlElement.getAttribute(CLASS_ATTR);
		String parentArchetype = parent.getAttribute(SpriteTag.NAME);
		
		// Get arguments.
		List<String> args = new ArrayList<String>();
		NodeList nodes = parent.getChildNodes();
		for(int i = 0; i < nodes.getLength(); i++) {
			String value = getValue((Element) nodes.item(i));
			args.add(value);
		}
		
		IComponent component = parser.getConverterRack().constructInstance(className, args);

		SpriteConstructor archetype = parser.getSpriteConstructor(parentArchetype);
		archetype.addComponent(component);
	}
}
