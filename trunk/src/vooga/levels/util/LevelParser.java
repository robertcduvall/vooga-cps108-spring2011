package vooga.levels.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.golden.gamedev.object.Background;

import vooga.core.VoogaGame;
import vooga.levels.AbstractLevel;
import vooga.levels.IGoal;
import vooga.levels.util.tags.*;
import vooga.resources.xmlparser.Parser;
import vooga.resources.xmlparser.ParserException;
import vooga.resources.xmlparser.XMLTag;
import vooga.sprites.improvedsprites.Sprite;

/**
 * XML Level Parser
 * @author Sterling Dorminey & Wesley Brown
 *
 */
public class LevelParser extends Parser {
	private AbstractLevel level;
	private VoogaGame game;
	private Map<String, SpriteConstructor> spriteFactoryMap;
	private ConverterRack converterRack;
	
	private static final class LevelTag extends XMLTag {
		public static final String LEVEL = "level";

		@Override
		public String getTagName() {
			return LEVEL;
		}
		
	}
	
	/**
	 * Reads level XML file, converts information to appropriate classes, and 
	 * adds information to the level for future use.
	 * @param level current level class receiving information form XML file
	 * @param game current game
	 */
	public LevelParser(AbstractLevel level, VoogaGame game) {
		super();
		
		this.level = level;
		this.game = game;
		
		spriteFactoryMap = new HashMap<String, SpriteConstructor>();
		converterRack = new ConverterRack(game);
		
		super.addDefinitions(	new LevelTag(), 
								new BackgroundTag(this, game.getImageLoader()),
								new SpriteTag(this), 
								new CollisionManagerTag(this),
								new InstanceTag(this),
								new GoalTag(this),
								new ComponentTag(this),
								new MusicTag(this),
								new StringsTag(level));
	}
	
	
	/**
	 * Get the level this parser is attached to
	 * @return level parser
	 */
	public AbstractLevel getLevel() {
		return level;
	}
	
	
	/**
	 * Get the current game
	 * @return vooga gam
	 */
	public VoogaGame getGame() {
		return game;
	}
	
	
	/**
	 * Adds new background to queue of backgrounds for the level
	 * @param background 
	 */
	public void addToBackgroundQueue(Background background) {
		level.addToBackgroundQueue(background);
	}
	
	
	/**
	 * Adds new music file to queue of music for the level
	 * @param music
	 */
	public void addToMusicQueue(String music) {
		level.addToMusicQueue(music);
	}
	
	
	/**
	 * Sets the level's goal
	 * @param goal goal class for this level
	 */
	public void setGoal(IGoal goal) {
		level.setGoal(goal);
	}
	
	
	/**
	 * Add a new sprite archetype constructor.
	 * @param name name to refer to archetype constructor
	 * @param factory sprite constructor
	 */
	public void addSpriteArchetype(String name, SpriteConstructor factory) {
		spriteFactoryMap.put(name, factory);
	}

	/**
	 * Construct a sprite given the name of its archetype and other assignments for it.
	 */
	public Sprite makeSprite(String name, List<String> assignments) {
		SpriteConstructor factory = spriteFactoryMap.get(name);
		return factory.construct(assignments);
	}
	
	/**
	 * Construct a sprite give the name of its archetype and its arguments
	 * @param name name referring to archetype defined in xml file
	 * @param assignments sprite arguments
	 * @return
	 */
	public Sprite makeSprite(String name, Object ... assignments) {
		SpriteConstructor factory = spriteFactoryMap.get(name);
		return factory.construct(assignments);
	}
	
	/**
	 * Returns the sprite constructor associated with the given archetype.
	 */
	public SpriteConstructor getSpriteConstructor(String type) {
		SpriteConstructor constructor = spriteFactoryMap.get(type);
		if(constructor == null) throw ParserException.SYNTAX_ERROR;
		return constructor;
	}
	
	/**
	 * Returns the map of archetype names to sprite constructors.
	 */
	public Map<String, SpriteConstructor> getSpriteConstructorMap() {
		return spriteFactoryMap;
	}
	
	
	/**
	 * Get the class converter 
	 * @return converterrack
	 */
	public ConverterRack getConverterRack() {
		return converterRack;
	}

}
