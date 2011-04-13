package vooga.debugger;

import java.util.ArrayList;
import java.util.TreeMap;

public class TestMethods
{
	//AbstractCollections and AbstractMaps
	
	public static void main(String args[]) throws SecurityException, NoSuchFieldException
	{
		
		TreeMap<Integer,String> list = new TreeMap<Integer,String>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		list2.add(1);
		//System.out.println(ClassUtil.iterableAsField(list2));
	}
}
