package vooga.stats;

/**
 * Generic Stat class. You can customize to any Object class
 * 
 *  @author Chao Chen
 *  @author Yin Xiao
 */

public abstract class AbstractStat<T> 
{	
	private T myStat;
	
	public AbstractStat(T t)
	{
		myStat = t;
	}
		
	public T getStat() 
	{
		return myStat;
	}
	
	public void cheat(T t)
	{
		myStat = t;
	}
	
	public abstract T update();
}