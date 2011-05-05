package games.patterson_game.refactoredVooga.levelsRefactored.util.tags;

import games.patterson_game.refactoredVooga.levelsRefactored.util.LevelParser;
import games.patterson_game.refactoredVooga.resources.xmlparser.Parser;
import games.patterson_game.refactoredVooga.resources.xmlparser.XMLTag;
import org.w3c.dom.Element;


/**
 * 
 * @author WesleyBrown
 *
 */
public class MusicTag extends XMLTag {

	private static final String MUSIC = "music";
	private static final String FILE = "file";
	
	private LevelParser parser;
	
	public MusicTag(LevelParser parser) {
		this.parser = parser;
	}
	
	@Override
	public String getTagName() {
		return MUSIC;
	}
	
	@Override
	public void parse(Parser context, Element xmlElement) {
		String filename = xmlElement.getAttribute(FILE);
		
		parser.addToMusicQueue(filename);
	}

}
