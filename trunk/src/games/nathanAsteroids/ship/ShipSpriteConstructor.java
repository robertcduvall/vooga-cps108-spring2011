package games.nathanAsteroids.ship;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import vooga.levels.AbstractLevel;
import vooga.levels.util.ConverterRack;
import vooga.reflection.Reflection;

import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;
import vooga.util.buildable.components.IComponent;

/**
 * Factory class to construct a given sprite given some set of assignments.
 * @author SterlingDorminey
 *
 */
public class ShipSpriteConstructor {
	private String className;
	private String imageName;
	private ConverterRack converterRack;
	private List<IComponent> spriteComponents;
	private int mass;
	private int hp;
	private int def;
	
	public ShipSpriteConstructor(ConverterRack converterRack, 
			String className,String imageName, int mass, int hp, int def) {
		this.converterRack = converterRack;
		this.className = className;
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
		return sprite;
	}
	
	public Sprite construct(Object ... assignments) {
		BufferedImage image = (BufferedImage) converterRack.convert(BufferedImage.class, imageName);

		// Put image and the rest of the assignments into a single array.
		Object[] arguments = new Object[assignments.length+4];
		arguments[0] = image;
		for(int i = 0; i < assignments.length-1; i++) {
			arguments[i+1] = assignments[i];
		}
		arguments[assignments.length-1] = mass;
		arguments[assignments.length] = hp;
        arguments[assignments.length+1] = def;
        arguments[assignments.length+2] = assignments[assignments.length-2];
        arguments[assignments.length+3] = assignments[assignments.length-1];
		Sprite sprite = (Sprite) Reflection.createInstance(className, arguments);
		for(IComponent component : spriteComponents) {
			sprite.addComponent(component);
		}
		return sprite;
	}

	public String getTargetName() {
		return className;
	}

	public void addComponent(IComponent component) {
		spriteComponents.add(component);
	}
}
