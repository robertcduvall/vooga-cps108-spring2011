package games.jezzball;

import java.awt.Point;

import vooga.core.event.EventManager;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;
import vooga.sprites.improvedsprites.Sprite;

public class TileManager {
    
    int[][] tileArray= new int[15][15]; // double array representing the grid layout of the game field
    private static Point TOP_LEFT_CORNER = new Point(100,100);
    private EventManager eventManager;
    
    public static int EMPTY = 0;
    public static int WALL = 1;
    public static int TILE = 2;
    public static int BALL = 4;
    public static int CHECKED = 5;
    
    public TileManager(EventManager eventManager){
        initTileArray();
        this.eventManager = eventManager;
    }
    
    private void initTileArray() {

        //tileArray = new int[resource.getInteger("gridSizeX")][resource.getInteger("gridSizeY")];

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
    
    public void setTileArray(int typeFrom, int typeTo){
        for (int i = 0; i< tileArray.length; i++){
            for (int j = 0; j < tileArray[i].length; j++){
                if(tileArray[i][j]==typeFrom){
                    tileArray[i][j]=typeTo;
                }
            }
        }
    }
    
    public void setTile(Point p, int type){
        tileArray[getTileXCoord(p)][getTileYCoord(p)]=type;
    }
    
    public int getTile(Point p){
        return tileArray[getTileXCoord(p)][getTileYCoord(p)];
    }
    
    public void fillEmptyChamber(VoogaPlayField playField) {
        int[][] tempArray = new int[tileArray.length][tileArray[0].length];
        setBallLocations(tempArray, playField);

        for(int i = 0; i<tempArray.length; i++){
            for(int j = 0; j < tempArray.length; j++){
                recurselyCheckToFill(tempArray, i, j);
            }
        }
    }

    protected void setBallLocations(int[][] tempArray, VoogaPlayField playField) {
        for(Sprite s : playField.getSpriteGroup("ball").getSprites()){
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
            eventManager.fireEvent(this, "Level.spawnSprite", new Object[]{new Point((int)(TOP_LEFT_CORNER.getX()+i*20), (int)(TOP_LEFT_CORNER.getY()+j*20)), "wall"});
        }else{
            tempArray[i][j]=BALL;
        }
        return result;
    }


    public void setTileArrayGivenPoint(int type, Point location){
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
}
