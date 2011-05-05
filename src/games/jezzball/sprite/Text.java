package games.jezzball.sprite;

import java.awt.image.BufferedImage;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;

public class Text extends Sprite {
    public Text(BufferedImage image, int x, int y, VoogaGame game){
        super(image, x, y);
        this.setAngle(Direction.NORTH.getAngle());
        game.registerEventHandler("Sprite.text.die", new IEventHandler() {
            
            @Override
            public void handleEvent(Object o) {
                setActive(false);
                
            }
        });
        game.addTimer("endScene", 5000, "Sprite.text.die");
    }
}
