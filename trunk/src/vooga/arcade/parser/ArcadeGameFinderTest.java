package vooga.arcade.parser;

import java.io.File;
import java.util.List;

public class ArcadeGameFinderTest
{
	public static void main(String[] args)
	{

		String folderPath = ".";
		ArcadeGameFinder agf = new ArcadeGameFinder();
		List<String> test = agf.getAllGameFiles(folderPath);
		
		for(String s : test)
		{
			System.out.println(s);
		}
	}
}