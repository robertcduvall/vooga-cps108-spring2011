package vooga.resources;

import org.w3c.dom.Element;

import vooga.resources.xmlparser.Parser;
import vooga.resources.xmlparser.XMLTag;

/**
 * Parser for key map definitions.
 * @author Sterling Dorminey
 *
 */
public class KeyMapParser extends Parser {
	
	private class KeyTag extends XMLTag {
		private static final String TAG_NAME = "key";
		private static final String SENSITIVE_ATTR = "sensitive";
		private static final String NAME_ATTR = "name";
		private static final String EVENT_ATTR = "event";
		
		private KeyMap keyMap;
		
		@Override
		public String getTagName() {
			return TAG_NAME;
		}
		
		public KeyTag(KeyMap keyMap) {
			this.keyMap = keyMap;
		}
		
		@Override
		public void parse(Parser context, Element xmlElement) {
			String sensitive = xmlElement.getAttribute(SENSITIVE_ATTR);
			String name = xmlElement.getAttribute(NAME_ATTR);
			String event = xmlElement.getAttribute(EVENT_ATTR);
			
			keyMap.addKeyEvent(event, sensitive, name);
		}
	}
	
	private class RootTag extends XMLTag {
		private static final String TAG_NAME = "keymap";
		
		@Override
		public String getTagName() {
			return TAG_NAME;
		}
	}
	
	public KeyMapParser(KeyMap keyMap) {
		addDefinitions(new RootTag(), new KeyTag(keyMap));
	}
}
