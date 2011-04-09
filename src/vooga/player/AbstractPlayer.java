package vooga.player;

public abstract class AbstractPlayer implements Cloneable
{
	/**
	 * @author Kevin Tao and Andrea Scripa
	 */
	
	/*
	 * If needed, one extension is to add a history of commands and events
	 */
	
	int playerId;
	long timeOfLastChange;
	long timeOfLastChangeQuery;
	
	public abstract int compareTo(AbstractPlayer p);
	
	public abstract AbstractPlayer clone();
	
	public abstract boolean equals(AbstractPlayer p);
	
	public boolean hasChanged(){
		
		if(timeOfLastChange > timeOfLastChangeQuery){
			timeOfLastChangeQuery = System.nanoTime();
			return true;
		}else{
			timeOfLastChangeQuery = System.nanoTime();
			return false;
		}
	}
	public void changeOccurred(){
		timeOfLastChange = System.nanoTime();
	}
	
}
