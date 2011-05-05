package games.patterson_game.refactoredVooga.core.event.filters;

/**
 * @author Michael Ansel
 * @author Ethan Goh
 */
public class KeepAllEventFilter implements IEventFilter
{
	@Override
	public boolean isFiltered(String events)
	{
		return true;
	}
}
