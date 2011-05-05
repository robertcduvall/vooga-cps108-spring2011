package vooga.replay;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import vooga.core.VoogaGame;
import vooga.core.event.EventLayer;
import vooga.core.event.IEventHandler;
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
	protected VoogaGame game;
	/**
	 * Constructs a Replay Object.
	 * 
	 * @param ge - The Main GameEngine
	 * @param table - The StateTable in which Replay will read from
	 * @param start - A flag point denoting where in the StateTable Replay will begin to read
	 * @param stop - A flag point denoting where in the StateTable Replay will finish reading from the StateTable
	 */
	private Replay(VoogaGame game, StateTable table, int start, int stop, int time) {
		//super(ge);
		this.myTable = table;
		this.start = start;
		this.stop = (stop <= time) ? stop : time;
		time = start;
		this.game = game;
		this.game.getEventManager().pushFilter();
		this.game.removeEventHandlers("Input");
		this.game.removeEventHandlers("Method");
		initEvents();
	}
	public void initEvents(){
		this.game.addEveryTurnEvent("Method.updateFromTable", new IEventHandler(){
			@Override
			public void handleEvent(Object o){
				update();
			}
		});
		this.game.registerEventHandler("Method.returnBack", new IEventHandler(){
			@Override
			public void handleEvent(Object o){
				game.getEventManager().popFilter();
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
	public Replay(VoogaGame game, StateTable table, int time) {
		this(game, table, 0, time - 1, time);
	}


	/**
	 * Overriden update updates PlayField directly from global StateTable.
	 */
	public void update() {
		if (inRange(time) && !(game.keyDown(KeyEvent.VK_P))) {
			myTable.transformSpritesToState(time);
			time = game.keyDown(KeyEvent.VK_Q) ? Math.max(--time,start) : Math.min(++time,stop);
		}
		else if(!inRange(time)){
			game.fireEvent(this, "Method.returnBack");
		}
		game.getLevelManager().getCurrentLevel().clearPlayField();
		for(Sprite sprite : myTable.myMap.get(time).keySet()){
			game.getLevelManager().getCurrentLevel().addSprite(sprite);
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
