package vooga.replay;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import vooga.core.VoogaGame;
import vooga.core.event.EventLayer;
import vooga.core.event.EventManager;
import vooga.core.event.IEventHandler;
import vooga.levels.LevelManager;
import vooga.sprites.improvedsprites.Sprite;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

/**
 * Instance of a "Replay" which updates and renders directly from a StateTable.
 * 
 * @author Josue, Chris
 */

public class Replay {

	protected StateTable myTable;
	protected int start, stop, time;
	protected EventManager em;
	protected LevelManager lm;
	/**
	 * Constructs a Replay Object.
	 * 
	 * @param ge - The Main GameEngine
	 * @param table - The StateTable in which Replay will read from
	 * @param start - A flag point denoting where in the StateTable Replay will begin to read
	 * @param stop - A flag point denoting where in the StateTable Replay will finish reading from the StateTable
	 */
	private Replay(EventManager eventManager, LevelManager levelManager, StateTable table, int start, int stop, int time) {
		this.myTable = table;
		this.start = start;
		this.stop = (stop <= time) ? stop : time;
		time = start;
		this.em = eventManager;
		this.lm = levelManager;
		this.em.pushFilter();
		removeEvents();
		initEvents();
	}
	public void removeEvents(){
		this.em.removeEventHandlers("Input");
		this.em.removeEventHandlers("Method");
		this.em.removeEventHandlers("Level");
		this.em.removeEventHandlers("User");
	}
	public void initEvents(){
		this.em.addEveryTurnEvent("Method.updateFromTable", new IEventHandler(){
			@Override
			public void handleEvent(Object o){
				update();
			}
		});
		this.em.registerEventHandler("Method.returnBack", new IEventHandler(){
			@Override
			public void handleEvent(Object o){
				em.popFilter();
			}
		});
		this.em.registerEventHandler("Input.pause", new IEventHandler(){
			@Override
			public void handleEvent(Object o){
				time = time;
			}
		});
		this.em.registerEventHandler("Input.rewind", new IEventHandler(){
			@Override
			public void handleEvent(Object o){
				time = Math.max(--time,start);
			}
		});
	}
	/**
	 * Constructs a Replay Object with default flag points starting from the beginning of the StateTable and 
	 * ending at the end of the StateTable 
	 * 
	 * @param ge
	 * @param table
	 */
	public Replay(EventManager eventManager, LevelManager levelManager, StateTable table, int time) {
		this(eventManager, levelManager, table, 0, time - 1, time);
	}


	/**
	 * Overriden update updates PlayField directly from global StateTable.
	 */
	public void update() {
		if (inRange(time)) {
			myTable.transformSpritesToState(time);
			time =  Math.min(++time,stop);
		}
		else {
			em.fireEvent(this, "Method.returnBack");
		}
		lm.getCurrentLevel().clearPlayField();
		for(Sprite sprite : myTable.myMap.get(time).keySet()){
			lm.getCurrentLevel().addSprite(sprite);
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
