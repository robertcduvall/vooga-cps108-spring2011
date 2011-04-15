package vooga.player;


/**
 * @author Andrea Scripa
 */
public abstract class HumanPlayerWrapper extends AbstractPlayerWrapper
{

    /**
     * HumanPlayer's constructor.
     */
    public HumanPlayerWrapper (Player p)
    {
        super(p);
        p.storeWrapper(this);
        type = Player.PlayerType.HUMAN_PLAYER;
    }
    
    /**
     * HumanPlayer's input listeners.  These should generally be to key events, or possibly
     * to button clicks.  This input gets parsed into a command (String) and sent to
     * the Player's updateEntities method.
     */
    public abstract void addInputListeners();
}
