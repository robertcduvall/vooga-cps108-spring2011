package levels;

/**
 * Represents an exceptional situation specific to the model.
 * 
 * @author Andrew Patterson
 * @author Robert C. Duvall
 */
@SuppressWarnings("serial")
public class LevelException extends RuntimeException
{
    public static LevelException NON_EXISTANT_LEVEL =
        new LevelException("The specified level does not exist");
    public static LevelException NON_EXISTANT_LEVEL_ORDER =
        new LevelException("The level order has not been defined");
    public static LevelException LEVEL_LOADING_ERROR =
        new LevelException("There was an error loading the level");
    public static LevelException NON_EXISTANT_SPRITE =
        new LevelException("The specified sprite does not exist");


    /**
     * Create exception with given message
     * 
     * @param message explanation of problem
     */
    public LevelException (String message)
    {
        super(message);
    }
}
