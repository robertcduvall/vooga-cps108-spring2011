package games.patterson_game.refactoredVooga.core.event.filters;

/**
 * @author Michael Ansel
 * @author Ethan Goh
 */
public class RemoveAllEventFilter implements IEventFilter
{
	@Override
	public boolean isFiltered(String events)
	{
		return false;
	}
}
