package games.patterson_game.refactoredVooga.replay;

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
	
	protected Map<Integer, Map<Sprite, SpriteReplayData>> myMap;
	protected BufferedImageSerialData myBackgroundImageData;
	protected Background myBackground;
	protected int gameTime;
	protected int replayTime;
	protected SerialPlayField lastPlayField;

	public StateTable() {
		myMap = new HashMap<Integer, Map<Sprite, SpriteReplayData>>();
		lastPlayField = new SerialPlayField();
		gameTime = 0;
	}

	
	public Replay replayTable(GameEngine parent){
		return new Replay(parent, this, gameTime);
	}
	/**
	 * Using the play field, this will record the SpriteReplayData and the
	 * background at that point in time.
	 * 
	 * @param field
	 *            - PlayField passed to the StateTable to be recorded.
	 */
	public void updateStateTable(PlayField field) 
	{
		myBackgroundImageData  = new BufferedImageSerialData(((ImageBackground)field.getBackground()).getImage());
		Map<Sprite, SpriteReplayData> tempMap = new HashMap<Sprite, SpriteReplayData>();
		for (SpriteGroup spriteGroup : field.getGroups()) 
		{
			updateHelper(spriteGroup.getSprites(), tempMap);
		}
		myMap.put(gameTime, tempMap);
		gameTime++;
	}
	
	/**
	 * Update helper method.
	 */
	private void updateHelper(Sprite[] sprites, Map<Sprite, SpriteReplayData> tempMap) 
	{	
		for (Sprite sprite : sprites) 
		{
			if (sprite != null && sprite.isActive()) 
			{
				tempMap.put(sprite, new SpriteReplayData(sprite, sprite.isActive()));
			}
		}
	}
	
	public void render(Graphics2D g) 
	{
		myBackground.render(g);
		Map<Sprite, SpriteReplayData> tempMap = myMap.get(replayTime);
		for (Sprite sprite : tempMap.keySet()) 
		{
			sprite.render(g);
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
	public void transformSpritesToState(int t) 
	{
		replayTime = t;
		myBackground = new ImageBackground(myBackgroundImageData.getImage());
		Map<Sprite, SpriteReplayData> tempMap = myMap.get(replayTime);
		for (Sprite sprite : tempMap.keySet()) 
		{
			sprite = tempMap.get(sprite).transformSprite(sprite);
			sprite.setBackground(myBackground);
		}
	}

	/**
	 * Updates StateTable based on the kind of Game element being recorded.
	 * 
	 * @param field
	 *            - PlayField from which the recording is made
	 */
	public void record(PlayField field) 
	{
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
