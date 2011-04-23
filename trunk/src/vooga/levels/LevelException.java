package vooga.levels;

/**
 * Represents an exception situation specific to levels.
 * 
 * @author Andrew Patterson
 * @author Robert C. Duvall
 */
@SuppressWarnings("serial")
public class LevelException extends RuntimeException
{
    /** Throw when an unwritten level is attempted to be called */
    public static LevelException NON_EXISTANT_LEVEL =
        new LevelException("The specified level does not exist");
    
    /** Throw when the level order has not been specified */
    public static LevelException NON_EXISTANT_LEVEL_ORDER =
        new LevelException("The level order has not been defined");
    
    /** Throw when there is a general error loading a level */
    public static LevelException LEVEL_LOADING_ERROR =
        new LevelException("There was an error loading the level");
    
    /** Throw when there is a general error parsing a level file */
    public static LevelException LEVEL_PARSING_ERROR =
        new LevelException("There was an error parsing the level file");
    
    /** Throw when there is a general error parsing a level file */
    public static LevelException LEVEL_CREATION_ERROR =
        new LevelException("There was a reflection error while attempting to create the level");


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
