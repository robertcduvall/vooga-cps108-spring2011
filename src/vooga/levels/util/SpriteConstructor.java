package vooga.levels.util;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

import vooga.reflection.Reflection;

import com.golden.gamedev.object.Sprite;

/**
 * Factory class to construct a given sprite given some set of assignments.
 * @author SterlingDorminey
 *
 */
public class SpriteConstructor {
	private String className;
	private String spriteGroup;
	private List<String> partialAssignments;
	private ConverterRack converterRack;
	
	public SpriteConstructor(ConverterRack converterRack, String className,
			String spriteGroup, List<String> partialAssignments) {
		this.converterRack = converterRack;
		this.className = className;
		this.partialAssignments = partialAssignments;
		this.spriteGroup = spriteGroup;
	}
	
	/**
	 * Construct a sprite given other assignments to complete the constructor args.
	 */
	public Sprite construct(List<String> otherAssignments) {
		Class<?> spriteClass;
		try {
			spriteClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			return null;
		}
		
		// Merge partial assignments and other assignments.
		List<String> assignments = new ArrayList<String>();
		assignments.addAll(partialAssignments);
		assignments.addAll(otherAssignments);
		
		// Get the constructor for the sprite class (assume there's only one for now.)
		// TODO: Handle multiple constructors.
		Constructor<?> spriteConstructor = spriteClass.getConstructors()[0];
		
		// Iterate over types and convert them.
		Class<?>[] types = spriteConstructor.getParameterTypes();
		Object[] params = new Object[types.length];
		
		for(int i = 0; i < types.length; i++) {
			Object out = converterRack.convert(types[i], assignments.get(i));
			params[i] = out;
		}
		
		// Use reflection to create a new instance of the sprite class.
		Sprite sprite = (Sprite) Reflection.createInstance(className, params);
		
		return sprite;
	}

	public String getTargetName() {
		return className;
	}
}
