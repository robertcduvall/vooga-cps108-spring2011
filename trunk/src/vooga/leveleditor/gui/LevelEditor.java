package vooga.leveleditor.gui;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.engine.BaseInput;
import com.golden.gamedev.gui.TButton;
import com.golden.gamedev.gui.TFloatPanel;
import com.golden.gamedev.gui.toolkit.FrameWork;

public class LevelEditor extends TFloatPanel{
	
	private TButton myButton;

	public LevelEditor(GameObject game, String name, boolean closable, boolean iconable, int location_x,
			int location_y, int size_x, int size_y) {
		super(name, closable, iconable, location_x, location_y, size_x, size_y);
		myButton = new ExampleButton(game, "Sprite",0 ,0, 100, 100);
		this.add(myButton);
	}



}
