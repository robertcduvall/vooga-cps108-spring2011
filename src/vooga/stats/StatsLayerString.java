package vooga.stats;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Map;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.GameFontManager;

/**
 * This class displays label (String) on screen
 * 
 *@author Chao Chen
 */

public class StatsLayerString extends StatsLayer {
	public final static Color DEFAULT_COLOR = Color.WHITE;
	public final static Font DEFAULT_FONT = new Font("mine", Font.PLAIN, 22);
	
	private String myString;
	private GameFont myFont;
	private Color myColor;
	private Font myRealFont;
	
	/**
     * create a String to display as a label
     * @param String
     * @param font
     * @param color
     */
    public StatsLayerString (String str, Font font, Color color)
    {
        myString = str;
        myColor = color;
        GameFontManager fontManager = new GameFontManager();
        myRealFont = font;
        myFont = fontManager.getFont(myRealFont, myColor);
    }

	public StatsLayerString (String str)
	{
		this(str, DEFAULT_FONT, DEFAULT_COLOR);
	}

	public StatsLayerString (String str, Font font)
	{
		this(str, font, DEFAULT_COLOR);
		
	}

	public StatsLayerString (String str, GameFont font)
	{
		myString = str;
		myFont = font;
	}
	
	public StatsLayerString (String str, Color color)
	{
		this(str, DEFAULT_FONT, color);	
	}	
	
	public StatsLayerString (Map<String, String> attributes, OverlayTracker tracker)
	{
		this(attributes.get("label"));
		
		String fontName = attributes.get("fontName");
		if (fontName != null && fontName.startsWith("gameFont")) {
			GameFont gameFont = OverlayCreator.getGameFont(fontName.substring(8));
			if (gameFont != null) {
				setFont(gameFont);
			} 
		} else {
			String fontStyleStr = attributes.get("fontStyle");
			int fontStyle = OverlayCreator.stringToFontStyle(fontStyleStr);
			String fontSizeStr = attributes.get("fontSize");
			int fontSize;
			if (fontSizeStr == null) {
				fontSize = 22;
			} else {
				fontSize = Integer.valueOf(fontSizeStr);
			}
			String fontColorStr = attributes.get("color");
			Color color = OverlayCreator.stringToColor(fontColorStr);
			setFont(new Font(fontName, fontStyle, fontSize));
			setColor(color);
		}	
		setLocation(attributes);
	}
		
	/**
	 * set String font
	 */
	public void setFont (Font font)
	{
		if(myRealFont != null){
			myRealFont = font;
			GameFontManager fontManager = new GameFontManager();
			myFont = fontManager.getFont(font, myColor);
		}
	}
	
	public void setString (String str)
	{
		myString = str;
	}
	
	public void setFont (GameFont font)
	{
		myFont = font;
		myRealFont = null;
	}
	
	/**
	 * set String color
	 */
	public void setColor (Color color)
	{
		if(myRealFont != null){
			myColor = color;
			GameFontManager fontManager = new GameFontManager();
			myFont = fontManager.getFont(myRealFont, color);
		}		
	}
	
	/**
	 * this method is needed by other stat classes
	 */
	public void print (String str, Graphics2D g)
	{		
        createImage(str, g);
	}
	
	private void createImage (String str, Graphics2D g)
	{
		myFont.drawString(g, str, (int) getX(), (int) getY());		
	}

	/**
	 * print the String
	 */
	@Override
	public void render (Graphics2D g)
	{
		print(myString, g);
	}
	
	/**
	 * get the String
	 */
	public String getString ()
	{
		return myString;
	}

	/**
	 * get the width of the string
	 */
	@Override
	public int getWidth ()
	{
		return getTextWidth(myString);
	}
	
	/**
	 * get the height of the string
	 */
	@Override
	public int getHeight ()
	{
		return getTextHeight();
	}	
	
	private int getTextWidth (String str)
	{
	    return myFont.getWidth(str);
	}
	    
	private int getTextHeight ()
	{
	    return myFont.getHeight();
	}
}