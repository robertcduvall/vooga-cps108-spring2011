package vooga.levels.util.tags;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import vooga.levels.IGoal;
import vooga.levels.util.LevelParser;
import vooga.resources.xmlparser.Parser;
import vooga.resources.xmlparser.XMLTag;

public class GoalTag extends XMLTag {

	private static final String GOAL = "goal";
	private static final String CLASS = "class";
	
	private LevelParser parser;
	
	public GoalTag(LevelParser parser) {
		this.parser = parser;
	}
	
	@Override
	public String getTagName() {
		return GOAL;
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
