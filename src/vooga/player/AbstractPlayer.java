package vooga.player;

public abstract class AbstractPlayer implements Cloneable
{
	/**
	 * @author Kevin Tao and Andrea Scripa
	 * 
	 * This is the most general player, that all other players derive from.
	 */
	
	/*
	 * If needed, one extension is to add a history of commands and events
	 */
	
	protected int playerId;
	private long timeOfLastChange;
	private long timeOfLastChangeQuery;
	private static int nextPlayerId = 1;
	
	public AbstractPlayer(){
		playerId = nextPlayerId;
		nextPlayerId ++;
	}
	
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
	
//	public boolean isActive()
//	{
//	    return true;
//	}
	
}
