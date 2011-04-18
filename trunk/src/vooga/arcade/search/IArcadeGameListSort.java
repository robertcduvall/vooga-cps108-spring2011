package vooga.arcade.search;

import java.util.List;

import vooga.arcade.parser.ArcadeGameObject;

/**
 * Sorts to reorder the arcade game list.
 * @author Ethan Yong-Hui Goh
 *
 */
public interface IArcadeGameListSort
{
	List<ArcadeGameObject> getSortedList(List<ArcadeGameObject> gameList, final String tag); 
}
