package vooga.resources.managertags;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import vooga.resources.ResourceManager;
import vooga.resources.xmlparser.XMLTag;
import vooga.resources.xmlparser.Parser;

/**
 * Represents the resource bundle XML entry in the system.
 * @author Sterling Dorminey
 *
 */
public class StringsTag extends XMLTag {
	private static final String TAG_NAME = "strings";
	private ResourceManager manager;
	
	@Override
	public String getTagName() {
		return TAG_NAME;
	}

	public StringsTag(ResourceManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void parse(Parser context, Element xmlElement) {
		NodeList children = xmlElement.getChildNodes();
		for(int i = 0; i < children.getLength(); i++) {
			if(!(children.item(i) instanceof Element)) continue;
			Element child = (Element) children.item(i);
			String key = child.getNodeName();
			String value = getValue(child);
			manager.getBundle().addEntry(key, value);
		}
	}
}
