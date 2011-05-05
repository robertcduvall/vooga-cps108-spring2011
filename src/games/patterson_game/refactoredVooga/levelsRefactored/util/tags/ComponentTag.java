package games.patterson_game.refactoredVooga.levelsRefactored.util.tags;

import games.patterson_game.refactoredVooga.levelsRefactored.util.LevelParser;
import games.patterson_game.refactoredVooga.levelsRefactored.util.SpriteConstructor;
import games.patterson_game.refactoredVooga.resources.xmlparser.Parser;
import games.patterson_game.refactoredVooga.resources.xmlparser.XMLTag;
import vooga.util.buildable.components.*;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


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
		String parentArchetype = parent.getAttribute(SpriteTag.TYPE);
		
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
