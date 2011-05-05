package vooga.replay;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.core.event.IEventHandler;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;
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

	private Map<Integer, Map<Sprite, SpriteReplayData>> myMap;
	private SerialBackground myBackground;
	private int gameTime, replayTime;
	private EventManager myEventManager;
	private LevelManager myLevelManager;

	public StateTableRefactored(EventManager eventManager, LevelManager levelManager) {
		myMap = new HashMap<Integer, Map<Sprite, SpriteReplayData>>();
		gameTime = 0;
		myEventManager = eventManager;
		myLevelManager = levelManager;
		initEvents();
	}

	public void initEvents() {
		myEventManager.addEveryTurnEvent("Method.record", new IEventHandler() {
			@Override
			public void handleEvent(Object o) {
				record(myLevelManager.getCurrentLevel());
			}
		});
	}

	public Replay replayTable(VoogaGame parent) {
		return new Replay(parent, this, gameTime);// REASON - takes a normal StateTable
	}

	/**
	 * Using the play field, this will record the SpriteReplayData and the
	 * background at that point in time.
	 * 
	 * @param field
	 *            - PlayField passed to the StateTable to be recorded.
	 */
	public void updateStateTable(VoogaPlayField field) {
		myBackground = new SerialBackground(field.getBackground());
		Map<Sprite, SpriteReplayData> stateMap = new HashMap<Sprite, SpriteReplayData>();
		for (SpriteGroup<Sprite> spriteGroup : field.getAllSpriteGroups()) {
			updateHelper(spriteGroup.getSprites(), stateMap);
		}
		myMap.put(gameTime, stateMap);
		gameTime++;
	}

	/**
	 * Update helper method. Updates each sprite in Iterable sprites, which represents
	 * the sprites that are in a sprite group.
	 */
	private void updateHelper(Iterable<Sprite> sprites, Map<Sprite, SpriteReplayData> stateMap) {
		for (Sprite sprite : sprites) {
			if (sprite != null && sprite.isActive()) {
				stateMap.put(sprite, new SpriteReplayData(sprite, sprite.isActive()));
			}
		}
	}

	public void render(Graphics2D g) {
		myBackground.getBackground().render(g);
		Map<Sprite, SpriteReplayData> tempMap = myMap.get(replayTime);
		for (Sprite sprite : tempMap.keySet()) {
			sprite.render(g);
		}
	}

	/**
	 * This will set the position of the sprites to their position at a certain
	 * point in time. This returns false once reach the last value of the table.
	 * 
	 * @param t
	 *            - Time, location in time relative to "StateTable indices"
	 */
	public void transformSpritesToState(int t) {
		replayTime = t;
		Map<Sprite, SpriteReplayData> tempMap = myMap.get(replayTime);
		for (Sprite sprite : tempMap.keySet()) {
			sprite = tempMap.get(sprite).transformSprite(sprite);
			sprite.setBackground(myBackground.getBackground());
		}
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
