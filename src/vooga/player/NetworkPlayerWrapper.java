package vooga.player;


/**
 * @author Andrea Scripa
 */
public abstract class NetworkPlayerWrapper extends AbstractPlayerWrapper
{
    /**
     * NetworkPlayer's constructor.
     */
    public NetworkPlayerWrapper (Player p)
    {
        super(p);
        p.storeWrapper(this);
        type = Player.PlayerType.NETWORK_PLAYER;
    }

    /**
     * NetworkPlayer's input listeners.  These should be connected to the network engine
     * and should decode the packets it listens for.  This input gets parsed into a 
     * command (String) and sent to the Player's updateEntities method.
     */
    public abstract void addInputListeners();
}
