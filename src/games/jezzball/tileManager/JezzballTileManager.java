package games.jezzball.tileManager;

import games.jezzball.sprite.Ball;

import java.awt.Point;

import vooga.core.event.EventManager;
import vooga.levels.VoogaPlayField;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;
/**
 * implementation of TileManager for Jezzball.
 * 
 * @author KevinWang
 *
 */
public class JezzballTileManager extends TileManager{
    
    protected EventManager eventManager;
    
    public JezzballTileManager(int x, int y, int tileSize, Point origin, EventManager eventManager) {
        super(x, y, tileSize, origin);
        this.eventManager =eventManager;
    }

    /**
     * determine of the wall segmented off an empty chamber. Fill the
     * chamber if it is empty
     * 
     * @param ballGroup
     */
    public void fillEmptyChamber(SpriteGroup<Sprite> ballGroup) {
        String[][] tempArray = new String[tileArray.length][tileArray[0].length];
        addSpriteInGroupToTempArray(tempArray, ballGroup, "ball");
    
        for(int i = 0; i<tempArray.length; i++){
            for(int j = 0; j < tempArray.length; j++){
                recurselyCheckToFill(tempArray, i, j);
            }
        }
    }
    
    /**
     * have all the balls mark its postion in tempArray
     * @param tempArray
     * @param group
     * @param type
     */
    private void addSpriteInGroupToTempArray(String[][] tempArray,
            SpriteGroup<Sprite> group, String type) {
        for(Sprite s : group.getSprites()){
            Point location = new Point((int)s.getX(), (int)s.getY());
            tempArray[pointToTileX(location)][pointToTileY(location)] = type;
        }
    }
    
    /**
     * recursively check mark to see if a section is empty
     * @param tempArray
     * @param i
     * @param j
     * @return
     */
    private boolean recurselyCheckToFill(String[][] tempArray, int i, int j) {
        //base case-if there is a ball, cannot fill in the chamber
        if(tempArray[i][j]=="ball") return false;
        //Other conditions to end recursion
        if(tempArray[i][j]=="checked") return true;
        if(tileArray[i][j]=="wall") return true;
        if(i<0 || i>=tempArray.length) return true;
        if(j<0 || j >= tempArray[i].length) return true;
    
        tempArray[i][j]="checked";
        boolean result = (recurselyCheckToFill(tempArray, i+1, j) && recurselyCheckToFill(tempArray, i-1, j) && recurselyCheckToFill(tempArray, i, j-1) && recurselyCheckToFill(tempArray, i, j+1));
        if(result){
            eventManager.fireEvent(this, "Level.spawnSprite", new Object[]{tileToPoint(i,j), "wall"});
        }else{
            tempArray[i][j]="ball";
        }
        return result;
    }

    /**
     * initialize the tileArray
     */
    @Override
    protected void initTileArray() {
        for(int i = 0; i<tileArray.length; i++){
            for(int j = 0; j < tileArray.length; j++){
                if(i==0|| i==tileArray.length-1|| j==0 || j==tileArray[i].length-1){
                    tileArray[i][j] = "wall";
                }else{
                    tileArray[i][j]= "empty";
                }
            }
        }
    }

}
