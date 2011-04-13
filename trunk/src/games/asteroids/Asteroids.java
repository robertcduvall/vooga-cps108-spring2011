package games.asteroids;

import java.awt.Graphics2D;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

import vooga.core.*;
import vooga.core.event.IEventHandler;

public class Asteroids extends VoogaGame {
	private PlayField myPlayfield;
    private Background mBackground;
    private SpriteGroup myShip;
    private SpriteGroup myAsteriods;
    private BasicCollisionGroup playerMissileToAsteroid, playerToAsteroid;
    private int score;

    private void setup()
    {
        myPlayfield = new PlayField(); // Background from resources
        playerMissileToAsteroid = new BasicCollisionGroup()
        {
            @Override
            public void collided(Sprite s1, Sprite s2)
            {
                s1.setActive(false);
                s2.setActive(false);
                score++;
            }
        };
        playerToAsteroid = new BasicCollisionGroup()
        {

            @Override
            public void collided(Sprite s1, Sprite s2)
            {
                s1.setActive(false);
                s2.setActive(false);
            }
        };

    }
	
	
	@Override
	public void initResources() {
		//TODO: Setup team fill this in.
		this.setup();
		//fire Event to start menu
		super.fireEvent(this, "start.menu");
		//fire event to start level
		super.fireEvent(this, "start.level");
		super.registerEventHandler("level.complete", new IEventHandler() {
			@Override
			public void handleEvent(Object o) {
				onLevelComplete();
			}
		});
	}

	private void onLevelComplete() {
		super.fireEvent(this, "level.change");
	}

	@Override
	public void render(Graphics2D g) {
		myPlayfield.render(g);
	}

	@Override
	public void updatePlayField(long elapsedTime) {
		myPlayfield.update(elapsedTime);
	}

}
