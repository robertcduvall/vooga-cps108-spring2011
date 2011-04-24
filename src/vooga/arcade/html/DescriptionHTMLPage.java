package vooga.arcade.html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class DescriptionHTMLPage
{
	private static HTMLGenerator htmlGenerator = new HTMLGenerator();

	public static String generateHTMLFile(String description)
	{
		htmlGenerator.addTag("p", description);
		
		//TODO: FIX THISSSS OAWKEMRLFSDMLKAERJWLKFJSLKDF
		File file = new File("src/vooga/arcade/html/test.html");
		try
		{	
			FileWriter ofstream = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(ofstream);
			out.write(htmlGenerator.toString());
			out.close();
		}
		catch (Exception e)
		{
		}
		return file.getAbsolutePath();
	}
}
