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
import vooga.reflection.Reflection;
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
    
    private static Point TOP_LEFT_CORNER = new Point(100,100);
    private static int SPAWN_RATE = 100;
    private boolean spawning = false;
    
    private static int EMPTY = 0;
    private static int WALL = 1;
    private static int TILE = 2;
    private static int BALL = 4;
    private static int CHECKED = 5;
    
    private static int NUM_HIT_WALL=0;
    
    private Map<String, int[]> modifierMap;
    private String[] mapKey= new String[]{"Up", "Down", "Right", "Left"};
    private int[][] mapValue = new int[][]{new int[]{0,-20}, new int[]{0,20}, new int[]{20,0}, new int[]{-20,0}};
    
    int[][] tileArray = new int[15][15];
    
    
    public Level (Collection<SpriteGroup<Sprite>> players, VoogaGame game)
    {
        super(players, game);
        this.game = (Jezzball)game;
        initTileArray();
        addCollisionManager(new CreateWallCollision(game, getSpriteGroup("ball"), getSpriteGroup("tile")));
        
        modifierMap = new HashMap<String, int[]>();
        for(int i=0; i<mapKey.length; i++){
            modifierMap.put(mapKey[i], mapValue[i]);
        }

        registerEvents();
        
    }
    private void initTileArray() {
        for(int i = 0; i<tileArray.length; i++){
            for(int j = 0; j < tileArray.length; j++){
                if(i==0|| i==tileArray.length-1|| j==0 || j==tileArray[i].length-1){
                    tileArray[i][j] = WALL;
                }else{
                    tileArray[i][j]= EMPTY;
                }
            }
        }
        
    }
    
    private void setTileArray(int typeFrom, int typeTo){
        for (int i = 0; i< tileArray.length; i++){
            for (int j = 0; j < tileArray[i].length; j++){
                if(tileArray[i][j]==typeFrom){
                    tileArray[i][j]=typeTo;
                }
            }
        }
    }
    
    public void resetSpawn() {
        spawning = false;
        NUM_HIT_WALL=0;
        setTileArray(TILE,EMPTY);
        for(Sprite s : getSpriteGroup("tile").getSprites()){
            s.setActive(false);
        }
    }
    
    public void spawnTile(int x, int y){
        addArchetypeSprite("tile", x, y);
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
        tileArray[getTileXCoord(p)][getTileYCoord(p)]=TILE;
        game.fireEvent(this, "Level.spawnTile", p);
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
        
        if(!checkToContinue(WALL, p)) return;
        
        setTileArrayGivenPoint(TILE, p);
        game.fireEvent(this, "Level.spawnTile", p);
        game.addTimer("spawn"+direction, SPAWN_RATE, "Game.spawn"+direction, p);
        
        
    }

    
    public Point modifyPoint(Point p, String direction){
        int[] modifierArray = modifierMap.get(direction);
        return new Point((int)p.getX()+modifierArray[0], (int)p.getY()+modifierArray[1]);
    }



    private boolean checkToContinue(int type, Point location){
        if(!checkTileArrayGivenPoint(type, location)){
            return true;
        }
        
        NUM_HIT_WALL++;
        if(NUM_HIT_WALL==2){
            NUM_HIT_WALL=0;
            game.fireEvent(this,"Level.consolidateWall");
            setTileArray(TILE, WALL);
            fillEmptyChamber();
            spawning = false;
        }
        return false;
    }
    
    
    private void fillEmptyChamber() {
        int[][] tempArray = new int[tileArray.length][tileArray[0].length];
        setBallLocations(tempArray);
        
        for(int i = 0; i<tempArray.length; i++){
            for(int j = 0; j < tempArray.length; j++){
                recurselyCheckToFill(tempArray, i, j);
            }
        }
    }

    protected void setBallLocations(int[][] tempArray) {
        for(Sprite s : getSpriteGroup("ball").getSprites()){
            int x = (int) ((s.getX()-TOP_LEFT_CORNER.getX())/20);
            int y = (int) ((s.getY()-TOP_LEFT_CORNER.getY())/20);
            tempArray[x][y]=BALL;
            System.out.println("ball is at " + x + " " + y);
        }
    }

    private boolean recurselyCheckToFill(int[][] tempArray, int i, int j) {
        //base case-if there is a ball, cannot fill in the chamber
        if(tempArray[i][j]==BALL) return false;
        //Other conditions to end recursion
        if(tempArray[i][j]==CHECKED) return true;
        if(tileArray[i][j]==WALL) return true;
        if(i<0 || i>=tempArray.length) return true;
        if(j<0 || j >= tempArray[i].length) return true;
        
        tempArray[i][j]=CHECKED;
        boolean result = (recurselyCheckToFill(tempArray, i+1, j) && recurselyCheckToFill(tempArray, i-1, j) && recurselyCheckToFill(tempArray, i, j-1) && recurselyCheckToFill(tempArray, i, j+1));
        if(result){
            addArchetypeSprite("wall", (int)(TOP_LEFT_CORNER.getX()+i*20), (int)(TOP_LEFT_CORNER.getY()+j*20));
        }else{
            tempArray[i][j]=BALL;
        }
        return result;
    }

    private boolean checkTileArrayGivenPoint(int type, Point location){
        int x = getTileXCoord(location);
        int y = getTileYCoord(location);
        return tileArray[x][y] == type;
        
    }
    
    private void setTileArrayGivenPoint(int type, Point location){
        int x = getTileXCoord(location);
        int y = getTileYCoord(location);
        tileArray[x][y] = type;
    }
    
    private int getTileXCoord(Point location){
        return (int)(location.getX()-TOP_LEFT_CORNER.getX())/20;
    }
    
    private int getTileYCoord(Point location){
        return (int)(location.getY()-TOP_LEFT_CORNER.getY())/20;
    }
    
    
    private void registerEvents() {
        
        
        game.registerEventHandler("Level.spawnTile", new IEventHandler() {
            
            @Override
            public void handleEvent(Object o) {
                Point p = (Point)o;
                spawnTile((int)p.getX(), (int)p.getY());
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
