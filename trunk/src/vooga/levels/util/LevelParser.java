package vooga.levels.util;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import vooga.levels.util.tags.BackgroundTag;
import vooga.resources.xmlparser.Parser;
import vooga.resources.xmlparser.XMLTag;

/**
 * XML Level Parser
 * @author Sterling Dorminey
 *
 */
public class LevelParser extends Parser {
	private String background;
	private Map<String, SpriteConstructor> spriteFactoryMap;
	private ConverterRack converterRack;
	
	private static final class LevelTag extends XMLTag {
		public static final String LEVEL = "level";

		@Override
		public String getTagName() {
			return LEVEL;
		}
		
	}
	public LevelParser() {
		super();
		
		spriteFactoryMap = new HashMap<String, SpriteConstructor>();
		converterRack = new ConverterRack();
		
		super.addDefinitions(new LevelTag(), new BackgroundTag(this));
	}
	
	public void setBackground(String background) {
		this.background = background;
	}
	
	public String getBackground() {
		return background;
	}
	
	public void addSprite(String name, SpriteConstructor factory) {
		spriteFactoryMap.put(name, factory);
	}

	public ConverterRack getConverterRack() {
		return converterRack;
	}	
}
