package vooga.levels.util.tags;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import vooga.levels.util.LevelParser;
import vooga.levels.util.SpriteConstructor;
import vooga.resources.xmlparser.Parser;
import vooga.resources.xmlparser.XMLTag;

public class SpriteTag extends XMLTag {
	private static final String GROUP = "group";
	private static final String CLASS = "class";
	private static final String NAME = "name";
	private static final String SPRITE = "sprite";
	private static final String IMAGE = "image";
	
	private LevelParser parser;
	
	@Override
	public String getTagName() {
		return SPRITE;
	}

	public SpriteTag(LevelParser parser) {
		this.parser = parser;
	}
	
	@Override
	public void parse(Parser context, Element xmlElement) {
		String className = xmlElement.getAttribute(CLASS);
		String name = xmlElement.getAttribute(NAME);
		String spriteGroup = xmlElement.getAttribute(GROUP);
		String image = xmlElement.getAttribute(IMAGE);
		
		parser.addSpriteArchetype(name, new SpriteConstructor(parser.getLevel(), 
				parser.getConverterRack(),
				className, spriteGroup, image));
	}
}
