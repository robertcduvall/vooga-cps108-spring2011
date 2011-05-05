package games.jezzball.tileManager;

import java.awt.Point;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

/**
 * manager class for managing tile maps in the game
 * @author KevinWang
 *
 */
public abstract class TileManager {
    
    protected String[][] tileArray;
    protected Point origin = new Point(100,100);
    protected int tileSize = 20;
    protected Map<String, List<TileManager.TileCoordinate>> tileGroups;
    
    
    
    public TileManager(int x, int y, int tileSize){
        tileArray = new String[x][y];
        this.tileSize = tileSize;
        tileGroups = new HashMap<String, List<TileManager.TileCoordinate>>();
        initTileArray();
    }
    
    public TileManager(int x, int y, int tileSize, Point origin){
        this(x, y, tileSize);
        this.origin = origin;
    }
    
    protected abstract void initTileArray();
    
    /**
     * convert a tile type to another
     * 
     * @param typeFrom type to be converted
     * @param typeTo type converted to
     */
    public void setTileArray(String typeFrom, String typeTo){
        for (int i = 0; i< tileArray.length; i++){
            for (int j = 0; j < tileArray[i].length; j++){
                if(getTile(i,j)== typeFrom){
                    setTile(i,j, typeTo);
                }
            }
        }
    }
    
    /**
     * set tile type using a point
     * @param p
     * @param type
     */
    public void setTile(Point p, String type){
        setTile(pointToTileX(p),pointToTileY(p),type);
    }
    
    /**
     * set tile type using x,y
     * @param x x index of the tile array
     * @param y y index of the tile array
     * @param type type to be set to
     */
    public void setTile(int x, int y, String type){
        removeTileFromGroup(x, y, type);
        tileArray[x][y]=type;
        addToTileGroup(x, y, type);
    }

    /**
     * add a tile to a tileGroup
     * @param x x index of the tile array
     * @param y y index of the tile array
     * @param type type of the tile
     */
    protected void addToTileGroup(int x, int y, String type) {
        if(tileGroups.containsKey(type)){
            tileGroups.get(type).add(new TileCoordinate(x, y));
        }else{
            tileGroups.put(type, new ArrayList<TileCoordinate>(Arrays.asList(new TileCoordinate(x,y))));
        }
    }
    
    /**
     * remove a tile from a tileGroup
     * 
     * @param x x index of the tile array
     * @param y y index of the tile array
     * @param type type of the tile
     */
    protected void removeTileFromGroup( int x, int y, String type) {
        if(!tileGroups.containsKey(type)) return;
        
        TileCoordinate current = new TileCoordinate(x,y);
        List<TileCoordinate> tileList = tileGroups.get(type);
        
        for(int i = 0 ; i < tileList.size(); i++){
            if(tileList.get(i).equals(current)){
                tileList.remove(i);
                return;
            }
        }
    }

    /**
     * return tile type given point
     * @param p
     * @return
     */
    public String getTile(Point p){
        return getTile(pointToTileX(p), pointToTileY(p));
    }
    
    /**
     * return tile type give index of the tile array
     * 
     * @param x x index of the tile array
     * @param y y index of the tile array
     * @return
     */
    public String getTile(int x, int y){
        return tileArray[x][y];
    }
    
    /**
     * Add all sprites of a spriteGroup to the tile from their position
     * 
     * @param group SpriteGroup
     */
    public void addSpriteInGroupToTileArray(SpriteGroup<?> group){
        for(Sprite s : group.getSprites()){
            Point location = new Point((int)s.getX(), (int)s.getY());
            setTile(location, group.getName());
        }
    }
    
    /**
     * convert point to x coordinate relative to origin
     * @param location
     * @return
     */
    protected int pointToTileX(Point location){
        return (int)(location.getX()-origin.getX())/tileSize;
    }

    /**
     * convert point to y coordinate relative to origin
     * @param location
     * @return
     */
    protected int pointToTileY(Point location){
        return (int)(location.getY()-origin.getY())/tileSize;
    }
    
    /**
     * convert tile coordinates to a point relative to origin
     * @param x
     * @param y
     * @return
     */
    protected Point tileToPoint(int x, int y){
        return new Point((int)origin.getX()+x*tileSize, (int)origin.getY()+y*tileSize);
    }
    /**
     * return list of tiles from the same group
     * @param key
     * @return Coordinates representing a tile.
     */
    protected List<TileCoordinate> getTileGroup(String key){
        return tileGroups.get(key);
    }
    
    /**
     * private class created so that it would not be confused with
     * the java point class
     * 
     * @author KevinWang
     *
     */
    private class TileCoordinate{
        private int x;
        private int y;
        
        public TileCoordinate(){
            this(0,0);
        }
        
        public TileCoordinate(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        public int getX(){
            return x;
        }
        
        public int getY(){
            return y;
        }

        public boolean equals(TileCoordinate t){
            return x==t.getX() && y == t.getY();
        }
    }
    
}
