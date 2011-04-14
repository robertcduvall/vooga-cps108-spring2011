/**
 * 
 */
package vooga.player;

/**
 * @author Kevin Tao and Andrea Scripa We will create an all-events listener, to
 *         feed info to the AIPlayer.
 */
public abstract class AIPlayerWrapper 
{
    private Player myPlayer;
    
    //Listen to events to find out what has changed/what the opponent has done.
    //Convert to common currency command and call AI engine with this information.
    //Convert results of AI Engine and call updateSprite
    
    
    public AIPlayer(Player p){
        Player myPlayer = p;
    }
}
