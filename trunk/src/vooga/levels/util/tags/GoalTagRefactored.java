package vooga.levels.util.tags;

/**
 * Refactored: Wesley Brown
 * 
 * Pushed collection of arguments up to the xmltag since it is repeated code
 * across multiple tags.
 */

import org.w3c.dom.Element;

import vooga.levels.IGoal;
import vooga.levels.util.LevelParser;
import vooga.resources.xmlparser.Parser;
import vooga.resources.xmlparser.XMLTagRefactored;

public class GoalTagRefactored extends XMLTagRefactored {

	private static final String GOAL = "goal";
	private static final String CLASS = "class";
	
	private LevelParser parser;
	
	public GoalTagRefactored(LevelParser parser) {
		this.parser = parser;
	}
	
	@Override
	public String getTagName() {
		return GOAL;
	}

	@Override
	public void parse(Parser context, Element xmlElement) {
		String className = xmlElement.getAttribute(CLASS);
		IGoal goal = (IGoal) parser.getConverterRack().constructInstance(className, getChildArguments(xmlElement));
		parser.setGoal(goal);
	}
}
