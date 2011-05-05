package vooga.arcade.html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import vooga.arcade.parser.ArcadeGameObject;

/**
 * 
 * @author Ethan Goh
 *
 */
public class AboutHTMLPage
{

	private static String[] aboutFields = { "Title", "Genre", "Author",
			"Date Created", "Version", "Language", "Price" };

	public static String generateHTMLFile(ArcadeGameObject ago)
	{
		HTMLGenerator htmlGenerator = new HTMLGenerator();

		for (String s : aboutFields)
		{
			String t = removeAllSpaces(s);
			if (!ago.getData(t.toLowerCase()).isEmpty())
			{
				htmlGenerator.addTag("b", s + ": ");
				htmlGenerator.addText(ago.getData(t.toLowerCase()));
				htmlGenerator.addLineBreak();
			}
		}

		// TODO: FIX THISSSS OAWKEMRLFSDMLKAERJWLKFJSLKDF
		File file = new File("src/vooga/arcade/html/description.html");
		file.delete();
		try
		{
			FileWriter ofstream = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(ofstream);
			out.write(htmlGenerator.toString());
			out.close();
		}
		catch (Exception e)
		{
			System.err.println("WTF FAIL");
		}

		return file.getAbsolutePath();
	}

	private static String removeAllSpaces(String s)
	{
		String[] ar = s.split("\\s+");
		String tr = "";
		for (String t : ar)
			tr += t;
		return tr;
	}
}
