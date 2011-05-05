package games.fishin.sprites;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.AnimatedSprite;
import vooga.sprites.improvedsprites.Sprite;

@SuppressWarnings("serial")
public class Exclamation extends AnimatedSprite {

    private VoogaGame game;
    private boolean isVisible;

    public Exclamation(VoogaGame game, int x, int y) {
        super(game.getImageLoader().getAnimation("exclamation"), x, y);
        super.setAnimationFrame(0, 0);
        this.game = game;
        isVisible = false;
        bindEvents();
    }

    private void bindEvents() {
        game.registerEventHandler("FishBiteStart", new IEventHandler()
        {
            @Override
            public void handleEvent(Object o)
            {
                isVisible = true;
                update();
            }
        });
        game.registerEventHandler("FishBiteDone", new IEventHandler()
        {
            @Override
            public void handleEvent(Object o)
            {
                isVisible = false;
            }
        });
    }

    private void update() {
        if (isVisible) {

            Sprite omg = game.getLevelManager().addArchetypeSprite("exclamation", 100, 180);
        }
        else {
            this.setActive(false);
        }
    }

}
