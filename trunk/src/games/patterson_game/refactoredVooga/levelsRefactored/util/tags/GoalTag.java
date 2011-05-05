package games.patterson_game.refactoredVooga.levelsRefactored.util.tags;

import games.patterson_game.refactoredVooga.levelsRefactored.IGoal;
import games.patterson_game.refactoredVooga.levelsRefactored.util.LevelParser;
import games.patterson_game.refactoredVooga.resources.xmlparser.Parser;
import games.patterson_game.refactoredVooga.resources.xmlparser.XMLTag;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


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
		    if(children.item(i).getNodeType() != Node.ELEMENT_NODE) continue;
			args.add(getValue((Element) children.item(i)));
		}
		
		IGoal goal = (IGoal) parser.getConverterRack().constructInstance(className, args);
		parser.setGoal(goal);
	}
}
