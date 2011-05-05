package games.patterson_game.refactoredVooga.replay;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

/**
 * Instance of a "Replay" which updates and renders directly from a StateTable.
 * 
 * @author Josue, Chris
 */

public class Replay extends GameObject {

	protected StateTable myTable;
	protected int start, stop, time;
	/**
	 * Constructs a Replay Object.
	 * 
	 * @param ge - The Main GameEngine
	 * @param table - The StateTable in which Replay will read from
	 * @param start - A flag point denoting where in the StateTable Replay will begin to read
	 * @param stop - A flag point denoting where in the StateTable Replay will finish reading from the StateTable
	 */
	private Replay(GameEngine ge, StateTable table, int start, int stop, int time) {
		super(ge);
		this.myTable = table;
		this.start = start;
		this.stop = (stop <= time) ? stop : time;
		time = start;
	}
	/**
	 * Constructs a Replay Object with default flag points starting from the beginning of the StateTable and 
	 * ending at the end of the StateTable 
	 * 
	 * @param ge
	 * @param table
	 */
	public Replay(GameEngine ge, StateTable table, int time) {
		this(ge, table, 0, time - 1, time);
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
		if (inRange(time) && !(keyDown(KeyEvent.VK_P))) {
			myTable.transformSpritesToState(time);
			time = keyDown(KeyEvent.VK_Q) ? Math.max(--time,start) : Math.min(++time,stop);
		}
	}

	/**
	 * Checks whether your current location in the StateTable is in the
	 * predetermined range.
	 * 
	 * @param t
	 *            - Time variable for indices in StateTable
	 * @return Boolean as to whether the time t falls in the predetermined
	 *         range.
	 */
	public boolean inRange(int t) {
		return t >= start && t <= stop;
	}

	public void setStateTable(StateTable st) {
		myTable = st;
	}
}
