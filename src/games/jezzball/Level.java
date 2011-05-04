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
                consolidateWalls();
            }
        });
        
        game.registerEventHandler("Level.fillWall", new IEventHandler() {
            
            @Override
            public void handleEvent(Object o) {
                fillWall((List<Point>)o);
            }
        });
        
        game.registerEventHandler("Level.collideWithTile", new IEventHandler() {
            
            @Override
            public void handleEvent(Object o) {
                collideWithTile();
                
            }

            
        });
        
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
    
    public void collideWithTile() {
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
    
    public void consolidateWalls(){
        for(Sprite s : getSpriteGroup("tile").getSprites()){
            if(s.isActive()){
                addArchetypeSprite("wall", (int)s.getX(), (int)s.getY());
                s.setActive(false);
            }
            
        }
        
        System.out.println("Consolidate!");
    }
    
    public void fillWall(List<Point> pointList){
        for(Point p : pointList){
            addArchetypeSprite("wall", (int)p.getX(), (int)p.getY());
        }
    }
    
    
    

    public void spawnVertical(Point p){
        if(spawning) return;
        spawning = true;
        tileArray[getXGrid(p)][getYGrid(p)]=TILE;
        game.fireEvent(this, "Level.spawnTile", p);
        game.addTimer("spawnUp", SPAWN_RATE, "Game.spawnUp", p);
        game.addTimer("spawnDown", SPAWN_RATE, "Game.spawnDown", p);
    }
    
    public void spawnHorizontal(Point p){
        if(spawning) return;
        spawning = true;
        tileArray[getXGrid(p)][getYGrid(p)]=TILE;
        game.fireEvent(this, "Level.spawnTile", p);
        game.addTimer("spawnLeft", SPAWN_RATE, "Game.spawnLeft", p);
        game.addTimer("spawnRight", SPAWN_RATE, "Game.spawnRight", p);
    }
    
    public void spawnGivenDirection(Point p, String direction){
        p = modifyPoint(p, direction);
        
        if(!checkToContinue(WALL, p)){
            return;
        }
        setGrid(TILE, p);
        game.fireEvent(this, "Level.spawnTile", p);
        game.addTimer("spawn"+direction, SPAWN_RATE, "Game.spawn"+direction, p);
        
        
    }
    /**
     * @param p
     * @param direction
     * @return
     */
    protected Point getNewPoint(Point p, String direction) {
        try {
            Method m =this.getClass().getDeclaredMethod(direction, Point.class);
            p= (Point) m.invoke(this, p);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
    
    public Point modifyPoint(Point p, String direction){
        int[] modifierArray = modifierMap.get(direction);
        return new Point((int)p.getX()+modifierArray[0], (int)p.getY()+modifierArray[1]);
    }
    
    public void spawnUp(Point p){
        if(!spawning) return;
        p = new Point((int)p.getX(), (int)p.getY()-20);
        if(!checkToContinue(WALL, p)){
            return;
        }
        setGrid(TILE, p);
        game.fireEvent(this, "Level.spawnTile", p);
        game.addTimer("spawnUp", SPAWN_RATE, "Game.spawnUp", p);
    }
    
    public void spawnDown(Point p){
        if(!spawning) return;
        p = new Point((int)p.getX(), (int)p.getY()+20);
        if(!checkToContinue(WALL, p)){
            return;
        }
        setGrid(TILE, p);
        game.fireEvent(this, "Level.spawnTile", p);
        game.addTimer("spawnDown", SPAWN_RATE, "Game.spawnDown", p);
    }
    
    public void spawnLeft(Point p){
        if(!spawning) return;
        p = new Point((int)p.getX()-20, (int)p.getY());
        if(!checkToContinue(WALL, p)){
            return;
        }
        setGrid(TILE, p);
        game.fireEvent(this, "Level.spawnTile", p);
        game.addTimer("spawnLeft", SPAWN_RATE, "Game.spawnLeft", p);
    }
    
    public void spawnRight(Point p){
        if(!spawning) return;
        p = new Point((int)p.getX()+20, (int)p.getY());
        if(!checkToContinue(WALL, p)){
            return;
        }
        setGrid(TILE, p);
        game.fireEvent(this, "Level.spawnTile", p);
        game.addTimer("spawnRight", SPAWN_RATE, "Game.spawnRight", p);
    }



    private boolean checkToContinue(int type, Point location){
        if(!checkGrid(type, location)){
            return true;
        }
        
        NUM_HIT_WALL++;
        if(NUM_HIT_WALL==2){
            NUM_HIT_WALL=0;
            game.fireEvent(this,"Level.consolidateWall");
            consolidateToWall();
            fillWalls();
            spawning = false;
        }
        return false;
    }
    
    
    private void fillWalls() {
        int[][] tempArray = new int[tileArray.length][tileArray[0].length];
        SpriteGroup<Sprite> sg = getSpriteGroup("ball");
        
        for(Sprite s : sg.getSprites()){
            int x = (int) ((s.getX()-TOP_LEFT_CORNER.getX())/20);
            int y = (int) ((s.getY()-TOP_LEFT_CORNER.getY())/20);
            tempArray[x][y]=BALL;
            System.out.println("ball is at " + x + " " + y);
        }
        
        for(int i = 0; i<tempArray.length; i++){
            for(int j = 0; j < tempArray.length; j++){
                recurseCheck(tempArray, i, j);
            }
        }
        
    }

    private boolean recurseCheck(int[][] tempArray, int i, int j) {
        if(tempArray[i][j]==CHECKED) return true;
        if(tileArray[i][j]==WALL) return true;
        if(tempArray[i][j]==BALL) return false;
        if(i<0 || i>=tempArray.length) return true;
        if(j<0 || j >= tempArray[i].length) return true;
        tempArray[i][j]=CHECKED;
        
        boolean result = (recurseCheck(tempArray, i+1, j) && recurseCheck(tempArray, i-1, j) && recurseCheck(tempArray, i, j-1) && recurseCheck(tempArray, i, j+1));
        System.out.println("position " + i + " " + j);
        if(result){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
        if(result){
            addArchetypeSprite("wall", (int)(TOP_LEFT_CORNER.getX()+i*20), (int)(TOP_LEFT_CORNER.getY()+j*20));
        }else{
            tempArray[i][j]=BALL;
        }
        return result;
    }

    private void consolidateToWall() {
        setTileArray(TILE, WALL);
    }

    private boolean checkGrid(int type, Point location){
        int x = getXGrid(location);
        int y = getYGrid(location);
        return tileArray[x][y] == type;
        
    }
    
    private void setGrid(int type, Point location){
        int x = getXGrid(location);
        int y = getYGrid(location);
        tileArray[x][y] = type;
    }
    
    private int getXGrid(Point location){
        return (int)(location.getX()-TOP_LEFT_CORNER.getX())/20;
    }
    
    private int getYGrid(Point location){
        return (int)(location.getY()-TOP_LEFT_CORNER.getY())/20;
    }
    
    
    private void registerEvents() {
        game.registerEventHandler("Game.SpawnVerticalTile", new IEventHandler() {
            
            @Override
            public void handleEvent(Object o) {
                spawnVertical((Point)o);
            }
        });
        
        game.registerEventHandler("Game.SpawnHorizontalTile", new IEventHandler() {
            @Override
            public void handleEvent(Object o) {
                spawnHorizontal((Point)o);
            }
        });
        
        game.registerEventHandler("Game.spawnUp", new IEventHandler() {
            @Override
            public void handleEvent(Object o) {
                //spawnUp((Point)o);
                spawnGivenDirection((Point)o, "Up");
            }
        });
        
        game.registerEventHandler("Game.spawnDown", new IEventHandler() {
            @Override
            public void handleEvent(Object o) {
                spawnDown((Point)o);
            }
        });
        
        game.registerEventHandler("Game.spawnRight", new IEventHandler() {
            @Override
            public void handleEvent(Object o) {
                spawnRight((Point)o);
            }
        });
        
        game.registerEventHandler("Game.spawnLeft", new IEventHandler() {
            @Override
            public void handleEvent(Object o) {
                spawnLeft((Point)o);
            }
        });
        
        
    }

}
