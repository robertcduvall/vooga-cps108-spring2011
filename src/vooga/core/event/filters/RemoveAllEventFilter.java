package vooga.core.event.filters;

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
