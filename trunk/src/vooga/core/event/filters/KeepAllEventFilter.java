package vooga.core.event.filters;

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
