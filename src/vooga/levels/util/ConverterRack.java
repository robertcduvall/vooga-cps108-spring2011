package vooga.levels.util;

import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vooga.core.VoogaGame;
import vooga.reflection.Reflection;
import vooga.resources.images.ImageLoader;
import vooga.sprites.improvedsprites.Sprite;

/**
 * An extendible class that allows conversion of strings to specified types.
 * @author Sterling Dorminey
 *
 */
public class ConverterRack {
	private Map<Class<?>, Converter<?>> conversionMap;
	
	/* Some built-in converters for common operations. */
	private class StringConverter extends Converter<String> {
		@Override
		public String convert(String input) {
			return input;
		}
	}
	
	private class IntegerConverter extends Converter<Integer> {
		@Override
		public Integer convert(String input) {
			return Integer.parseInt(input);
		}
	}
	
	private class BooleanConverter extends Converter<Boolean> {
		@Override
		public Boolean convert(String input) {
			return input.toLowerCase().equals("true");
		}
	}
	
	private class DoubleConverter extends Converter<Double> {
		@Override
		public Double convert(String input) {
			return Double.parseDouble(input);
		}
	}
	
	private class ImageConverter extends Converter<BufferedImage> {
		private ImageLoader loader;
		
		public ImageConverter(ImageLoader loader) {
			this.loader = loader;
		}
		
		@Override
		public BufferedImage convert(String input) {
			return loader.getImage(input);
		}
	}
	
	public ConverterRack(VoogaGame g) {
		conversionMap = new HashMap<Class<?>, Converter<?>>();
		
		addConverter(String.class, new StringConverter());
		addConverter(Integer.class, new IntegerConverter());
		addConverter(int.class, new IntegerConverter());
		addConverter(Boolean.class, new BooleanConverter());
		addConverter(boolean.class, new BooleanConverter());
		addConverter(Double.class, new DoubleConverter());
		addConverter(double.class, new DoubleConverter());
		addConverter(BufferedImage.class, new ImageConverter(g.getResourceManager().getImageLoader()));
	}
	
	/**
	 * Converts an object from a string into class target.
	 */
	public Object convert(Class<?> target, String input) {
		if(!conversionMap.containsKey(target)) {
			throw new RuntimeException("Unable to convert");
		}
		
		Converter<?> converter = conversionMap.get(target);
		
		return converter.convert(input);
	}
	
	public void addConverter(Class<?> target, Converter<?> converter) {
		conversionMap.put(target, converter);
	}
	
	/**
	 * Multiple convert. Sort of a utility function.
	 * @param targets class types.
	 * @param strings strings to convert.
	 * @return an array of converted objects.
	 */
	public Object[] convert(List<Class<?>> targets, List<String> strings) {
		Object[] objects = new Object[strings.size()];
		
		for(int i = 0; i < strings.size(); i++) {
			objects[i] = convert(targets.get(i), strings.get(i));
		}
		
		return objects;
	}
	
	/**
	 * Construct an object given string assignments.
	 */
	public<T> T constructInstance(String className, List<String> assignments) {
		Class<?> spriteClass;
		try {
			spriteClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			return null;
		}
		
		// Get the constructor for the target class (assume there's only one for now.)
		// TODO: Handle multiple constructors.
		Constructor<?> spriteConstructor = spriteClass.getConstructors()[0];
		
		// Iterate over types and convert them.
		Class<?>[] types = spriteConstructor.getParameterTypes();
		Object[] params = new Object[types.length];
		
		for(int i = 0; i < types.length; i++) {
			Object out = convert(types[i], assignments.get(i));
			params[i] = out;
		}
				
		// Use reflection to create a new instance of the target class.
		if(params.length > 0)
		{
		    return (T) Reflection.createInstance(className, params);
		} else {
            return (T) Reflection.createInstance(className);
		}
	}
}
