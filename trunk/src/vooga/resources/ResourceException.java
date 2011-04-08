package vooga.resources;

@SuppressWarnings("serial")
public class ResourceException extends RuntimeException
{
    public static ResourceException resourceReadingException(String resourceName)
    {
        return new ResourceException(String.format("Unable to read the resource file %s", resourceName));
    }

    public ResourceException(String message)
    {
        super(message);
    }
}
