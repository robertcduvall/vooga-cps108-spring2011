package vooga.player;

public interface INetworkable
{

    // The network team should just be firing events...
    // extend the listener and fire off to the server/locally.
    // So maybe we can get rid of these.
    //Need a way of decoding packets?
    
    // Call network engine for this info.
    public String getMACAddress(AbstractPlayer p);
  
    
    // TODO: Add input listener
}
