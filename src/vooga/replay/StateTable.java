package vooga.replay;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.levels.VoogaPlayField;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.object.Background;
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
	protected SerialBackground myBackground;
	protected int gameTime;
	protected int replayTime;
	protected SerialPlayField lastPlayField;
	protected VoogaGame game;

	public StateTable() {
		myMap = new HashMap<Integer, Map<Sprite, SpriteReplayData>>();
		lastPlayField = new SerialPlayField();
		gameTime = 0;
	}
	public StateTable(VoogaGame game){
		this();
		this.game = game;
		initEvents();
	}
	public void initEvents(){
		this.game.addEveryTurnEvent("Method.record", new IEventHandler(){
	    	@Override
	    	public void handleEvent(Object o){
	    		record(game.getLevelManager().getCurrentLevel());
	    	}
	    });
	}
	public Replay replayTable(VoogaGame parent){
		return new Replay(parent, this, gameTime);
	}
	/**
	 * Using the play field, this will record the SpriteReplayData and the
	 * background at that point in time.
	 * 
	 * @param field
	 *            - PlayField passed to the StateTable to be recorded.
	 */
	public void updateStateTable(VoogaPlayField field) 
	{
		//myBackgroundImageData  = new BufferedImageSerialData(((ImageBackground)field.getBackground()).getImage());
		myBackground = new SerialBackground(field.getBackground());
		Map<Sprite, SpriteReplayData> tempMap = new HashMap<Sprite, SpriteReplayData>();
		for (SpriteGroup<Sprite> spriteGroup : field.getAllSpriteGroups()) 
		{
			updateHelper(spriteGroup.getSprites(), tempMap);
		}
		myMap.put(gameTime, tempMap);
		gameTime++;
	}
	
	/**
	 * Update helper method.
	 */
	private void updateHelper(Iterable<Sprite> sprites, Map<Sprite, SpriteReplayData> tempMap) 
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
		myBackground.getBackground().render(g);
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
		//myBackground = new ImageBackground(myBackgroundImageData.getImage());
		Map<Sprite, SpriteReplayData> tempMap = myMap.get(replayTime);
		for (Sprite sprite : tempMap.keySet()) 
		{
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
	public void record(VoogaPlayField field) 
	{
		updateStateTable(field);
		//if (field != null)
			//lastPlayField.setPlayField(field);
	}

	public void record(Sprite s) {
		VoogaPlayField tempField = new VoogaPlayField();
		tempField.addSprite(s);
		this.record(tempField);
	}

	public void record(SpriteGroup<Sprite> sg) {
		VoogaPlayField tempField = new VoogaPlayField();
		tempField.addSpriteGroup(sg);
		this.record(tempField);
	}
}
