package vooga.player;


/**
 * @author Andrea Scripa
 */
public abstract class HumanPlayerWrapper extends AbstractPlayerWrapper
{

    public HumanPlayerWrapper (Player p)
    {
        super(p);
    }
    
    public abstract void addInputListeners();
}
