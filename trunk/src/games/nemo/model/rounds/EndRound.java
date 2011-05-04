package games.nemo.model.rounds;

import java.awt.Graphics2D;

import com.golden.gamedev.object.GameFont;

/**
 * If player wins the whole game,
 * this playfield will show up
 * @author Yin
 *
 */
public class EndRound extends AbstractTransitRound
{
	private String myToShow;
	private GameFont myFont;
	
	public EndRound(String resourceFile, String keyword, String toShow, GameFont font) {
		super(resourceFile, keyword, font);
		myToShow = new String(toShow);
		myFont = font;
	}
	
	/**
	 * Show indicated message for users
	 */
	@Override
	public void render(Graphics2D g)
	{
		super.render(g);
		
		myFont.drawString(g, "====================", 340, 307);
        myFont.drawString(g, myToShow, 350, 317);
        myFont.drawString(g, "====================", 340, 327);
	}
	
}
