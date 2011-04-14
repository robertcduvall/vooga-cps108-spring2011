package vooga.player;


/**
 * @author Andrea Scripa
 */
public abstract class NetworkPlayerWrapper extends AbstractPlayerWrapper
{
    public NetworkPlayerWrapper (Player p)
    {
        super(p);
    }

    //Decodes the network packets
    public abstract void addInputListeners();
}
