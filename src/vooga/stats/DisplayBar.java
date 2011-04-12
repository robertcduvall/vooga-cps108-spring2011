package vooga.stats;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * The StatsLayerBar class displays a bar that grows or shrinks with Stat class
 * 
 * @author Chao Chen
 */
public class DisplayBar extends Display 
{
	private static final int DEFAULT_MAXLENGTH = 100;
	private static final int DEFAULT_HEIGHT = 5;
	private static final Color DEFAULT_FILLCOLOR = Color.WHITE;
	private static final Color DEFAULT_BACKGROUNDCOLOR = Color.GREEN;

	private Stat<Integer> myStat;
	private int myMaxScore;
	private int myMaxLength = DEFAULT_MAXLENGTH;
	private Color myColor = DEFAULT_FILLCOLOR;
	private Color myBackgroundColor = DEFAULT_BACKGROUNDCOLOR;
	private int myHeight = DEFAULT_HEIGHT;
	
	/**
	 * Constructs an StatsLayerBar with the given Stat<Integer> and maxScore.
	 * 
	 * @param stat
	 *            The statistic the bar will present.
	 * @param maxScore
	 *            The maximum value of the statistic.
	 */
	
	public DisplayBar(Stat<Integer> stat, int maxScore) 
	{
		myStat = stat;
		myMaxScore = maxScore;
	}
	
	public DisplayBar (Map<String, String> map, DisplayTracker tracker)
	{
		String strMax = map.get("max");
		myMaxScore =  Integer.parseInt(strMax);
		String statLoc = map.get("stat");
		myStat = (Stat<Integer>)tracker.getStat(statLoc);	
		String strLength = map.get("length");
		if(strLength !=null){
			setMaxLength(Integer.parseInt(strLength));
		}
		String strHeight = map.get("height");
		if(strHeight !=null){
			setMaxLength(Integer.parseInt(strHeight));
		}
		setLocation(map);
		String color = map.get("color");
		if(color !=null){
			setColor(DisplayCreator.stringToColor(color));
		}
		String backColor = map.get("backColor");
		if(backColor !=null){
			setBackgroundColor(DisplayCreator.stringToColor(backColor));
		}
	}
	
	
	/**
	 * update the bar on display
	 */
	@Override
	public void update(long t) 
	{
		drawBar();
	}

	
	/**
	 * draw bar on the display according to user's specifics
	 */
	private void drawBar()
	{
		BufferedImage bufferedImage = new BufferedImage(myMaxLength, myHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bufferedImage.createGraphics();
		g2d.setColor(myBackgroundColor);
		g2d.fillRect(0, 0, myMaxLength, myHeight);
		
		double ratio = (double) myStat.getStat() / (double) myMaxScore;

		//only draw the bar when score > 0
		if (ratio > 0) {
			g2d.setColor(myColor);
			g2d.fillRect(0, 0, (int) Math.round(ratio) * myMaxLength, myHeight);
		}
		
		g2d.dispose();
		setImage(bufferedImage);
	}

	
	/**
	 * color of the bar
	 */
	public Color getColor() 
	{
		return myColor;
	}
	

	/**
	 * set new color for the bar
	 */
	public void setColor(Color color) 
	{
		myColor = color == null ? DEFAULT_FILLCOLOR : color;
	}

	
	/**
	 * max length of the bar
	 */
	public int getMaxLength() 
	{
		return myMaxLength;
	}
	

	/**
	 * set max length of the bar
	 */
	public void setMaxLength(int maxLength) 
	{
		myMaxLength = maxLength > 0 ? maxLength : DEFAULT_MAXLENGTH;
	}

	
	/**
	 * get Stat in the bar
	 */
	public Stat<Integer> getStat() 
	{
		return myStat;
	}
	

	/**
	 * set new Stat in the bar
	 */
	public void setStat(Stat<Integer> stat) 
	{
		if(stat != null)  myStat = stat;
	}

	
	/**
	 * max score of the bar
	 */
	public int getMaxScore() 
	{
		return myMaxScore;
	}

	
	/**
	 * set the max score of the bar
	 */
	public void setMaxScore(int maxScore) 
	{
		if(maxScore > 0)  myMaxScore = maxScore;
	}

	
	/**
	 * get the background color of the bar
	 */
	public Color getBackgroundColor() 
	{
		return myBackgroundColor;
	}

	
	/**
	 * set the background color of the bar
	 */
	public void setBackgroundColor(Color backgroundColor) 
	{
		myBackgroundColor = backgroundColor == null ? DEFAULT_BACKGROUNDCOLOR : backgroundColor;
	}

	
	/**
	 * get height of the bar, overrides getHeight() in Sprite
	 */
	@Override
	public int getHeight() 
	{
		return myHeight;
	}

	
	/**
	 * set height of the bar
	 */
	public void setHeight(int height) 
	{
		myHeight = height > 0 ? height : DEFAULT_HEIGHT;
	}
}