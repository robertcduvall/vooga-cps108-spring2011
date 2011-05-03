package games.jezzball.sprite;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;

public class Cursor extends Sprite {
    private boolean vertical= true;
    private VoogaGame game;
    
    public Cursor(BufferedImage image, final VoogaGame game, int x, int y){
        super(image, x, y);
        this.game = game;
        /*
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
        */
    }
    
    @Override
    public void update(long elapsedTime){
        setX(game.getMouseX()-getWidth()/2);
        setY(game.getMouseY()-getHeight()/2);
    }
    
    public Point getCoordinate(){
        return new Point(game.getMouseX(), game.getMouseY());
    }
    
    public boolean orientationIsVertical(){
        return vertical;
    }
    
    public void mouseClicked(){
        game.fireEvent(this, "Cursor.clicked", new Object[]{getCoordinate(), orientationIsVertical()});
    }
}
