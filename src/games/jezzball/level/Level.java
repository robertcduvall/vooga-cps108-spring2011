package games.jezzball.level;

import games.jezzball.Jezzball;
import games.jezzball.collision.CreateWallCollision;
import games.jezzball.sprite.Cursor;
import games.jezzball.tileManager.JezzballTileManager;
import games.jezzball.tileManager.TileManager;

import java.awt.Point;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.levels.AbstractLevel;
import vooga.levels.LevelManager;
import vooga.reflection.Reflection;
import vooga.resources.bundle.Bundle;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

/**
 * Level class managing adding and removing sprites
 * 
 * @author KevinWang
 *
 */
public class Level extends AbstractLevel
{
    private Jezzball game;
    private static int SPAWN_RATE = 100;
    private boolean spawning = false;
    private int gridSize = 20;

    private static int NUM_HIT_WALL=0;
    private static int CONTACT_ON_BOTH_SIDES = 2;

    private Map<String, int[]> modifierMap;// Map used to make writing code to move in specific directions easier
    private final String[] modifierMapKey= new String[]{"Up", "Down", "Right", "Left"};
    private int[][] modifierMapValue = new int[][]{new int[]{0,-gridSize}, new int[]{0,gridSize}, new int[]{gridSize,0}, new int[]{-gridSize,0}};
    
    private JezzballTileManager tileManager;
    
    //Should use resource manager instead of hard coding numbers and words.
    //However, it seems like bundle doesn't work.
    public Level (Collection<SpriteGroup<Sprite>> players, VoogaGame game)
    {
        super(players, game);        
        this.game = (Jezzball)game;
        tileManager= new JezzballTileManager(15, 15, gridSize, new Point(100,100), game.getEventManager());
        
        addCollisionManager(new CreateWallCollision(game, getSpriteGroup("ball"), getSpriteGroup("tile")));
        initModifierMap();
        registerEvents();

    }
    /**
     * initialized modifierMap
     */
    protected void initModifierMap() {
        modifierMap = new HashMap<String, int[]>();
        for(int i=0; i<modifierMapKey.length; i++){
            modifierMap.put(modifierMapKey[i], modifierMapValue[i]);
        }
    }


    /**
     * Method to reset spawning of new wall
     */
    public void resetSpawn() {
        spawning = false;
        NUM_HIT_WALL=0;
        tileManager.setTileArray("tile","empty");
        for(Sprite s : getSpriteGroup("tile").getSprites()){
            s.setActive(false);
        }
    }

    

    
    @Override
    public void loadLevel ()
    {
        addAllSpritesFromPool();
        addBackground();
    }

    /**
     * replace certain sprites with a different type of sprite
     * @param replaced
     * @param toReplace
     */
    public void replaceSprites(String replaced, String toReplace){
        for(Sprite s : getSpriteGroup(replaced).getSprites()){
            if(s.isActive()){
                addArchetypeSprite(toReplace, (int)s.getX(), (int)s.getY());
                s.setActive(false);
            }
        }
    }

    /**
     * jumpstart the spawing process
     * 
     * @param p
     * @param vertical
     */
    public void initialSpawn(Point p, boolean vertical){
        if(spawning) return;
        spawning = true;
        tileManager.setTile(p, "tile");
        
        addArchetypeSprite("tile", (int)p.getX(), (int)p.getY());
        if(vertical){
            game.addTimer("spawnUp", SPAWN_RATE, "Game.spawnUp", p);
            game.addTimer("spawnDown", SPAWN_RATE, "Game.spawnDown", p);
        }else{
            game.addTimer("spawnLeft", SPAWN_RATE, "Game.spawnLeft", p);
            game.addTimer("spawnRight", SPAWN_RATE, "Game.spawnRight", p);
        }

    }


    /**
     * Continuing of spawning given a direction 
     * @param p
     * @param direction
     */
    public void spawnGivenDirection(Point p, String direction){
        if(!spawning) return;

        p = modifyPoint(p, direction);

        if(!checkToContinue("wall", p)) return;

        tileManager.setTile(p, "tile");
        addArchetypeSprite("tile", (int)p.getX(), (int)p.getY());
        game.addTimer("spawn"+direction, SPAWN_RATE, "Game.spawn"+direction, p);


    }


    
    private Point modifyPoint(Point p, String direction){
        int[] modifierArray = modifierMap.get(direction);
        return new Point((int)p.getX()+modifierArray[0], (int)p.getY()+modifierArray[1]);
    }



    private boolean checkToContinue(String type, Point location){
        if(tileManager.getTile(location)!=type){
            return true;
        }

        NUM_HIT_WALL++;
        if(NUM_HIT_WALL==CONTACT_ON_BOTH_SIDES){//if both sides have reached a wall
            NUM_HIT_WALL=0;
            game.fireEvent(this,"Level.consolidateWall");
            tileManager.setTileArray("tile", "wall");
            tileManager.fillEmptyChamber(getSpriteGroup("ball"));
            spawning = false;
        }
        return false;
    }

    private void registerEvents() {


        game.registerEventHandler("Level.spawnSprite", new IEventHandler() {

            @Override
            public void handleEvent(Object o) {
                Object[] arg = (Object[])o;
                Point p = (Point)arg[0];
                String type = (String)arg[1];
                addArchetypeSprite(type, (int)p.getX(), (int)p.getY());
            }
        });

        game.registerEventHandler("Level.consolidateWall", new IEventHandler() {

            @Override
            public void handleEvent(Object o) {
                replaceSprites("tile", "wall");
            }
        });


        game.registerEventHandler("Level.collideWithTile", new IEventHandler() {

            @Override
            public void handleEvent(Object o) {
                resetSpawn();
            }


        });
        game.registerEventHandler("Game.SpawnVerticalTile", new IEventHandler() {

            @Override
            public void handleEvent(Object o) {
                initialSpawn((Point)o, true);
            }
        });

        game.registerEventHandler("Game.SpawnHorizontalTile", new IEventHandler() {
            @Override
            public void handleEvent(Object o) {
                initialSpawn((Point)o, false);
            }
        });

        for(int i =0; i<modifierMapKey.length; i++){
            final int count = i;
            game.registerEventHandler("Game.spawn"+modifierMapKey[count], new IEventHandler() {
                @Override
                public void handleEvent(Object o) {
                    spawnGivenDirection((Point)o, modifierMapKey[count]);
                }
            });
        }

    }

}
