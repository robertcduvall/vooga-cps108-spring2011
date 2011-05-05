package games.jezzball.sprite;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;

/**
 * player controlled cursor that creates new wall.
 * 
 * @author KevinWang
 *
 */
public class Cursor extends Sprite {
    private boolean vertical = true;
    private VoogaGame game;
    private int tileSize;

    public Cursor(BufferedImage image,final VoogaGame game, int x, int y, int tileSize) {
        super(image, x, y);
        this.game = game;
        this.tileSize = tileSize;
        this.setAngle(Direction.NORTH.getAngle());

        registerEvents();

    }

    /**
     * @param game
     */
    protected void registerEvents() {
        game.registerEventHandler("Input.Right", new IEventHandler() {
            @Override
            public void handleEvent(Object o) {
                moveRight();
            }
        });

        game.registerEventHandler("Input.Down", new IEventHandler() {
            @Override
            public void handleEvent(Object o) {
                moveDown();
            }
        });

        game.registerEventHandler("Input.Up", new IEventHandler() {
            @Override
            public void handleEvent(Object o) {
                moveUp();
            }
        });

        game.registerEventHandler("Input.Left", new IEventHandler() {
            @Override
            public void handleEvent(Object o) {
                moveLeft();
            }
        });
        
        game.registerEventHandler("Input.spawnTile", new IEventHandler() {
            
            @Override
            public void handleEvent(Object o) {
                
                if(vertical){
                    game.fireEvent(this, "Game.SpawnVerticalTile", getCoordinate());
                } else{
                    game.fireEvent(this, "Game.SpawnHorizontalTile", getCoordinate());
                }
            }
        });
        
        game.registerEventHandler("Input.changeOrientation", new IEventHandler() {
            
            @Override
            public void handleEvent(Object o) {
                vertical = !vertical;
                if(vertical){
                    setImage(game.getImageLoader().getImage("vertical_arrow"));
                }else{
                    setImage(game.getImageLoader().getImage("horizontal_arrow"));
                }
                
            }
        });
    }

    /**
     * moves left
     */
    public void moveLeft() {
        this.move(-tileSize, 0);
    }
    
    /**
     * moves right
     */
    public void moveRight() {
        this.move(tileSize, 0);
    }

    /**
     * moves up
     */
    public void moveUp() {
        this.move(0, -tileSize);
    }

    /**
     * moves down
     */
    public void moveDown() {
        this.move(0, tileSize);
    }
    
    
    /**
     * returns coordinate
     * @return new point
     */
    public Point getCoordinate() {
        return new Point((int)this.getX(), (int)this.getY());
    }

    /**
     * indiate state of the cursor
     * @return
     */
    public boolean orientationIsVertical() {
        return vertical;
    }

}
