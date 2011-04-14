package vooga.player.interfaces;

import vooga.player.Player;

public interface INetworkable
{   
    // Call network engine for this info.
    public String getMACAddress(Player p);
  
    public String getIPAddress(Player p);
    
    public String getUserName(Player p);
    
    // TODO: Add input listener
    // Need a way of decoding packets?
}
