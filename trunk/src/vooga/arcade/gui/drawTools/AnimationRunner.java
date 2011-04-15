package vooga.arcade.gui.drawTools;

import java.awt.Image;
import java.util.ArrayList;

public class AnimationRunner {

	private ArrayList<OneScene> scenes;
	private int sceneIndex;
	private long movieTime;
	private long totalTime;

	// This is the basic animation constructor
	public AnimationRunner() {
		scenes = new ArrayList<OneScene>();
		totalTime = 0;
		start();
	}

	public synchronized void addScene(Image i, long t) {
		totalTime += t;
		scenes.add(new OneScene(i, t)); // i is image, total is time
	}

	// start animation
	public synchronized void start() {
		movieTime = 0;
		sceneIndex = 0;
	}

	public synchronized void update(long timePassed) {
		if (scenes.size() > 1) {
			movieTime += timePassed;
			if (movieTime >= totalTime) {
				movieTime = 0;
				sceneIndex = 0;
			}
			while (movieTime > getScene(sceneIndex).endTime) {
				sceneIndex++;
			}
		}
	}

	public synchronized Image getImage() {
		if (scenes.size() == 0) {
			return null;
		} else {
			return getScene(sceneIndex).pic;
		}
	}

	private OneScene getScene(int x) {
		return scenes.get(x);
	}

	private class OneScene {
		Image pic;
		long endTime;

		public OneScene(Image p, long et) {
			this.endTime = et;
			this.pic = p;
		}
	}
}
