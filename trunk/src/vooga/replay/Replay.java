package replay;

import java.awt.Graphics2D;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

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

	@Override
	public void update(long elapsedTime) {
		if(inRange(time)){
			myTable.transformSprite(time);
			time++;
		}
	}
	
	public boolean inRange(int t){
		return t >= start && t <=  stop;
	}
	
	public void setStateTable(StateTable st){
		myTable = st;
	}
}
