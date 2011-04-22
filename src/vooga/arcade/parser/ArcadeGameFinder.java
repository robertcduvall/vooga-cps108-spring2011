package vooga.arcade.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Traverses a directory recursively and finds all arcade games within a file
 * system and returns them as a List of ArcadeGameObjects, which is to be used
 * by the view.
 * 
 * @author Ethan Yong-Hui Goh
 */
public class ArcadeGameFinder
{
	private static List<String> fileList = new ArrayList<String>();

	public static List<ArcadeGameObject> findAllArcadeGames(String folderPath)
	{
		List<String> fileList = getAllGameFiles(folderPath);
		List<ArcadeGameObject> arcadeList = new ArrayList<ArcadeGameObject>();
		for (String s : fileList)
		{
			arcadeList.add(XmlIO.getArcadeGameObject(s));
		}

		return arcadeList;
	}

	private static List<String> getAllGameFiles(String folderPath)
	{
		recurseFileSystem(new File(folderPath));
		return fileList;
	}

	private static void recurseFileSystem(File file)
	{
		System.out.println(file.getAbsolutePath());
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