package vooga.arcade.html;

import java.util.Map;

public class HTMLGeneratorTest
{
	public static void main(String[] args)
	{
		DescriptionHTMLPage hg = new DescriptionHTMLPage();
		String s = hg.generateHTMLFile("this is a test");
		System.out.println(s);
		
	}
}
