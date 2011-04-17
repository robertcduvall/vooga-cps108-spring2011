package vooga.levels.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ColorBackground;

import vooga.levels.AbstractLevel;
import vooga.levels.util.tags.BackgroundTag;
import vooga.levels.util.tags.CollisionManagerTag;
import vooga.levels.util.tags.InstanceTag;
import vooga.levels.util.tags.SpriteTag;
import vooga.resources.xmlparser.ParserException;
import vooga.resources.xmlparser.Parser;
import vooga.resources.xmlparser.XMLTag;

/**
 * XML Level Parser
 * @author Sterling Dorminey
 *
 */
public class LevelParser extends Parser {
	private AbstractLevel level;
	private Map<String, SpriteConstructor> spriteFactoryMap;
	private ConverterRack converterRack;
	
	private static final class LevelTag extends XMLTag {
		public static final String LEVEL = "level";

		@Override
		public String getTagName() {
			return LEVEL;
		}
		
	}
	public LevelParser(AbstractLevel level) {
		super();
		
		this.level = level;
		
		spriteFactoryMap = new HashMap<String, SpriteConstructor>();
		converterRack = new ConverterRack();
		
		super.addDefinitions(new LevelTag(), new BackgroundTag(this),
								new SpriteTag(this), new CollisionManagerTag(this),
								new InstanceTag(this));
	}
	
	public AbstractLevel getLevel() {
		return level;
	}
	
	public void setBackground(String background) {
		//FIXME
		level.setBackground(new ColorBackground(Color.black));
	}
	
	public void addSpriteArchetype(String name, SpriteConstructor factory) {
		spriteFactoryMap.put(name, factory);
	}

	/**
	 * Construct a sprite given the name of its archetype and other assignments for it.
	 * @return an instance of the constructed sprite.
	 */
	public Sprite makeSprite(String name, List<String> otherAssignments) {
		SpriteConstructor factory = spriteFactoryMap.get(name);
		return factory.construct(otherAssignments);
	}
	
	/**
	 * Returns the sprite constructor associated with the given archetype.
	 */
	public SpriteConstructor getSpriteConstructor(String type) {
		return spriteFactoryMap.get(type);
	}
	
	/**
	 * Returns the map of archetype names to sprite constructors.
	 */
	public Map<String, SpriteConstructor> getSpriteConstructorMap() {
		return spriteFactoryMap;
	}
	
	public ConverterRack getConverterRack() {
		return converterRack;
	}
}
