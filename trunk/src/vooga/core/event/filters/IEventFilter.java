package vooga.core.event.filters;

import java.util.Map;

/**
 * @author Michael Ansel
 * @author Ethan Goh
 */
public interface IEventFilter
{
	boolean isFiltered(String events);
}
