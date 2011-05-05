package games.pong;

import games.breakout.sprites.Paddle;
import games.pong.sprites.PlayerPaddle;

import java.awt.Dimension;


import javax.imageio.ImageIO;


import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.resources.images.ImageLoader;
import vooga.sprites.spritegroups.SpriteGroup;
import vooga.view.function.AbstractGraphicsFunction;


import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.background.ColorBackground;
	


public class PongGame extends VoogaGame {
	public static EventManager eventManager;
	public static ImageLoader imageLoader;

	public static void main(String[] args) {
		launchGame(new PongGame(), new Dimension(640, 480), false);
	}

	public void updatePlayField(long elapsedTime) {	}


	@Override
	public void initResources() {
		eventManager = getEventManager();
        imageLoader = getImageLoader();
        
        PlayerPaddle playerPaddle = new PlayerPaddle(this, 10, 170);
        getLevelManager().addPlayer(new SpriteGroup<PlayerPaddle>("PlayerPaddle", playerPaddle));
		getLevelManager().loadLevel(0);
	}
}
