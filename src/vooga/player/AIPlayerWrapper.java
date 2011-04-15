/**
 * 
 */
package vooga.player;

/**
 * @author Andrea Scripa
 */
public abstract class AIPlayerWrapper extends AbstractPlayerWrapper
{
    /**
     * AIPlayer's constructor.
     */
    public AIPlayerWrapper (Player p)
    {
        super(p);
        p.storeWrapper(this);
        type = Player.PlayerType.AI_PLAYER;
    }
    
    /**
     * AIPlayer's input listeners.  These should generally be to certain changes in the 
     * state of the game.  These listeners should parse the input and pass it to
     * calculateAIResponse, which will likely call the AI engine when it is made.
     * The response should come back in the form of a string (the Player's command), which
     * gets passed to Player's updateEntities method.
     */
    public abstract void addInputListeners();
    
    public abstract void calculateAIResponse(String event);
}
