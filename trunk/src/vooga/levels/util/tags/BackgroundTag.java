package vooga.levels.util.tags;

import java.awt.image.BufferedImage;

import org.w3c.dom.Element;

import vooga.levels.util.LevelParser;
import vooga.resources.xmlparser.Parser;
import vooga.resources.xmlparser.XMLTag;

public class BackgroundTag extends XMLTag {
	
	private static final String BACKGROUND = "background";
	private LevelParser parser;
	
	public BackgroundTag(LevelParser parser) {
		this.parser = parser;
	}
	
	@Override
	public String getTagName() {
		return BACKGROUND;
	}

	@Override
	public void parse(Parser context, Element xmlElement) {
		String background = super.getValue(xmlElement);
		
		parser.setBackground(background);
	}
}
