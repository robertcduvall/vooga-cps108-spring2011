package vooga.stats;

/**
 * Generic Stat class. You can customize to any Object class
 * 
 *  @author Chao Chen
 */
public class Stat<T> {	
	private T myStat;
	
	public Stat(T t){
		myStat = t;
	}
		
	public T getStat() {
		return myStat;
	}
	
	public void setStat(T t){
		myStat = t;
	}
}