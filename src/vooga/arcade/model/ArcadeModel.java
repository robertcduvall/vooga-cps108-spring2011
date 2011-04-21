package vooga.arcade.model;

import java.util.ArrayList;
import java.util.List;

import vooga.arcade.parser.ArcadeGameFinder;
import vooga.arcade.parser.ArcadeGameObject;
import vooga.arcade.search.AlphabeticalSort;
import vooga.arcade.search.IArcadeGameListFilter;
import vooga.arcade.search.IArcadeGameListSort;
import vooga.arcade.search.SearchByStringFilter;

public class ArcadeModel
{
	private List<ArcadeGameObject> masterArcadeGameList;
	private List<ArcadeGameObject> masterFavoritesList;

	private List<ArcadeGameObject> currentGameList;
	private List<ArcadeGameObject> currentFavoritesList;

	// private VoogaUser currentUser;

	private IArcadeGameListSort currentSort;
	private IArcadeGameListFilter currentFilter;

	public ArcadeModel()
	{
		masterArcadeGameList = new ArrayList<ArcadeGameObject>();
		masterFavoritesList = new ArrayList<ArcadeGameObject>();
		currentFavoritesList = new ArrayList<ArcadeGameObject>();
		currentGameList = new ArrayList<ArcadeGameObject>();
		currentSort = new AlphabeticalSort();
		currentFilter = new SearchByStringFilter();
		loadGameList();
		filterArcadeGameList("title", null);
		filterFavoritesList("title", null);
	}

	private void loadGameList()
	{
		// TODO: Change the Path thing!
		masterArcadeGameList = ArcadeGameFinder
				.findAllArcadeGames("src/vooga/arcade/parser");
	}

	public void filterArcadeGameList(String tag, String[] query)
	{
		String[] ar = { "" };
		if (query == null)
			query = ar;
		currentGameList = currentSort
				.getSortedList(currentFilter.getFilteredList(
						masterArcadeGameList, tag, query), tag);
	}

	public void filterFavoritesList(String tag, String[] query)
	{
		String[] ar = { "" };
		if (query == null)
			query = ar;
		currentFavoritesList = currentSort.getSortedList(
				currentFilter.getFilteredList(masterFavoritesList, tag, query),
				tag);
	}

	public List<ArcadeGameObject> getCurrentGameList()
	{
		return currentGameList;
	}

	public List<ArcadeGameObject> getFavoritesList()
	{
		return currentFavoritesList;
	}

}
