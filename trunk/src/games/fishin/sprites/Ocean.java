package games.fishin.sprites;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.AnimatedSprite;

@SuppressWarnings("serial")
public class Ocean extends AnimatedSprite {
    private int x;
    private double t;
    private VoogaGame game;
    
    public Ocean(VoogaGame game, int x, int y) {
        super(game.getImageLoader().getAnimation("ocean"), x, y);
        this.x = 0;
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
        x +=(int) (10*Math.sin(t));
        t += Math.PI/180;
        if (t >= 2*Math.PI) {
            t -= 2*Math.PI;
        }
        super.setX(x);
        super.updateAnimation();
        //super.setMovement(0.1, Math.cos(t));
    }
}
