package games.fishin.sprites;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.AnimatedSprite;

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
        update();
    }

    private void bindEvents() {
        game.registerEventHandler("FishBiteStartExclamation", new IEventHandler()
        {
            @Override
            public void handleEvent(Object o)
            {
                isVisible = true;
                update();
            }
        });
        game.registerEventHandler("FishBiteDoneExclamation", new IEventHandler()
        {
            @Override
            public void handleEvent(Object o)
            {
                isVisible = false;
                update();
            }
        });
    }

    private void update() {
        if (isVisible) {
            super.setImages(game.getImageLoader().getAnimation("exclamation"));
        }
        else {
            super.setImages(game.getImageLoader().getAnimation("empty"));
        }
    }

}
