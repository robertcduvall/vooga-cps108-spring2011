package vooga.player;

/**
 * @author Andrea Scripa
 * 
 * The superclass of Player's three types of wrappers.  What all these wrappers have in 
 * common is the ability to parse a specific kind of input.
 */
public abstract class AbstractPlayerWrapper
{
    protected int type;
    
    /**
     * Constructor
     */
    public AbstractPlayerWrapper(Player p)
    {
        // TODO: DJ - once you decide whether I need an instance of VoogaGame or 
        // EventManager (from which to listen for events), load it here.
    }
    
    /**
     * Listeners
     */
    public abstract void addInputListeners();
}
