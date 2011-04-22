package vooga.stats.example02.model.rounds;

import java.awt.Graphics2D;

import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.font.BitmapFont;

/**
 * A playfield that shows welcome screen for player
 * @author Yin
 *
 */
public class StartRound extends AbstractTransitRound 
{
	private GameFont myFont;
	
	public StartRound(String resourceFile, String keyword, GameFont font) {
		super(resourceFile, keyword, font);
		myFont = font;
	}
	
	/**
	 * Welcome string is shown in this method
	 */
	@Override
	public void render(Graphics2D g)
	{        
        //super.render(g);
        
        myFont.drawString(g, "====================", 340, 307);
        myFont.drawString(g, "Click mouse to start a new game!", 350, 317);
        myFont.drawString(g, "====================", 340, 327);
        
        super.render(g);
	}
}
