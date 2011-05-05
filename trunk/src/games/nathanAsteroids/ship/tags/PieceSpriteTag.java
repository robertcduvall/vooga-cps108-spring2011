package games.nathanAsteroids.ship.tags;

import games.nathanAsteroids.ship.ShipParser;
import games.nathanAsteroids.ship.ShipSpriteConstructor;
import org.w3c.dom.Element;

import vooga.levels.util.LevelParser;
import vooga.levels.util.SpriteConstructor;
import vooga.resources.xmlparser.Parser;
import vooga.resources.xmlparser.XMLTag;

public class PieceSpriteTag extends XMLTag {
	private static final String GROUP = "group";
	private static final String CLASS = "class";
	public static final String TYPE = "type";
	private static final String SPRITE = "sprite";
	private static final String IMAGE = "image";
    private static final String MASS = "mass";
    private static final String HP = "hp";
    private static final String DEF = "def";
	
	private ShipParser parser;
	
	@Override
	public String getTagName() {
		return SPRITE;
	}

	public PieceSpriteTag(ShipParser parser) {
		this.parser = parser;
	}
	
	@Override
	public void parse(Parser context, Element xmlElement) {
		String className = xmlElement.getAttribute(CLASS);
		String name = xmlElement.getAttribute(TYPE);
		String spriteGroup = xmlElement.getAttribute(GROUP);
		String image = xmlElement.getAttribute(IMAGE);
		int mass = Integer.parseInt(xmlElement.getAttribute(MASS));
        int hp = Integer.parseInt(xmlElement.getAttribute(HP));
        int def = Integer.parseInt(xmlElement.getAttribute(DEF));
		
		parser.addSpriteArchetype(name, new ShipSpriteConstructor( 
				parser.getConverterRack(), className, image, mass, hp, def));
	}
}
