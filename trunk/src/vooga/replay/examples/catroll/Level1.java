package vooga.replay.examples.catroll;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import vooga.replay.StateTable;
import vooga.replay.StateTableFileManager;
import vooga.sprites.improvedsprites.*;
import vooga.sprites.spritegroups.*;
import vooga.sprites.spritebuilder.builder.SpriteBuilder;

import com.golden.gamedev.GameObject;
///import com.golden.gamedev.engine.audio.JavaLayerMp3Renderer;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.util.ImageUtil;

public class Level1 extends GameObject {

	private StateTableFileManager stfm = new StateTableFileManager();

	private StateTable myTable;
	private ImageSaveSprite myPlayer;
	private Background myBackground;
	private int angle = 0;
	private BufferedImage image = getImage("resources/images/cat.png");
	private SpriteGroup PLAYER_GROUP;
	private boolean right = false;

	private PlayField myField;

	private SpriteGroup ENEMY_GROUP;

	public Level1(CatRollGame engine) {
		super(engine);
	}

	@Override
	public void initResources() {
		myTable = new StateTable();
		myField = new PlayField();

		myPlayer = new ImageSaveSprite(image, "vooga/replay/examples/catroll/resourses/images/inGameCat");
		PLAYER_GROUP = new SpriteGroup("player group");
		PLAYER_GROUP.add(myPlayer);
		myBackground = new ImageBackground(
				getImage("resources/images/background.png"));
		PLAYER_GROUP.setBackground(myBackground);
		/*myField.addGroup(PLAYER_GROUP);
		myField.setBackground(myBackground);*/

	}

	@Override
	public void render(Graphics2D g) {
		myField.render(g);
		PLAYER_GROUP.render(g);
		myBackground.render(g);
	}

	@Override
	public void update(long elapsedTime) {
		myField.update(elapsedTime);
		if (keyDown(KeyEvent.VK_D)) {
			myPlayer.moveX(.08 * elapsedTime);
			angle += elapsedTime;
			right = true;
			myPlayer.setImage(ImageUtil.rotate(image, angle));
		}
		if (keyDown(KeyEvent.VK_A)) {
			myPlayer.moveX(-.08 * elapsedTime);
			angle -= elapsedTime;
			right = false;
			myPlayer.setImage(ImageUtil.rotate(image, angle));
		}
		if (keyDown(KeyEvent.VK_S)) {
			myPlayer.moveY(.08 * elapsedTime);
			elapsedTime = right ? elapsedTime : -elapsedTime;
			angle += elapsedTime;
			myPlayer.setImage(ImageUtil.rotate(image, angle));
		}
		if (keyDown(KeyEvent.VK_W)) {
			myPlayer.moveY(-.08 * elapsedTime);
			elapsedTime = right ? elapsedTime : -elapsedTime;
			angle += elapsedTime;
			myPlayer.setImage(ImageUtil.rotate(image, angle));
		}

		if (keyDown(KeyEvent.VK_ESCAPE)) {
			stfm.writeToNewSaveFile(myTable, "st");
			parent.nextGame = new CatReplay(parent, myTable);
			finish();
		}
		myTable.record(myField);

	}

}
