package games.jezzball;

import games.jezzball.collision.CreateWallCollision;
import games.jezzball.sprite.Cursor;

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
 * @author Michael Ansel
 * @author Wesley Brown
 *
 */
public class Level extends AbstractLevel
{
    private Jezzball game;

    
    private static int SPAWN_RATE = 100;
    private boolean spawning = false;

    

    private static int NUM_HIT_WALL=0;
    private static int CONTACT_ON_BOTH_SIDES = 2;

    private Map<String, int[]> modifierMap;
    private String[] modifierMapKey= new String[]{"Up", "Down", "Right", "Left"};
    private int[][] modifierMapValue = new int[][]{new int[]{0,-20}, new int[]{0,20}, new int[]{20,0}, new int[]{-20,0}};
    
    private TileManager tileManager;
    
    private Bundle resource;

    public Level (Collection<SpriteGroup<Sprite>> players, VoogaGame game)
    {
        super(players, game);        
        this.game = (Jezzball)game;
        resource = getBundle();
        tileManager= new TileManager(game.getEventManager());
        //SPAWN_RATE = resource.getInteger("spawnRate");
        //TOP_LEFT_CORNER = new Point(resource.getInteger("topLeftX"), resource.getInteger("topLeftY"));

        
        addCollisionManager(new CreateWallCollision(game, getSpriteGroup("ball"), getSpriteGroup("tile")));
        initModifierMap();
        registerEvents();

    }
    /**
     * 
     */
    protected void initModifierMap() {
        modifierMap = new HashMap<String, int[]>();
        for(int i=0; i<modifierMapKey.length; i++){
            modifierMap.put(modifierMapKey[i], modifierMapValue[i]);
        }
    }


    public void resetSpawn() {
        spawning = false;
        NUM_HIT_WALL=0;
        tileManager.setTileArray(TileManager.TILE,TileManager.EMPTY);
        for(Sprite s : getSpriteGroup("tile").getSprites()){
            s.setActive(false);
        }
    }

    public void spawnSprite(String type, int x, int y){
        addArchetypeSprite(type, x, y);
    }

    

    
    @Override
    public void loadLevel ()
    {
        addAllSpritesFromPool();
        addBackground();
    }

    public void replaceSprites(String replaced, String toReplace){
        for(Sprite s : getSpriteGroup(replaced).getSprites()){
            if(s.isActive()){
                addArchetypeSprite(toReplace, (int)s.getX(), (int)s.getY());
                s.setActive(false);
            }
        }
    }

    public void initialSpawn(Point p, boolean vertical){
        if(spawning) return;
        spawning = true;
        tileManager.setTile(p, TileManager.TILE);
        
        spawnSprite("tile", (int)p.getX(), (int)p.getY());
        if(vertical){
            game.addTimer("spawnUp", SPAWN_RATE, "Game.spawnUp", p);
            game.addTimer("spawnDown", SPAWN_RATE, "Game.spawnDown", p);
        }else{
            game.addTimer("spawnLeft", SPAWN_RATE, "Game.spawnLeft", p);
            game.addTimer("spawnRight", SPAWN_RATE, "Game.spawnRight", p);
        }

    }


    public void spawnGivenDirection(Point p, String direction){
        if(!spawning) return;

        p = modifyPoint(p, direction);

        if(!checkToContinue(tileManager.WALL, p)) return;

        tileManager.setTileArrayGivenPoint(tileManager.TILE, p);
        spawnSprite("tile", (int)p.getX(), (int)p.getY());
        game.addTimer("spawn"+direction, SPAWN_RATE, "Game.spawn"+direction, p);


    }


    public Point modifyPoint(Point p, String direction){
        int[] modifierArray = modifierMap.get(direction);
        return new Point((int)p.getX()+modifierArray[0], (int)p.getY()+modifierArray[1]);
    }



    private boolean checkToContinue(int type, Point location){
        if(tileManager.getTile(location)!=type){
            return true;
        }

        NUM_HIT_WALL++;
        if(NUM_HIT_WALL==CONTACT_ON_BOTH_SIDES){
            NUM_HIT_WALL=0;
            game.fireEvent(this,"Level.consolidateWall");
            tileManager.setTileArray(TileManager.TILE, TileManager.WALL);
            tileManager.fillEmptyChamber(this);
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
                spawnSprite(type, (int)p.getX(), (int)p.getY());
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


        final String[] directions = new String[]{"Up", "Down", "Left", "Right"};

        for(int i =0; i<directions.length; i++){
            final int count = i;
            game.registerEventHandler("Game.spawn"+directions[count], new IEventHandler() {
                @Override
                public void handleEvent(Object o) {
                    spawnGivenDirection((Point)o, directions[count]);
                }
            });
        }

    }

}
