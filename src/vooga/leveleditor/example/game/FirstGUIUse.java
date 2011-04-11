package vooga.leveleditor.example.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import vooga.leveleditor.gui.LevelEditor;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.gui.toolkit.FrameWork;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;

public class FirstGUIUse extends GameObject{

	private Background myBackground;
	private FrameWork myFrame;
	private SpriteGroup mySpriteGroup;
	public FirstGUIUse(GameEngine parent) {
		super(parent);
	}

	
	@Override
	public void initResources() {
		myBackground = new ColorBackground(Color.BLUE);
		myFrame = new FrameWork(bsInput, getWidth(), getHeight());
		mySpriteGroup = new SpriteGroup("test");
	}

	@Override
	public void render(Graphics2D arg0) {
		myBackground.render(arg0);
		myFrame.render(arg0);
		mySpriteGroup.render(arg0);
		
	}

	@Override
	public void update(long arg0) {
		myBackground.update(arg0);
		myFrame.update();
		mySpriteGroup.update(arg0);

		if(keyPressed(KeyEvent.VK_L)){
			myFrame.add(new LevelEditor(this, "Demo", true, true, 0, 0, 200, 200));
		}
	}
	public Background getBackground(){
		return myBackground;
	}
	
	public SpriteGroup getSpriteGroup(){
		return mySpriteGroup;
	}

}
