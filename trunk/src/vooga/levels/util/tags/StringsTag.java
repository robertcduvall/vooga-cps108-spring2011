package vooga.levels.util.tags;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import vooga.levels.AbstractLevel;
import vooga.resources.xmlparser.Parser;
import vooga.resources.xmlparser.XMLTag;

/**
 * Represents strings for level-specific bundles.
 * @author Sterling Dorminey
 *
 */
public class StringsTag extends XMLTag {
	private static final String TAG_NAME = "strings";
	private AbstractLevel level;
	
	@Override
	public String getTagName() {
		return TAG_NAME;
	}

	public StringsTag(AbstractLevel level) {
		this.level = level;
	}
	
	@Override
	public void parse(Parser context, Element xmlElement) {
		NodeList children = xmlElement.getChildNodes();
		for(int i = 0; i < children.getLength(); i++) {
			if(!(children.item(i) instanceof Element)) continue;
			Element child = (Element) children.item(i);
			String key = child.getNodeName();
			String value = getValue(child);
			level.getBundle().addEntry(key, value);
		}
	}
}
