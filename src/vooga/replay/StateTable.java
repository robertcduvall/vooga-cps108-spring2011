package vooga.replay;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.ImageBackground;

/**
 * Records all the sprites and background that were updated in the game. Used as
 * the medium from which Sprite data is replayed.
 * 
 * @author Josue, Chris
 * 
 */

@SuppressWarnings("serial")
public class StateTable implements Serializable {
	
	protected Map<Sprite, ArrayList<SpriteReplayData>> myMap;
	protected BufferedImageSerialData myBackgroundImageData;
	protected Background myBackground;
	protected int time;
	protected SerialPlayField lastPlayField;

	public StateTable() {
		myMap = new HashMap<Sprite, ArrayList<SpriteReplayData>>();
		lastPlayField = new SerialPlayField();
		time = 0;
	}

	
	public Replay replayTable(GameEngine parent){
		return new Replay(parent, this, time);
	}
	/**
	 * Using the play field, this will record the SpriteReplayData and the
	 * background at that point in time.
	 * 
	 * @param field
	 *            - PlayField passed to the StateTable to be recorded.
	 */
	public void updateStateTable(PlayField field) {
		myBackgroundImageData  = new BufferedImageSerialData(((ImageBackground)field.getBackground()).getImage());
		for (SpriteGroup s : field.getGroups()) {
			updateHelper(s.getSprites());
		}
		for (Sprite a : myMap.keySet()) {
			if (!(myMap.get(a).size() == time + 1)) {
				myMap.get(a).add(new SpriteReplayData(a, false));
			}
		}
		time++;
	}
	
	/**
	 * Update helper method.
	 */
	private void updateHelper(Sprite[] sprites) {
		for (Sprite a : sprites) {
			if (a != null && a.isActive()) {
				if (!myMap.containsKey(a)) {
					myMap.put(a, new ArrayList<SpriteReplayData>());
					for (int decrement = time; decrement > 0; decrement--) {
						myMap.get(a).add(new SpriteReplayData(a, false));
					}
				}
				myMap.get(a).add(new SpriteReplayData(a, a.isActive()));
			}
		}

	}

	public void render(Graphics2D g) {
		myBackground.render(g);
		for (Sprite s : myMap.keySet()) {
			if(s.isActive()) s.render(g);
		}
	}

	/**
	 * This will set the position of the sprites to their position at a certain
	 * point in time. Also set the background at that time. This returns false
	 * once reach the last value of the table.
	 * 
	 * @param t
	 *            - Time, location in time relative to "StateTable indices"
	 */
	public void transformSpritesToState(int t) {
		myBackground = new ImageBackground(myBackgroundImageData.getImage());
		for (Sprite s : myMap.keySet()) {
			s = myMap.get(s).get(t).transformSprite(s);
			s.setBackground(myBackground);
		}
	}

	/**
	 * Updates StateTable based on the kind of Game element being recorded.
	 * 
	 * @param field
	 *            - PlayField from which the recording is made
	 */
	public void record(PlayField field) {
		updateStateTable(field);
		//if (field != null)
			//lastPlayField.setPlayField(field);
	}

	public void record(Sprite s) {
		PlayField tempField = new PlayField();
		tempField.add(s);
		this.record(tempField);
	}

	public void record(SpriteGroup sg) {
		PlayField tempField = new PlayField();
		tempField.addGroup(sg);
		this.record(tempField);
	}
}
