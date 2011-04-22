package vooga.arcade.search;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vooga.arcade.parser.ArcadeGameObject;

/**
 * Alphabetical Sorting for ArcadeGame Listing.
 * @author Ethan Yong-Hui Goh
 *
 */
public class CopyOfAlphabeticalSort implements IArcadeGameListSort
{
	@Override
	public List<ArcadeGameObject> getSortedList(
			List<ArcadeGameObject> gameList, final String tag)
	{
		Collections.sort(gameList, new Comparator<ArcadeGameObject>()
		{
			@Override
			public int compare(ArcadeGameObject o1, ArcadeGameObject o2)
			{
				return o1.getData(tag).compareTo(o1.getData(tag));
			}
		});

		return gameList;
	}
}
