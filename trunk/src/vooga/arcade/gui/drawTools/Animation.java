package vooga.arcade.gui.drawTools;

import java.awt.DisplayMode;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Animation {

	private Screen screen;
	private Image bg;
	private AnimationRunner a;

	public void loadPics() {
		bg = new ImageIcon("C:\\Users\\CP\\Desktop\\pond1").getImage();
		Image one = new ImageIcon("resources\\Green.jpg").getImage();
		Image two = new ImageIcon("resources\\Green2.jpg").getImage();
		a = new AnimationRunner();
		a.addScene(one, 250);
		a.addScene(two, 250);
	}

	public void run(DisplayMode dm) {
	}
}
