package vooga.arcade.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vooga.arcade.parser.ArcadeGameObject;

/**
 * Silly Linear Search Algorithm for Search Functionality.
 * 
 * @author Ethan Yong-Hui Goh
 * 
 */
public class SearchByStringFilter implements IArcadeGameListFilter
{
	@Override
	public List<ArcadeGameObject> getFilteredList(
			List<ArcadeGameObject> gameList, String tag, String query[])
	{
		List<ArcadeGameObject> list = new ArrayList<ArcadeGameObject>();
		for (ArcadeGameObject s : gameList)
		{
			String tagName = s.getData(tag).toLowerCase();
			for (String t : query)
			{
				if (tagName.startsWith(t.toLowerCase()))
				{
					list.add(s);
				}
			}
		}
		return list;
	}
}
