package vooga.arcade.html;

import java.util.Map;

public class HTMLGenerator
{
	private String header = "<html><body><font face=\"Verdana\">";
	private String footer = "</font></body></html>";
	private StringBuilder htmlBody;

	public HTMLGenerator()
	{
		htmlBody = new StringBuilder();
	}

	public void addTag(String tag, String body)
	{
		htmlBody.append("<" + tag + ">" + body + "</" + tag + ">");
	}

	public void addTagWithAttributes(String tag, String body,
			Map<String, String> attributes)
	{
		htmlBody.append("<" + tag + " ");
		for (String s : attributes.keySet())
		{
			htmlBody.append(s + "=\"" + attributes.get(s) + "\"");
		}
		htmlBody.append(">" + body + "</" + tag + ">");
	}

	public void addImage(String tag, String URL)
	{
		htmlBody.append("<img src=\"" + URL + " />");
	}
	
	public void addLineBreak()
	{
		htmlBody.append("<br />");
	}
	
	public String toString()
	{
		return header + htmlBody.toString() + footer;
	}

	public void addText(String data)
	{
		htmlBody.append(data);
	}
}
