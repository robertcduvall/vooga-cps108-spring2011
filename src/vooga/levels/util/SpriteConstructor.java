package vooga.levels.util;

import java.awt.image.BufferedImage;
import java.lang.reflect.*;
import java.util.List;

import vooga.levels.AbstractLevel;
import vooga.reflection.Reflection;

import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

/**
 * Factory class to construct a given sprite given some set of assignments.
 * @author SterlingDorminey
 *
 */
public class SpriteConstructor {
	private String className;
	private String spriteGroup;
	private String imageName;
	private ConverterRack converterRack;
	private AbstractLevel level;
	
	public SpriteConstructor(AbstractLevel level, ConverterRack converterRack, 
			String className, String spriteGroup, String imageName) {
		this.converterRack = converterRack;
		this.className = className;
		this.spriteGroup = spriteGroup;
		this.level = level;
		this.imageName = imageName;
	}
	
	/**
	 * Constructs a sprite, adding it to the appropriate sprite in the level.
	 * @param otherAssignments the assignments to finish the assignment.
	 * @return 
	 */
	public Sprite construct(List<String> otherAssignments) {
		Sprite sprite = constructInstance(otherAssignments);
		// TODO get level so that correct sprite group can be grabbed
		SpriteGroup group = level.getSpriteGroup(spriteGroup);
		group.add(sprite);
		return sprite;
	}
	
	/**
	 * Construct a sprite given other assignments to complete the constructor args.
	 */
	private Sprite constructInstance(List<String> assignments) {
		Class<?> spriteClass;
		try {
			spriteClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			return null;
		}
		
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
		
		BufferedImage image = (BufferedImage) converterRack.convert(BufferedImage.class, imageName);
		
		// Use reflection to create a new instance of the sprite class.
		Sprite sprite = (Sprite) Reflection.createInstance(className, image, params);
		
		return sprite;
	}
	
	public Sprite construct(Object ... assignments) {
		BufferedImage image = (BufferedImage) converterRack.convert(BufferedImage.class, imageName);

		Sprite sprite = (Sprite) Reflection.createInstance(className, image, assignments);
		
		return sprite;
	}

	public String getTargetName() {
		return className;
	}

	public String getSpriteGroup() {
		return spriteGroup;
	}
}
