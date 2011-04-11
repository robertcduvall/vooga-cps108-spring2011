/**
 * 
 */
package vooga.player;

/**
 * @author Kevin Tao and Andrea Scripa
 *
 */
public abstract class NetworkedPlayer extends AbstractPlayer
{
    // The network team should just be firing events...
    // extend the listener and fire off to the server/locally.
    // So maybe we can get rid of these.
    //Need a way of decoding packets?
    
    public String getIPAddress(AbstractPlayer p)
    {
    	return "";
        //Call networking engine for this info.
    }
    
    // TODO: Add input listener
    
}
