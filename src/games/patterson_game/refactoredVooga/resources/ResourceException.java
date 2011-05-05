package games.patterson_game.refactoredVooga.resources;

@SuppressWarnings("serial")
public class ResourceException extends RuntimeException
{
    public static ResourceException resourceReadingException(String resourceName)
    {
        return new ResourceException(String.format("Unable to read the resource file %s", resourceName));
    }
    
    public static ResourceException syntaxException()
    {
        return new ResourceException("Syntax error in resource file.");
    }
    
    public static ResourceException notFoundException()
    {
    	return new ResourceException("Unable to find resource!");
    }

    public ResourceException(String message)
    {
        super(message);
    }
}
