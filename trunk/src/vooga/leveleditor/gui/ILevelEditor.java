package vooga.leveleditor.gui;

import com.golden.gamedev.Game;

public interface ILevelEditor {
	/**
	 * Sets the key stroke to bring up the level editor.
	 * @param key
	 */
	public void setLevelEditorKey(int key);
	/**
	 * Sets the type of the level for the Level Editor. Examples of different level
	 * types are Static, panning, etc.
	 * @param game
	 */
	public void setLevelEditorType(Game game);
}
