/**
 * 
 */
package vooga.player;

/**
 * @author Andrea Scripa
 */
public abstract class AIPlayerWrapper extends AbstractPlayerWrapper 
{
    public AIPlayerWrapper (Player p)
    {
        super(p);
    }
    
    public abstract void addInputListeners();
    
    public abstract void calculateAIResponse(String event);
}
