package vooga.debugger;

public class NodeTest
{
	public NodeTest next;
	public String key;
	
	public NodeTest(String k)
	{
		key = k;
		next = new NodeTest(key+".");
	}
	public String toString()
	{
		return key;
	}
}