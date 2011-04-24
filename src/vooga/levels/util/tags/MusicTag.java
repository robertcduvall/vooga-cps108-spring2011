package vooga.levels.util.tags;

import org.w3c.dom.Element;

import vooga.levels.util.LevelParser;
import vooga.resources.xmlparser.Parser;
import vooga.resources.xmlparser.XMLTag;

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
