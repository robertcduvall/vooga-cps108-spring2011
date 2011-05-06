package vooga.arcade.view.gui;

/**
 * 
 * @author Andrea
 * New object to accommodate refactoring changes.
 *
 */

public class ProductionState
{
    private String pathToResources = "vooga.arcade.resources.";
    private String resourceFileSuffix = "ButtonsResource";
    
    public static State currentState;
    
    public enum State {
        LOGINTOOLBAR, USERTOOLBAR
    }
    
    public ProductionState (State state)
    {
        currentState = state;
    }
    
    public static void changeStates(State newState)
    {
        currentState = newState;
    }
    
    public String getStateResources()
    {
        // System.out.println("Current State: " + this.currentState.toString());
        return pathToResources + currentState.toString() + resourceFileSuffix;
    }
}
