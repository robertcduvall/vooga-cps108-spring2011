package vooga.replay;

import java.awt.Graphics2D;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

/**
 * Instance of a "Replay" which updates and renders directly from a StateTable. 
 * 
 * @author Josue, Chris
 */

public class Replay extends GameObject{
	
	protected StateTable myTable;
	protected int start, stop, time;
	
	public Replay(GameEngine ge, StateTable table, int start, int stop){
		super(ge);
		this.myTable = table;
		this.start = start;
		this.stop = (stop <= table.getTime()) ? stop : table.getTime();
		time = start;
	}
	
	public Replay(GameEngine ge, StateTable table) {
		this(ge,table,0, table.getTime() - 1);
	}

	@Override
	public void initResources() {
	}

	@Override
	public void render(Graphics2D g) {
		myTable.render(g);
	}

	/**
	 * Overriden update updates PlayField directly from global StateTable.
	 */
	@Override
	public void update(long elapsedTime) {
		if(inRange(time)){
			myTable.transformSprite(time);
			time++;
		}
	}
	
	/**
	 * Checks whether your current location in the StateTable is in the 
	 * predetermined range.
	 * 
	 * @param t - Time variable for indices in StateTable
	 * @return Boolean as to whether the time t falls in the predetermined range.
	 */
	public boolean inRange(int t){
		return t >= start && t <=  stop;
	}
	
	public void setStateTable(StateTable st){
		myTable = st;
	}
}
