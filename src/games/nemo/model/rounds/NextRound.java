package games.nemo.model.rounds;

import java.awt.Graphics2D;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.background.ImageBackground;

import games.nemo.util.commands.ImageReader;
import games.nemo.util.resources.ResourceManager;

/**
 * If futher round exists after winnign previous round,
 * this playfield will show up
 * @author Yin
 *
 */
public class NextRound extends AbstractTransitRound 
{
	private GameFont myFont;
	
	public NextRound(String resourceFile, String keyword, GameFont font) {
		super(resourceFile, keyword, font);
		myFont = font;
	}
	
	/**
	 * Show indication for next operation
	 */
	@Override
	public void render(Graphics2D g)
	{
		super.render(g);
		
		myFont.drawString(g, "====================", 340, 307);
        myFont.drawString(g, "Press Enter to start next round!", 350, 317);
        myFont.drawString(g, "====================", 340, 327);
	}
	
}
