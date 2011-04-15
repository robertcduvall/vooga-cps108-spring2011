package vooga.replay;


import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.golden.gamedev.object.*;

/**
 * Records all the sprites and background that were updated in the game.
 * Used as the medium from which Sprite data is replayed. 
 * 
 * @author Josue, Chris
 * 
 */

@SuppressWarnings("serial")
public class StateTable implements Serializable {

	//private StateTable parent;
	//private ArrayList<StateTable> children;
	protected Map<Sprite, ArrayList<SpriteReplayData>> myMap;
	protected List<Background> backgroundList;
	protected Background myBackground;
	protected int time;
	protected SerialPlayField lastPlayField;

	public StateTable() {
		myMap = new HashMap<Sprite, ArrayList<SpriteReplayData>>();
		backgroundList = new ArrayList<Background>();
		time = 0;
		lastPlayField = new SerialPlayField();
	}

	public Map<Sprite, ArrayList<SpriteReplayData>> getTable() {
		return myMap;
	}
	
	public Background getBackground(){
		return myBackground;
	}
	
	public int getTime(){
		return time;
	}
	
	/**
	 * Using the play field, this will record the SpriteReplayData and the
	 * background at that point in time.
	 * 
	 * @param field - PlayField passed to the StateTable to be recorded.
	 */
	public void updateStateTable(PlayField field) {
		backgroundList.add(field.getBackground());
		for (SpriteGroup s : field.getGroups()) {
			updateHelper( s.getSprites());
		}
		for(Sprite a: myMap.keySet()){
			if(!(myMap.get(a).size()==time+1)){
				myMap.get(a).add(new SpriteReplayData(a,-1,-1));
			}
		}
		time++;
	}
	
	/**
	 * Update helper method.
	 */
	private void updateHelper(Sprite[] sprites){ 
		for (Sprite a : sprites) {
			if (a != null) {
				if (!myMap.containsKey(a)) {
					myMap.put(a, new ArrayList<SpriteReplayData>());
					for (int decrement = time; decrement > 0; decrement--) {
						myMap.get(a).add(new SpriteReplayData(a,-1, -1));
					}
				}
				myMap.get(a).add(new SpriteReplayData(a));
			}
		}
		
	}

	public void render(Graphics2D g) {
		myBackground.render(g);
		for (Sprite s : myMap.keySet()) {
			s.render(g);
		}
	}

	/**
	 * This will set the position of the sprites to their position at a certain
	 * point in time. Also set the background at that time. This returns false
	 * once reach the last value of the table.
	 * 
	 * @param t - Time, location in time relative to "StateTable indices"
	 */
	public void transformSprite(int t) {
		myBackground = backgroundList.get(t);
		for (Sprite s : myMap.keySet()) {
			SpriteReplayData sData = myMap.get(s).get(t);
			s.setLocation(sData.getX(), sData.getY());
			s.setImage(sData.getImage());
			s.setBackground(myBackground);
		}
		
	}
	public PlayField getLastPlayField(){
		return (PlayField)lastPlayField;
	}
	/**
	 * Updates StateTable based on the kind of Game element being recorded.
	 * 
	 * @param field - PlayField from which the recording is made
	 */
	public void record(PlayField field) {
		updateStateTable(field);
		if(field != null)
			lastPlayField.setPlayField(field);
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
