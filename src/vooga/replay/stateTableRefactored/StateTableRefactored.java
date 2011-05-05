package vooga.replay.stateTableRefactored;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.core.event.IEventHandler;
import vooga.levels.AbstractLevel;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;
import vooga.replay.Replay;
import vooga.replay.SerialBackground;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

/**
 * Records all the sprites and background that were updated in the game. Used as
 * the medium from which Sprite data is replayed.
 * 
 * @author Josue, Chris
 * 
 * Refactored by Chris
 * 
 */

@SuppressWarnings("serial")
public class StateTableRefactored implements Serializable {

	private Map<Integer, State> myMap;
	private SerialBackground myBackground;
	private int replayTime;
	
	private EventManager myEventManager;
	private AbstractLevel myLevel;

	public StateTableRefactored(EventManager eventManager, LevelManager levelManager) {
		myMap = new HashMap<Integer, State>();
		myEventManager = eventManager;
		myLevel = levelManager.getCurrentLevel();
		initEvents();
	}

	/**
	 * Initializes background and Record event
	 */
	private void initEvents() {
		myBackground = new SerialBackground(myLevel.getBackground());
		myEventManager.addEveryTurnEvent("Method.record", new IEventHandler() {
			@Override
			public void handleEvent(Object o) {
				record(myLevel);
			}
		});
	}

	/**
	 * Using the play field, this will record the SpriteReplayData and the
	 * background at that point in time.
	 * 
	 * @param field
	 *            - PlayField passed to the StateTable to be recorded.
	 */
	private void updateStateTable(VoogaPlayField field) {
		State moment = new State();
		for (SpriteGroup<Sprite> spriteGroup : field.getAllSpriteGroups()) {
			moment.addSpriteGroupToState(spriteGroup);
		}
		myMap.put(myMap.size(), moment); //tack the state onto the end
	}
	
	/**
	 * Creates a replay object using this StateTable
	 * @param parent VoogaGame object (In case you wish to return to the VoogaGame?)
	 * @return Replay object declared with current StateTable
	 */
	public Replay replayTable(VoogaGame parent) {
		return new Replay(parent, this, myMap.size());// REASON - takes a normal StateTable
	}

	/**
	 * When called by Replay, this updates the PlayField based a single state in the state
	 * table, at index replayTime.
	 * @param g
	 */
	public void render(Graphics2D g) {
		myBackground.getBackground().render(g);
		myMap.get(replayTime).render(g);
	}

	/**
	 * This will set the position of the sprites to their position at a certain
	 * point in time. This returns false once reach the last value of the table.
	 * 
	 * @param t
	 *            - Time, location in time relative to "StateTable indices"
	 */
	public void transformToState(int t) {
		replayTime = t;
		myMap.get(replayTime).transformSpritesToState();
	}

	/**
	 * Updates StateTable based on the kind of Game element being recorded.
	 * 
	 * @param field
	 *            - PlayField from which the recording is made
	 */
	public void record(VoogaPlayField field) {
		updateStateTable(field);
	}
}
