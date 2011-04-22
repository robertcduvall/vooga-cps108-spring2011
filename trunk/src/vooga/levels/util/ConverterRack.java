package vooga.levels.util;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import com.golden.gamedev.Game;

import vooga.core.VoogaGame;
import vooga.resources.images.ImageLoader;

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
	
	// How do we know string is to an image?
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
	
	public ConverterRack(Game g) {
		conversionMap = new HashMap<Class<?>, Converter<?>>();
		
		addConverter(String.class, new StringConverter());
		addConverter(Integer.class, new IntegerConverter());
		addConverter(Boolean.class, new BooleanConverter());
		addConverter(Double.class, new DoubleConverter());
		addConverter(ImageConverter.class, new ImageConverter(g.getImageLoader()));
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
}
