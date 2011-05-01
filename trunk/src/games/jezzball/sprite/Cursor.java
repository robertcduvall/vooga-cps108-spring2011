package games.jezzball.sprite;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;

public class Cursor extends Sprite {
    private boolean vertical= true;
    
    public Cursor(final VoogaGame game, int x, int y){
        super();
        game.addEveryTurnEvent("trackCursor", new IEventHandler() {
            
            @Override
            public void handleEvent(Object o) {
                setX(game.getMouseX());
                setY(game.getMouseY());
            }
        });
        
        game.registerEventHandler("Input.changeOrientation", new IEventHandler() {
            
            @Override
            public void handleEvent(Object o) {
                vertical = !vertical;
            }
        });
        
        game.registerEventHandler("Input.clicked", new IEventHandler() {
            
            @Override
            public void handleEvent(Object o) {
                //TODO spawn new sprite that will cause tiles to 
                
            }
        });
    }
}
