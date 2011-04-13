package vooga.replay.examples.catroll;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ImageBackground;

public class MainMenu extends GameObject {

	private Sprite startButton, replayButton;
	private Background mainBackground;

	public MainMenu(CatRollGame engine) {
		super(engine);
	}

	@Override
	public void initResources() {
		mainBackground = new ImageBackground(getImage("resources/images/main.png"));
		replayButton = new Sprite(getImage("resources/images/replay.png"), this.getWidth() / 2,this.getHeight() / 4
				);
		startButton = new Sprite(getImage("resources/images/start.png"), this.getWidth() / 2,this.getHeight()- this.getHeight() / 4
				);
		startButton.setX(startButton.getX() - startButton.getWidth() / 2);
		replayButton.setX(replayButton.getX() - startButton.getWidth() / 2);
		startButton.setBackground(mainBackground);
	}

	@Override
	public void render(Graphics2D g) {
		mainBackground.render(g);
		startButton.render(g);
		replayButton.render(g);
	}

	@Override
	public void update(long elapsedTime) {
		startButton.update(elapsedTime);
		if (click()) {
			if ((checkPosMouse(startButton, true))) {
				parent.nextGameID = 1;
				finish();
			}
			if (checkPosMouse(replayButton, true)) {
				parent.nextGameID = 2;
				finish();
			}
		}

	}

}
