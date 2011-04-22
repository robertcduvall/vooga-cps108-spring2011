package vooga.levels.util.tags;

import java.lang.reflect.Constructor;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import vooga.levels.IGoal;
import vooga.levels.util.LevelParser;
import vooga.reflection.Reflection;
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
		Class<?> goalClass = null;
		try {
			goalClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// Get the constructor for the sprite class (assume there's only one for now.)
		// TODO: Handle multiple constructors.
		Constructor<?> goalConstructor = goalClass.getConstructors()[0];
		
		// Iterate over types and convert them.
		Class<?>[] types = goalConstructor.getParameterTypes();
		Object[] params = new Object[types.length];
		
		// Get the list of children and use them for constructor arguments.
		NodeList children = xmlElement.getChildNodes();
		for(int i = 0; i < children.getLength(); i++) {
			Object out = parser.getConverterRack().convert(types[i], getValue((Element) children.item(i)));
			params[i] = out;
		}
		
		// Use reflection to create a new instance of the goal class and add it to the level.
		parser.setGoal((IGoal) Reflection.createInstance(className, params));
	}

}
