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
    //Need a way of decoding packets?
    
    public String getIPAddress(AbstractPlayer p)
    {
    	return "";
        //Call networking engine for this info.
    }
    
    // TODO: Add input listener
    
}
