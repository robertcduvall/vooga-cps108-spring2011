package vooga.gui;

import vooga.gui.interfaces.IPaneManager; 

import java.awt.Color;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ColorBackground;

/**
 * Class that is basically GameObject, but also implements IPaneManager, so Panes
 * can be created with objects of this class as their parent!
 * @author Dave Crowe, David Colon-Smith
 *
 */
public abstract class VoogaGame extends GameObject implements IPaneManager {
	public Background myBackground;
	
	public VoogaGame(GameEngine parent) {
		super(parent);
		myBackground=new ColorBackground(Color.BLUE, 1024,768);
	}
}
