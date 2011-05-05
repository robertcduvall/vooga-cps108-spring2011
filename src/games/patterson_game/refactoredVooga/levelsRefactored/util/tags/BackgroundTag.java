package games.patterson_game.refactoredVooga.levelsRefactored.util.tags;

import games.patterson_game.refactoredVooga.levelsRefactored.util.LevelParser;
import games.patterson_game.refactoredVooga.resources.images.ImageLoader;
import games.patterson_game.refactoredVooga.resources.xmlparser.Parser;
import games.patterson_game.refactoredVooga.resources.xmlparser.XMLTag;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.background.ImageBackground;


public class BackgroundTag extends XMLTag {
	
	private static final String BACKGROUND = "background";
	private static final String TYPE_ATTR = "type";
	
	private LevelParser parser;
	private static Map<String, BackgroundConstructor> constructorMap = null;
	
	private abstract class BackgroundConstructor {
		protected ImageLoader loader;
		public BackgroundConstructor(ImageLoader loader) {
			this.loader = loader;
		}
		
		public abstract Background create(String value);
	}
	
	private class ImageBackgroundConstructor extends BackgroundConstructor {
		public ImageBackgroundConstructor(ImageLoader loader) {
			super(loader);
		}

		@Override
		public Background create(String value) {
			return new ImageBackground(loader.getImage(value));
		}
	}
	
	private class ColorBackgroundConstructor extends BackgroundConstructor {
		public ColorBackgroundConstructor(ImageLoader loader) {
			super(loader);
		}
		
		@Override
		public Background create(String value) {			
			// Use reflection black magic.
			Color color = Color.black;
			try {
				color = (Color) Color.class.getField(value).get(new Color(0));
			} catch (Exception e) {
			}
			return new ColorBackground(color);
		}
	}
	
	
	public BackgroundTag(LevelParser parser, ImageLoader imageLoader) {
		this.parser = parser;
		if(constructorMap == null) {
			constructorMap = new HashMap<String, BackgroundConstructor>();
			constructorMap.put("image", new ImageBackgroundConstructor(imageLoader));
			constructorMap.put("color", new ColorBackgroundConstructor(imageLoader));
		}
	}
	
	@Override
	public String getTagName() {
		return BACKGROUND;
	}

	@Override
	public void parse(Parser context, Element xmlElement) {
		String value = super.getValue(xmlElement);
		String type = xmlElement.getAttribute(TYPE_ATTR);
		
		Background background = constructorMap.get(type).create(value);
		parser.addToBackgroundQueue(background);
	}
}
