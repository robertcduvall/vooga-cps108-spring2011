package vooga.arcade.search;

import java.util.List;

import vooga.arcade.parser.ArcadeGameObject;

/**
 * Filters to shorten the arcade game list.
 * @author Ethan Yong-Hui Goh
 *
 */
public interface IArcadeGameListFilter
{
	List<ArcadeGameObject> getFilteredList(List<ArcadeGameObject> gameList, String tag, String query); 
}
