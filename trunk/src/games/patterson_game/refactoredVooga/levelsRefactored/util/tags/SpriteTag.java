package games.patterson_game.refactoredVooga.levelsRefactored.util.tags;

import games.patterson_game.refactoredVooga.levelsRefactored.util.LevelParser;
import games.patterson_game.refactoredVooga.levelsRefactored.util.SpriteConstructor;
import games.patterson_game.refactoredVooga.resources.xmlparser.Parser;
import games.patterson_game.refactoredVooga.resources.xmlparser.XMLTag;
import org.w3c.dom.Element;


public class SpriteTag extends XMLTag {
	private static final String GROUP = "group";
	private static final String CLASS = "class";
	public static final String TYPE = "type";
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
		String name = xmlElement.getAttribute(TYPE);
		String spriteGroup = xmlElement.getAttribute(GROUP);
		String image = xmlElement.getAttribute(IMAGE);
		
		parser.addSpriteArchetype(name, new SpriteConstructor(parser.getLevel(), 
				parser.getConverterRack(), className, spriteGroup, image));
	}
}
