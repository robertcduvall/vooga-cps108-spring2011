package games.fishin.sprites;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.AnimatedSprite;

@SuppressWarnings("serial")
public class Ocean extends AnimatedSprite {
    private double t;
    private VoogaGame game;
    
    public Ocean(VoogaGame game, int x, int y) {
        super(game.getImageLoader().getAnimation("ocean"), x, y);
        t = 0;
        this.game = game;
        bindEvents();
    }
    
    private void bindEvents() {
        game.registerEventHandler("MoveOcean", new IEventHandler()
        {
            @Override
            public void handleEvent(Object o)
            {
                moveOcean();
            }
        });
    }

    public void moveOcean() {
        t += Math.PI/8;
        if (t >= 2*Math.PI) {
            t -= 2*Math.PI;
        }
        super.setMovement(Math.cos(t)/6, 0);
    }
}
