package games.nemo.model.rounds;

import com.golden.gamedev.object.PlayField;
/**
 * Round is a class that represents what kind of playfield should be shown on screen
 * @author Yin
 *
 */
public abstract class AbstractRound extends PlayField
{
	/**
	 * A method that can catch key pressing
	 * The system required method update should be included in this method
	 * @param elapsedTime
	 * @param up
	 * @param down
	 * @param left
	 * @param right
	 * @param click
	 */
	public abstract void execute(long elapsedTime,
			                     boolean up,
			                     boolean down,
			                     boolean left,
			                     boolean right,
			                     boolean click);
	
	/**
	 * 
	 * @return current win status for this round
	 */
	public abstract boolean isWin();
	
	/**
	 * 
	 * @return current loss status for this round
	 */
	public abstract boolean isLoss();
}
