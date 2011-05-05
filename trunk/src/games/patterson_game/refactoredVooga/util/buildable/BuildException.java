package games.patterson_game.refactoredVooga.util.buildable;

public class BuildException extends RuntimeException
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final String DIFFERENT_COMPONENTS =
        "Trying to combine two different components";
    public static final String DOES_NOT_HAVE_COMPONENT =
        "Artist does not contain this components";
    public static final String BUILDABLEOBJECT_DNE =
        "Buildable Object Does Not Exist";
    public static final String NULL_COMPONENT_NOT_ALLOWED =
        "Cannot instantiate a null Component";
    public static final String ERROR_IN_CLONING = "Cloning Error";
    public static final String EMPTY_CONSTRUCTOR_DNE =
        "Cannot instantiate the empty constructor for this Component";
    public static final String NO_DEFAULT_CONSTRUCTOR =
        "This Component does not have a default constructor";
    public static final String BAD_INPUT = 
        "This constructor does not carry the number of input qualities";
    public static final String BUILDING_ERROR = "Error while building the specified Buildable Object";
    
    public static final String NO_PRIMITIVE_TYPE = "Cannot internally set a primitive type component";
    
    public BuildException (String message)
    {
        super(message);
    }


    public BuildException (String message, Throwable throwable)
    {
        super(message, throwable);
    }

}
