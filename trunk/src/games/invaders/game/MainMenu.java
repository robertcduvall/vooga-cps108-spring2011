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
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ImageBackground;

public class MainMenu extends GameObject {

	{
		hideCursor();
	}

	GameFont font;

	BufferedImage title;
	BufferedImage arrow;

	int option;

	boolean animate, animate2;
	Timer aniTimer = new Timer(200);

	AnimatedSprite blueStar, redStar;

	Background mainMenuTitle;

	public MainMenu(GameEngine parent) {
		super(parent);
	}

	@Override
	public void initResources() {

		setMaskColor(Color.YELLOW);

		mainMenuTitle = new ImageBackground(
				getImage("resources/images/title.png"), 320, 240);

		arrow = getImage("resources/images/MenuArrow.png");

		font = fontManager.getFont(getImage("resources/images/BitmapFont.png"));

		blueStar = new AnimatedSprite(getImages(
				"resources/images/BlueStar.png", 7, 1), 26, 22);
		redStar = new AnimatedSprite(getImages("resources/images/RedStar.png",
				7, 1), 239, 150);

		blueStar.setAnimationTimer(aniTimer);
		redStar.setAnimationTimer(aniTimer);

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

	}

	@Override
	public void render(Graphics2D g) {

		mainMenuTitle.render(g);

		blueStar.render(g);
		redStar.render(g);

		font.drawString(g, "START", 157, 145);
		font.drawString(g, "EXIT", 157, 165);

		g.setColor(Color.WHITE);
		g.drawString("Copyright (c) 2005 Arbor Games Inc.", 60, 230);

		g.drawImage(arrow, 130, 145 + (option * 20), null);
	}

}