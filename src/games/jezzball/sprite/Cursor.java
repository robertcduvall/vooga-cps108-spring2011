package games.jezzball.sprite;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;

public class Cursor extends Sprite {
    private boolean vertical = true;
    private VoogaGame game;

    public Cursor(BufferedImage image,final VoogaGame game, int x, int y) {
        super(image, x, y);
        this.game = game;
        /*
         * game.addEveryTurnEvent("trackCursor", new IEventHandler() {
         * 
         * @Override public void handleEvent(Object o) { setX(game.getMouseX());
         * setY(game.getMouseY()); } });
         * 
         * game.registerEventHandler("Input.changeOrientation", new
         * IEventHandler() {
         * 
         * @Override public void handleEvent(Object o) { vertical = !vertical; }
         * });
         * 
         * game.registerEventHandler("Input.clicked", new IEventHandler() {
         * 
         * @Override public void handleEvent(Object o) { //TODO spawn new sprite
         * that will cause tiles to
         * 
         * } });
         */

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
            }
        });

    }

    public void moveLeft() {
        this.move(-20, 0);
        System.out.println("move to " +this.getX() + " " + this.getY());
    }

    public void moveRight() {
        this.move(20, 0);
        System.out.println("move to " +this.getX() + " " + this.getY());
    }

    public void moveUp() {
        this.move(0, -20);
        System.out.println("move to " +this.getX() + " " + this.getY());
    }

    public void moveDown() {
        this.move(0, 20);
        System.out.println("move to " +this.getX() + " " + this.getY());
    }
    
    

    public Point getCoordinate() {
        return new Point((int)this.getX(), (int)this.getY());
    }

    public boolean orientationIsVertical() {
        return vertical;
    }
    
    @Override
    public void render(Graphics2D g){
        super.render(g);
    }

}
