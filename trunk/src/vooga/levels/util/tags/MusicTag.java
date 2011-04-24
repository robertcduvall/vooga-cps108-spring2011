package vooga.levels.util.tags;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import vooga.levels.IGoal;
import vooga.levels.util.LevelParser;
import vooga.resources.xmlparser.Parser;
import vooga.resources.xmlparser.XMLTag;

public class MusicTag extends XMLTag {

	private static final String MUSIC = "music";
	
	private LevelParser parser;
	
	@Override
	public String getTagName() {
		return MUSIC;
	}
	
	@Override
	public void parse(Parser context, Element xmlElement) {
		String className = xmlElement.getAttribute(CLASS);
		
		NodeList children = xmlElement.getChildNodes();
		List<String> args = new ArrayList<String>();
		for(int i = 0; i < children.getLength(); i++) {
			args.add(getValue((Element) children.item(i)));
		}
		
		IGoal goal = (IGoal) parser.getConverterRack().constructInstance(className, args);
		parser.setGoal(goal);
	}

}
