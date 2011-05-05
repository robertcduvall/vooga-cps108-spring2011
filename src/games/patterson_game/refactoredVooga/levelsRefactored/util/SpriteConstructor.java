package games.patterson_game.refactoredVooga.levelsRefactored.util;

import games.patterson_game.refactoredVooga.levelsRefactored.AbstractLevel;
import games.patterson_game.refactoredVooga.reflection.Reflection;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.Sprite;
import games.patterson_game.refactoredVooga.sprites.spritegroups.SpriteGroup;
import games.patterson_game.refactoredVooga.util.buildable.components.IComponent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;



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
	private List<IComponent> spriteComponents;
	
	public SpriteConstructor(AbstractLevel level, ConverterRack converterRack, 
			String className, String spriteGroup, String imageName) {
		this.converterRack = converterRack;
		this.className = className;
		this.spriteGroup = spriteGroup;
		this.level = level;
		this.imageName = imageName;
		
		spriteComponents = new ArrayList<IComponent>();
	}
	
	/**
	 * Construct the sprite and add it to the sprite group.
	 * @param assignments the list of constructor arguments as a string.
	 * @return
	 */
	//FIXME: Refactor into single method.
	public Sprite construct(List<String> assignments) {
		//BufferedImage image = (BufferedImage) converterRack.convert(BufferedImage.class, imageName);
		
		assignments.add(0, imageName);
		
		Sprite sprite = converterRack.constructInstance(className, assignments);
		for(IComponent component : spriteComponents) {
			sprite.addComponent(component);
		}
		SpriteGroup<Sprite> group = level.getSpriteGroup(spriteGroup);
		group.addSprites(sprite);
		return sprite;
	}
	
	public Sprite construct(Object ... assignments) {
		BufferedImage image = (BufferedImage) converterRack.convert(BufferedImage.class, imageName);

		// Put image and the rest of the assignments into a single array.
		Object[] arguments = new Object[assignments.length+1];
		arguments[0] = image;
		for(int i = 0; i < assignments.length; i++) {
			arguments[i+1] = assignments[i];
		}
		Sprite sprite = (Sprite) Reflection.createInstance(className, arguments);
		for(IComponent component : spriteComponents) {
			sprite.addComponent(component);
		}
		SpriteGroup<Sprite> group = level.getSpriteGroup(spriteGroup);
		group.addSprites(sprite);
		return sprite;
	}

	public String getTargetName() {
		return className;
	}

	public String getSpriteGroup() {
		return spriteGroup;
	}

	public void addComponent(IComponent component) {
		spriteComponents.add(component);
	}
}
