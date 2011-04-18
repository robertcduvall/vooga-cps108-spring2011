package vooga.arcade.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ArcadeGameFinder
{
	private List<String> fileList;
	public ArcadeGameFinder()
	{
		fileList = new ArrayList<String>();
	}

	public List<ArcadeGameObject> findAllArcadeGames(String folderPath)
	{
		List<String> fileList = getAllGameFiles(folderPath);
		List<ArcadeGameObject> arcadeList = new ArrayList<ArcadeGameObject>();
		for(String s: fileList)
		{
			arcadeList.add(GameXMLParser.getGameData(s));	
		}
		
		return arcadeList;
	}
	
	public List<String> getAllGameFiles(String folderPath)
	{
		recurseFileSystem(new File(folderPath));
		return fileList;
	}

	public void recurseFileSystem(File file)
	{
		if (file.isDirectory())
		{
			File allFiles[] = file.listFiles();
			for (File aFile : allFiles)
			{
				recurseFileSystem(aFile);
			}
		}
		else if (file.isFile())
		{
			String path = file.getPath();
			if (path.substring(path.length() - 4, path.length()).equals(".xml"))
				fileList.add(file.getPath());

		}
	}
}