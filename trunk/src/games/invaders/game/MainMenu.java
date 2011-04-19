package games.invaders.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ColorBackground;

public class MainMenu extends GameObject {

	GameFont font;

	BufferedImage title;
	BufferedImage arrow;

	int option;

	boolean animate, animate2;
	Timer aniTimer = new Timer(200);

	AnimatedSprite blueStar, redStar;

	public Timer discoTimer = new Timer((int) Math.round((1000.0 * 60) / 118));
	PlayField playField;

	public MainMenu(GameEngine parent) {
		super(parent);
	}

	@Override
	public void initResources() {

		setMaskColor(Color.YELLOW);

		ColorBackground discoBackground = new ColorBackground(Color.BLACK);
		playField = new PlayField(discoBackground);
		Sprite invadersBackground = new Sprite(
				getImage("resources/images/asi_title.png"));
		playField.add(invadersBackground);

		arrow = getImage("resources/images/MenuArrow.png");

		font = fontManager.getFont(getImage("resources/images/BitmapFont.png"));

		blueStar = new AnimatedSprite(getImages(
				"resources/images/BlueStar.png", 7, 1), 126, 22);
		blueStar.setAnimationTimer(aniTimer);
		playField.add(blueStar);
		redStar = new AnimatedSprite(getImages("resources/images/RedStar.png",
				7, 1), 339, 150);
		redStar.setAnimationTimer(aniTimer);
		playField.add(redStar);

	}

	@Override
	public void update(long elapsedTime) {

		blueStar.update(elapsedTime);
		redStar.update(elapsedTime);

		if (animate) {

			blueStar.setAnimate(true);
			redStar.setAnimate(true);

			animate = false;
			aniTimer.refresh();

		} else {
			if (aniTimer.action(elapsedTime))
				animate = true;
		}

		switch (bsInput.getKeyPressed()) {
		case KeyEvent.VK_ENTER:
			if (option == 0) {
				// start
				parent.nextGameID = Invaders.GAME_MODE;
				finish();

			}

			if (option == 1) {
				// end
				finish();
			}
			break;

		case KeyEvent.VK_UP:
			option--;
			if (option <= 0)
				option = 0;
			break;

		case KeyEvent.VK_DOWN:
			option++;
			if (option >= 1)
				option = 1;
			break;

		case KeyEvent.VK_ESCAPE:
			finish();
			break;

		}
		updateBackground(elapsedTime);
	}

	private void updateBackground(long elapsedTime) {
		if (discoTimer.action(elapsedTime)) {
			// Background colorBackground = new ColorBackground(new Color(0));
			Background colorBackground = new ColorBackground(new Color(
					(int) (Math.random() * 256 * 256 * 256)));
			playField.setBackground(colorBackground);
			discoTimer.refresh();
		}
	}

	@Override
	public void render(Graphics2D g) {
		playField.render(g);

		font.drawString(g, "START", 257, 145);
		font.drawString(g, "EXIT", 257, 165);

		// TODO: Do something about this
		// g.drawString("Copyright (c) 2005 Arbor Games Inc.", 60, 230);

		g.drawImage(arrow, 230, 145 + (option * 20), null);
	}

}