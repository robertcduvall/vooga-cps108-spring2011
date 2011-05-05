package games.fishin.sprites;

import games.fishin.Fishin;
import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.AnimatedSprite;

@SuppressWarnings("serial")
public class Rod extends AnimatedSprite {
    private VoogaGame game;
    private boolean isReeling;

    public Rod(Fishin game, int x, int y) {
        super(game.getImageLoader().getAnimation("rodUp"), x, y);
        this.game = game;

        isReeling = false;
        bindEvents();
    }

    private void bindEvents() {
        game.registerEventHandler("FishBiteStart", new IEventHandler()
        {
            @Override
            public void handleEvent(Object o)
            {
                isReeling = true;
                game.fireEvent(this, "FishBiteStartExclamation");
                update();
            }
        });
        game.registerEventHandler("FishBiteDone", new IEventHandler()
        {
            @Override
            public void handleEvent(Object o)
            {
                isReeling = false;
                game.fireEvent(this, "FishBiteDoneExclamation");
                update();
            }
        });
        game.registerEventHandler("Input.User.Action", new IEventHandler() {
            @Override
            public void handleEvent(Object o) {
                if (isReeling){
                    game.fireEvent(this, "FishCaught");
                    game.fireEvent(this, "FishBiteDone");
                    System.out.println("Fish caught!");
                }
            }
        });
    }

    public void update() {
        if (isReeling) {
            super.setImages(game.getImageLoader().getAnimation("rodDown"));
        }
        else {
            super.setImages(game.getImageLoader().getAnimation("rodUp"));
        }
    }
}
