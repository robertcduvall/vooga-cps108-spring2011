package vooga.core.event.examples.bubblefishbob.bubbleFish;

import vooga.core.event.examples.bubblefishbob.bonuses.Bonus;

import com.golden.gamedev.object.Sprite;

/**
 * 
 * @author Ethan Yong-Hui Goh
 * 
 */
public class Item
{
	int type;
	private double x;
	private double y;
	double py;
	double vel_y;
	double time_existed;
	Sprite bm;
	private Bonus bonus;
	Item next;

	public Item(int t, double xi, double yi, double pyi, double vel_yi,
			double time, Bonus b)
	{
		type = t;
		x = xi;
		y = yi;
		py = pyi;
		vel_y = vel_yi;
		time_existed = time;
		bonus = b;
		bm = b.getIcon();
	}

	public Item(int t, double xi, double yi, double pyi, double vel_yi,
			double time, Sprite m)
	{
		type = t;
		x = xi;
		y = yi;
		py = pyi;
		vel_y = vel_yi;
		time_existed = time;
		bonus = null;
		bm = m;
	}

	public Bonus getBonus()
	{
		return bonus;
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}
	
	public void setY(double yf)
	{
		y = yf;
	}
}