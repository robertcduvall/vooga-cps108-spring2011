package vooga.levels.util.tags;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.levels.util.LevelParser;
import vooga.levels.util.PoolDeferredConstructor;
import vooga.resources.xmlparser.Parser;
import vooga.resources.xmlparser.XMLTag;

/**
 * 
 * @author Sterling Dorminey
 *
 */

public class InstanceTag extends XMLTag {
	private static final String TAG_NAME = "instance";
	private static final String TYPE_ATTR = "type";
	
	private LevelParser parser;
	
	@Override
	public String getTagName() {
		return TAG_NAME;
	}
	
	public InstanceTag(LevelParser parser) {
		this.parser = parser;
	}

	@Override
	public void parse(Parser context, Element xmlElement) {
		String type = xmlElement.getAttribute(TYPE_ATTR);
		
		//FIXME: Refactor: consider moving up in the hierarchy.
		NodeList children = xmlElement.getChildNodes();
		List<String> args = new ArrayList<String>(children.getLength());
		for(int i = 0; i < children.getLength(); i++) {
		    if(children.item(i).getNodeType() != Node.ELEMENT_NODE) continue;
			args.add(getValue((Element) children.item(i)));
		}
		
		PoolDeferredConstructor poolObject = new PoolDeferredConstructor(
				parser.getSpriteConstructor(type), args);
		
		parser.getLevel().addToSpritePool(poolObject);
		
	}
}
