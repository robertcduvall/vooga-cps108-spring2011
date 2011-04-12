package Debugger;

import java.util.ArrayList;

public class Player
{
	public int myHeight;
	public int myHealth;
	public String myName;
	public String dontShow;
	public ArrayList<Integer> values;
	
	public Player()
	{
		myHeight = 10;
		myHealth = 100;
		myName = "Jim";
		values = new ArrayList<Integer>();
		values.add(1);
		values.add(2);
	}
}
